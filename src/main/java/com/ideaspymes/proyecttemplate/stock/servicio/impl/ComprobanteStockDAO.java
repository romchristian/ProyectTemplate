/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.generico.IAuditable;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.generico.ResolutorReferencia;
import com.ideaspymes.proyecttemplate.stock.enums.EstadoComprobanteStock;
import com.ideaspymes.proyecttemplate.stock.enums.EstadoLote;
import static com.ideaspymes.proyecttemplate.stock.enums.TipoComprobanteStock.COMPRA;
import static com.ideaspymes.proyecttemplate.stock.enums.TipoComprobanteStock.TRANSFERENCIA_EXTERNA;
import static com.ideaspymes.proyecttemplate.stock.enums.TipoComprobanteStock.TRANSFERENCIA_INTERNA;
import static com.ideaspymes.proyecttemplate.stock.enums.TipoComprobanteStock.VENTA;
import com.ideaspymes.proyecttemplate.stock.exception.SinStockException;
import com.ideaspymes.proyecttemplate.stock.model.ComprobanteStock;
import com.ideaspymes.proyecttemplate.stock.model.DetComprobanteStock;
import com.ideaspymes.proyecttemplate.stock.model.LoteExistencia;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IComprobanteStockDAO;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.ILoteExistenciaService;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ComprobanteStockDAO implements IComprobanteStockDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @EJB
    private ResolutorReferencia resolutorRef;

    @EJB
    private ILoteExistenciaService loteExistenciaService;

    @Override
    public ComprobanteStock create(ComprobanteStock entity) {

        // Remueve items vacios
        List<Integer> indices = new ArrayList<>();

        Iterator<DetComprobanteStock> it = entity.getDetalles().iterator();

        while (it.hasNext()) {
            DetComprobanteStock d = it.next();
            if (d.getProducto() == null) {
                it.remove();
            }
        }

        // Fin remueve items vacios
        ComprobanteStock c = abmService.create(entity);
        return c;
    }

    /**
     *
     * @param entity
     * @param lotesPedientes
     * @return
     */
    @Override
    public ComprobanteStock create(ComprobanteStock entity, List<LoteExistencia> lotesPedientes) {

        entity.setDetalles(new ArrayList<DetComprobanteStock>());
        for (LoteExistencia lt : lotesPedientes) {
            if (lt.getEstadoLote() == EstadoLote.PENDIENTE_CONFIRMACION) {
                DetComprobanteStock ds = new DetComprobanteStock();
                ds.setComprobanteStock(entity);
                ds.setProducto(lt.getProducto());
                ds.setUnidadMedida(lt.getUnidadMedida());
                ds.setCantidad(lt.getCantidadIngresada());
                ds.setValor(lt.getCosto());
                entity.getDetalles().add(ds);
            }
        }

        create(entity);

//        for (LoteExistencia lt : lotesPedientes) {
//            if (lt.getEstado() == EstadoLote.PENDIENTE_CONFIRMACION) {
//                lt.setEstado(EstadoLote.ABIERTO);
//                abmService.getEM().merge(lt);
//            }
//        }
        return entity;
    }

    @Override
    public ComprobanteStock confirmar(ComprobanteStock entity) throws SinStockException{

        ComprobanteStock R = entity;
        if (entity.getEstadoComprobate() == EstadoComprobanteStock.PENDIENTE_CONFIRMACION) {

            List<Integer> indices = new ArrayList<>();

            Iterator<DetComprobanteStock> it = entity.getDetalles().iterator();

            while (it.hasNext()) {
                DetComprobanteStock d = it.next();
                if (d.getProducto() == null) {
                    it.remove();
                }
            }

            switch (entity.getTipo()) {
                case VENTA:
                    creaMovientosStockVenta(entity.getDetalles());
                    break;
                case COMPRA:
                    creaMovientosStockCompra(entity.getDetalles());
                    break;
                case TRANSFERENCIA_INTERNA:
                    creaMovientosStockTransferInterna(entity.getDetalles());
                    break;
                case TRANSFERENCIA_EXTERNA:
                    creaMovientosStockTransferExtena(entity.getDetalles());
                    break;
            }

            entity.setEstadoComprobate(EstadoComprobanteStock.CONFIRMADO);
            R = edit(entity);
        }
        return R;
    }

    @Override
    public ComprobanteStock edit(ComprobanteStock entity) {

        List<Integer> indices = new ArrayList<>();

        Iterator<DetComprobanteStock> it = entity.getDetalles().iterator();

        while (it.hasNext()) {
            DetComprobanteStock d = it.next();
            if (d.getProducto() == null) {
                it.remove();
            }
        }
        return abmService.update(entity);
    }

    @Override
    public void remove(ComprobanteStock entity) {
        abmService.delete(entity);
    }

    @Override
    public ComprobanteStock find(Object id) {
        return abmService.find(id, ComprobanteStock.class);
    }

    @Override
    public List<ComprobanteStock> findAll() {
        return abmService.getEM().createQuery("select obj from ComprobanteStock obj").getResultList();
    }

    @Override
    public List<ComprobanteStock> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

    private void creaMovientosStockVenta(List<DetComprobanteStock> detalles) throws SinStockException {

        for (DetComprobanteStock d : detalles) {

            generaAuditoriaUpdate(d);
            if (d.getProducto().getInventariable()) {
                loteExistenciaService.afectaLotesExistenciaMasCovenientes(d);
            }
        }
    }

    private void creaMovientosStockCompra(List<DetComprobanteStock> detalles) {

        for (DetComprobanteStock d : detalles) {
            generaAuditoriaUpdate(d);
            if (d.getProducto().getInventariable()) {
                loteExistenciaService.creaLoteExistencia(d);
            }
        }
    }

    private void creaMovientosStockTransferInterna(List<DetComprobanteStock> detalles) {

    }

    private void creaMovientosStockTransferExtena(List<DetComprobanteStock> detalles) {

    }

    @Override
    public List<ComprobanteStock> findAll(String query, QueryParameter params, int first, int pageSize) {
        return abmService.findByQuery(query, params.parameters(), first, pageSize);
    }

    @Override
    public List<ComprobanteStock> findFilter(String consulta, int first, int pageSize) {
        List<ComprobanteStock> items = new ArrayList<>();
        if (consulta != null) {
            System.out.println("Consulta: " + consulta);
            Query query = abmService.getEM().createNativeQuery(consulta, ComprobanteStock.class);
            if (first > 0) {
                query.setFirstResult(first);
            }

            if (pageSize > 0) {
                query.setMaxResults(pageSize);
            }

            items = (List<ComprobanteStock>) query.getResultList();

        }
        return items;
    }

    @Override
    public List<ComprobanteStock> completar(String matchText) {
        List<ComprobanteStock> sugerencias = new ArrayList<>();

        if (matchText != null && matchText.length() > 0) {
            String consulta = "select * from comprobantestock where estado = 'ACTIVO' and id = " + matchText.toUpperCase().trim() + " order by nombre";
            Query query = abmService.getEM().createNativeQuery(consulta, ComprobanteStock.class);
            query.setMaxResults(20);
            sugerencias = query.getResultList();
        }

        return sugerencias;
    }

    @Override
    public int countFilter(String consulta) {
        int R = 0;

        try {
            Query query = abmService.getEM().createNativeQuery(consulta);
            R = ((Long) query.getSingleResult()).intValue();

        } catch (Exception e) {
        }

        return R;
    }

    private void generaAuditoriaCreate(IAuditable d) {
        d.setFechaRegitro(new Date());
        d.setEstado(Estado.ACTIVO);
        d.setEmpresa(abmService.getCredencial().getEmpresa());
        String usuario = abmService.getCredencial().getUsuario() != null ? abmService.getCredencial().getUsuario().getNombre() + ", " + abmService.getCredencial().getUsuario().getUserName() : "";
        d.setUsuarioUltimaModificacion(usuario);
    }

    private void generaAuditoriaUpdate(IAuditable d) {
        d.setFechaUltimaModificacion(new Date());
        d.setEstado(Estado.ACTIVO);
        d.setEmpresa(abmService.getCredencial().getEmpresa());
        String usuario = abmService.getCredencial().getUsuario() != null ? abmService.getCredencial().getUsuario().getNombre() + ", " + abmService.getCredencial().getUsuario().getUserName() : "";
        d.setUsuarioUltimaModificacion(usuario);
    }

}
