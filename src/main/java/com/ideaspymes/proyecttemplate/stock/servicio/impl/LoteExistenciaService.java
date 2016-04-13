/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.generico.IAuditable;
import com.ideaspymes.proyecttemplate.stock.enums.EstadoLote;
import com.ideaspymes.proyecttemplate.stock.model.Deposito;
import com.ideaspymes.proyecttemplate.stock.model.DetComprobanteStock;
import com.ideaspymes.proyecttemplate.stock.model.LoteExistencia;
import com.ideaspymes.proyecttemplate.stock.model.MovimientoStockCompra;
import com.ideaspymes.proyecttemplate.stock.model.MovimientoStockVenta;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.model.ProductoUnidadMedida;
import com.ideaspymes.proyecttemplate.stock.model.UnidadMedida;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.ILoteExistenciaService;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IMovimientoStockDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class LoteExistenciaService implements ILoteExistenciaService {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;
    @EJB
    private IMovimientoStockDAO movimientoStockDAO;

    @Override
    public void creaLoteExistencia(DetComprobanteStock d) {

        LoteExistencia l = new LoteExistencia();
        
 

        l.setEstadoLote(EstadoLote.ABIERTO);
        l.setComprobanteStock(d.getComprobanteStock());
        l.setRefFactura("FacturaProveedor:0");
        l.setRefProveedor("Proveedor:0");
        l.setIngreso(d.getComprobanteStock().getFecha());
        l.setDeposito(d.getComprobanteStock().getDestino());
        l.setProducto(d.getProducto());
        l.setUnidadMedida(d.getUnidadMedida());
        l.setCantidadIngresada(d.getCantidad());
        l.setCantidadUsadaStock(0d);
        l.setCantidadReservadaStock(0d);
        l.setCantidadSaldoStock(d.getCantidad());
        l.setCosto(d.getTotal());

        l.setElaboracion(d.getElaboracion());
        l.setVencimiento(d.getVencimiento());

        l = creaLoteExitencia(l);

        MovimientoStockCompra m = new MovimientoStockCompra();
        m.setComprobanteStock(d.getComprobanteStock());
        m.setLoteExistencia(l);

        Deposito dp = d.getComprobanteStock().getDestino();
        generaAuditoriaCreate(d);

        if (dp != null) {
            m.setDeposito(dp);
        }
        m.setFecha(new Date());
        m.setProducto(d.getProducto());
        m.setUnidadMedida(d.getUnidadMedida());
        m.setCantidad(d.getCantidad());

        
        
        movimientoStockDAO.creaMovimientoStock(m);

    }

    @Override
    public LoteExistencia creaLoteExitencia(LoteExistencia l) {
        generaAuditoriaCreate(l);
        abmService.getEM().persist(l);
        abmService.getEM().flush();
        abmService.getEM().refresh(l);

        return l;
    }

    @Override
    public LoteExistencia guardaLoteExitencia(LoteExistencia l) {
        generaAuditoriaUpdate(l);
        l = abmService.getEM().merge(l);
        abmService.getEM().flush();
        abmService.getEM().refresh(l);

        return l;
    }

    @Override
    public void afectaLotesExistenciaMasCovenientes(DetComprobanteStock d) {
        List<LoteExistencia> lotesAAfectar = null;

        if (d.getProducto().getTieneVencimiento()) {

            lotesAAfectar = getLotesExitenciaVencimientosMasProximos(d.getProducto(), d.getUnidadMedida(), d.getCantidad());

        } else {

            lotesAAfectar = getLotesExitenciaMasAntiguos(d.getProducto(), d.getUnidadMedida(), d.getCantidad());

        }
        double cantidadPorMovimiento = d.getCantidad();

        for (LoteExistencia l : lotesAAfectar) {

            if (cantidadPorMovimiento > 0) {

                Deposito dp = d.getComprobanteStock().getOrigen();
                generaAuditoriaUpdate(d);

                MovimientoStockVenta m = new MovimientoStockVenta();
                m.setComprobanteStock(d.getComprobanteStock());
                System.out.println("Referencia en CreaMovimientoStockVenta: " + d.getComprobanteStock().getRefOrigen());

                if (dp != null) {
                    m.setDeposito(dp);
                }
                m.setFecha(new Date());
                m.setProducto(d.getProducto());
                m.setUnidadMedida(d.getUnidadMedida());

                if (l.getCantidadSaldoStock() > cantidadPorMovimiento) {
                    m.setCantidad(cantidadPorMovimiento);
                    cantidadPorMovimiento = afectaCantidadUsadaLoteExitencia(l, cantidadPorMovimiento);
                } else {
                    m.setCantidad(l.getCantidadSaldoStock());
                    cantidadPorMovimiento = afectaCantidadUsadaLoteExitencia(l, cantidadPorMovimiento);
                }

                m.setLoteExistencia(l);
                movimientoStockDAO.creaMovimientoStock(m);

            }
        }
    }

    @Override
    public void afectaLotesExistenciaMasCovenientes(Producto p, UnidadMedida um, Double cantidad) {
        List<LoteExistencia> lotesAAfectar = null;

        if (p.getTieneVencimiento()) {
            lotesAAfectar = getLotesExitenciaVencimientosMasProximos(p, um, cantidad);
        } else {
            lotesAAfectar = getLotesExitenciaMasAntiguos(p, um, cantidad);
        }
        double saldo = cantidad;
        for (LoteExistencia l : lotesAAfectar) {
            if (saldo > 0) {
                saldo = afectaCantidadUsadaLoteExitencia(l, saldo);
            }
        }
    }

    @Override
    public Double afectaCantidadUsadaLoteExitencia(LoteExistencia l, Double cantidad) {
        Double R;

        if (l.getCantidadSaldoStock() > cantidad) {
            l.setCantidadUsadaStock(l.getCantidadUsadaStock() + cantidad);
            l.setCantidadSaldoStock(l.getCantidadSaldoStock() - cantidad);
            R = cantidad;

        } else {
            R = cantidad - l.getCantidadSaldoStock();
            l.setCantidadUsadaStock(l.getCantidadIngresada());
            l.setCantidadSaldoStock(0d);

        }

        if (l.getCantidadSaldoStock() == 0) {
            l.setEstadoLote(EstadoLote.CERRADO);
        }

        generaAuditoriaUpdate(l);
        abmService.getEM().merge(l);
        return R;
    }

    @Override
    public List<LoteExistencia> getLotesExitenciaMasAntiguos(Producto p, UnidadMedida um, Double cantidad) {

        List<LoteExistencia> lotesDisponibles = abmService.getEM().createQuery("SELECT l from LoteExistencia l WHERE l.estadoLote = ?1 and l.producto = ?2 and l.unidadMedida = ?3 ORDER BY l.ingreso DESC")
                .setParameter(1, EstadoLote.ABIERTO)
                .setParameter(2, p)
                .setParameter(3, um)
                .getResultList();

        List<LoteExistencia> R = new ArrayList<>();

        double saldo = cantidad;
        for (LoteExistencia l : lotesDisponibles) {
            if ((l.getCantidadSaldoStock() - cantidad) >= 0) {
                R.add(l);
                break;
            } else {
                R.add(l);
                saldo = (l.getCantidadSaldoStock() - saldo) * 1; // esto tendria que ser negativo entonces multiplico por -1, para que en la siguiente iteracion no me sume
            }
        }

        return R;
    }

    @Override
    public List<LoteExistencia> getLotesExitenciaMasRecientes(Producto p, UnidadMedida um, Double cantidad) {
        List<LoteExistencia> lotesDisponibles = abmService.getEM().createQuery("SELECT l from LoteExistencia l WHERE l.estadoLote = ?1 and l.producto = ?2 and l.unidadMedida = ?3 ORDER BY l.ingreso")
                .setParameter(1, EstadoLote.ABIERTO)
                .setParameter(2, p)
                .setParameter(3, um)
                .getResultList();

        List<LoteExistencia> R = new ArrayList<>();

        double saldo = cantidad;
        for (LoteExistencia l : lotesDisponibles) {
            if ((l.getCantidadSaldoStock() - cantidad) >= 0) {
                R.add(l);
                break;
            } else {
                R.add(l);
                saldo = (l.getCantidadSaldoStock() - saldo) * 1; // esto tendria que ser negativo entonces multiplico por -1, para que en la siguiente iteracion no me sume
            }
        }

        return R;
    }

    @Override
    public List<LoteExistencia> getLotesExitenciaVencimientosMasProximos(Producto p, UnidadMedida um, Double cantidad) {
        List<LoteExistencia> lotesDisponibles = abmService.getEM().createQuery("SELECT l from LoteExistencia l WHERE l.estadoLote = ?1 and l.producto = ?2 and l.unidadMedida = ?3 ORDER BY l.vencimiento")
                .setParameter(1, EstadoLote.ABIERTO)
                .setParameter(2, p)
                .setParameter(3, um)
                .getResultList();

        List<LoteExistencia> R = new ArrayList<>();

        double saldo = cantidad;
        for (LoteExistencia l : lotesDisponibles) {
            if ((l.getCantidadSaldoStock() - cantidad) >= 0) {
                R.add(l);
                break;
            } else {
                R.add(l);
                saldo = (l.getCantidadSaldoStock() - saldo) * 1; // esto tendria que ser negativo entonces multiplico por -1, para que en la siguiente iteracion no me sume
            }
        }

        return R;
    }

    @Override
    public Double getCostoPonderado(Producto p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double getCostoLifo(Producto p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double getCostoFifo(Producto p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        d.setEmpresa(abmService.getCredencial().getEmpresa());
        String usuario = abmService.getCredencial().getUsuario() != null ? abmService.getCredencial().getUsuario().getNombre() + ", " + abmService.getCredencial().getUsuario().getUserName() : "";
        d.setUsuarioUltimaModificacion(usuario);
    }
    
    
    
}
