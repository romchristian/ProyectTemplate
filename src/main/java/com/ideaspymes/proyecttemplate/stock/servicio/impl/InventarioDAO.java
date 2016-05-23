/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.exception.SinStockException;
import com.ideaspymes.proyecttemplate.stock.model.ComprobanteStock;
import com.ideaspymes.proyecttemplate.stock.model.Deposito;
import com.ideaspymes.proyecttemplate.stock.model.DetComprobanteStock;
import com.ideaspymes.proyecttemplate.stock.model.DetInventario;
import com.ideaspymes.proyecttemplate.stock.model.Existencia;
import com.ideaspymes.proyecttemplate.stock.model.Inventario;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.model.TipoComprobanteStock;
import com.ideaspymes.proyecttemplate.stock.model.Ubicacion;
import com.ideaspymes.proyecttemplate.stock.model.UnidadMedida;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IComprobanteStockDAO;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IInventarioDAO;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.ILoteExistenciaDAO;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IProductoDAO;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IProductoUnidadMedidaDAO;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.ITipoComprobanteStockDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class InventarioDAO implements IInventarioDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @EJB
    private IComprobanteStockDAO ejbComprobanteStockDAO;
    @EJB
    private ITipoComprobanteStockDAO ejbITipoComprobanteStockDAO;
    @EJB
    private IProductoUnidadMedidaDAO productoUnidadMedidaDAO;
    @EJB
    private IProductoDAO productoDAO;

    @Override
    public Inventario create(Inventario entity) {
        return abmService.create(entity);
    }

    @Override
    public Inventario createInicial(Inventario entity) {
        Inventario R = create(entity);
        try {

            ComprobanteStock c = new ComprobanteStock();
            if (entity.getTipoComprobanteStock() != null) {
                c.setTipoComprobanteStock(entity.getTipoComprobanteStock());
            } else {
                c.setTipoComprobanteStock(ejbITipoComprobanteStockDAO.findPorNombre("Entrada por Ajuste"));
            }

            c.setDepositoPivot(R.getDeposito());
            c.setUbicacionPivot(R.getUbicacion());
            c.setResposable(R.getResponsable());
            c.setFecha(R.getFecha());
            c.setDescripcion("Inventario Inicial Nro.: " + R.getId());
            c.setDetalles(new ArrayList<DetComprobanteStock>());
            int indice = 0;

            for (DetInventario di : R.getDetalles()) {
                indice++;
                DetComprobanteStock d = new DetComprobanteStock();
                d.setComprobanteStock(c);
                d.setIndice(indice);
                d.setProducto(di.getProducto());
                d.setCantidad(di.getCantidad());
                d.setUnidadMedida(di.getUnidadMedida());
                d.setEstado(Estado.ACTIVO);
                d.setValor(0d);
                d.setTotal(0d);
                c.getDetalles().add(d);
            }

            ComprobanteStock comp = ejbComprobanteStockDAO.create(c);

            ejbComprobanteStockDAO.confirmar(comp);

        } catch (SinStockException ex) {
            throw new RuntimeException(ex);
        }

        return R;
    }

    @Override
    public Boolean actualizaInventario(Deposito d, Ubicacion u, UnidadMedida um, Producto p, Double cantidad) {

        Boolean R = false;

        try {

            Existencia e = productoDAO.findExistenciasPorProductoUbicacion(p, d, u);

            Double cantidadActual = 0d;

            if (e != null) {
                cantidadActual = e.getCantidad();
            }

            Double cantidadNecesaria = productoUnidadMedidaDAO.calculaCantidadUMStock(p, um, cantidad) - cantidadActual;
            if (cantidadNecesaria == 0) {
                //No hago nada
                return R;
            }

            TipoComprobanteStock tipo;
            if (cantidadNecesaria > 0) {
                tipo = ejbITipoComprobanteStockDAO.findPorNombre("Entrada por Ajuste");
            } else {
                tipo = ejbITipoComprobanteStockDAO.findPorNombre("Salida por Ajuste");
                cantidadNecesaria = cantidadNecesaria * -1;
            }

            ComprobanteStock c = new ComprobanteStock();
            c.setTipoComprobanteStock(tipo);
            c.setDepositoPivot(d);
            c.setUbicacionPivot(u);
            c.setResposable(abmService.getCredencial().getUsuario());
            c.setFecha(new Date());
            c.setDescripcion("Actualización Manual de Stock");
            c.setDetalles(new ArrayList<DetComprobanteStock>());

            DetComprobanteStock det = new DetComprobanteStock();
            det.setComprobanteStock(c);
            det.setIndice(1);
            det.setProducto(p);
            det.setCantidad(cantidadNecesaria);
            det.setUnidadMedida(um);
            det.setEstado(Estado.ACTIVO);
            det.setValor(0d);
            det.setTotal(0d);
            c.getDetalles().add(det);

            ComprobanteStock comp = ejbComprobanteStockDAO.create(c);

            ejbComprobanteStockDAO.confirmar(comp);

            R = true;

        } catch (SinStockException ex) {
            throw new RuntimeException(ex);
        }

        return R;
    }

    @Override
    public Inventario edit(Inventario entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Inventario entity) {
        abmService.delete(entity);
    }

    @Override
    public Inventario find(Object id) {
        return abmService.find(id, Inventario.class);
    }

    @Override
    public List<Inventario> findAll() {
        return abmService.getEM().createQuery("select obj from Inventario obj WHERE OBJ.estado = ?1")
                .setParameter(1, Estado.ACTIVO)
                .getResultList();
    }

    @Override
    public List<Inventario> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

    @Override
    public List<Inventario> findAll(String query, QueryParameter params, int first, int pageSize) {
        return abmService.findByQuery(query, params.parameters(), first, pageSize);
    }

    @Override
    public List<Inventario> findFilter(String consulta, int first, int pageSize) {
        List<Inventario> items = new ArrayList<>();
        if (consulta != null) {
            System.out.println("Consulta: " + consulta);
            Query query = abmService.getEM().createNativeQuery(consulta, Inventario.class);
            if (first > 0) {
                query.setFirstResult(first);
            }

            if (pageSize > 0) {
                query.setMaxResults(pageSize);
            }

            items = (List<Inventario>) query.getResultList();

        }
        return items;
    }

    @Override
    public List<Inventario> completar(String matchText) {
        List<Inventario> sugerencias = new ArrayList<>();

        if (matchText != null && matchText.length() > 0) {
            String consulta = "select * from inventario where estado = 'ACTIVO' and upper(nombre) like '%" + matchText.toUpperCase().trim() + "%' order by nombre";
            Query query = abmService.getEM().createNativeQuery(consulta, Inventario.class);
            query.setMaxResults(AbstractDAO.AUTOCOMPLETE_MAX_RESULS);
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

}
