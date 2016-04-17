/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.generico.IAuditable;
import com.ideaspymes.proyecttemplate.stock.model.Deposito;
import com.ideaspymes.proyecttemplate.stock.model.Existencia;
import com.ideaspymes.proyecttemplate.stock.model.MovimientoStock;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.model.UnidadMedida;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IMovimientoStockDAO;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class MovimientoStockDAO implements IMovimientoStockDAO {

    @EJB
    private ABMService abms;

    @Override
    public void creaMovimientoStock(MovimientoStock m) {
        System.out.println("Invoque creaMovimiento");
        generaAuditoria(m);
        
        Producto p = m.getProducto();
        abms.getEM().merge(m);
        System.out.println("Invoque creaMovimiento");
        afectaStockExistencia(m.getDeposito(), p, m.cantidadAAfectar(), p.getUnidadMedidaBase());
    }

    private void afectaStockExistencia(Deposito d, Producto p, Double cantidadAAfectar, UnidadMedida um) {
        System.out.println("Invoque afectaExistencia :  Deposito: " + d + " Producto : " + p + " UnidadMedida: " + um + " Cantidad : " + cantidadAAfectar);
        if (p != null && d != null && cantidadAAfectar != null && um != null) {
            Existencia e = null;
            System.out.println("Estoy dentro");
            try {
                e = (Existencia) abms.getEM().createQuery("Select e from Existencia e WHERE e.deposito= :deposito and e.producto= :producto and e.unidadMedida= :unidadmedida")
                        .setParameter("producto", p)
                        .setParameter("deposito", d)
                        .setParameter("unidadmedida", um)
                        .getSingleResult();

            } catch (Exception ex) {
                System.out.println("No se encontro existencia: " + ex.getMessage());
            }

            if (e == null) {
                e = new Existencia();
                e.setDeposito(d);
                e.setProducto(p);
                e.setCantidad(cantidadAAfectar);
                e.setUnidadMedida(um);
                generaAuditoria(e);
                System.out.println("No Existe e : " + e.getCantidad());
            } else {

                Double existenciaActual = e.getCantidad() == null ? 0d : e.getCantidad();
                e.setCantidad(existenciaActual + cantidadAAfectar);
                System.out.println("Existe e : " + e.getCantidad());
                generaAuditoria(e);
            }

            abms.getEM().merge(e);
            System.out.println("Hizo el merge: " + e.getCantidad());

            afectaStock(p, um);

        }
    }

    private void afectaStock(Producto p, UnidadMedida um) {
        if (p != null && um != null) {
            Double cantidadAcumulada = 0d;
            try {
                cantidadAcumulada = (Double) abms.getEM()
                        .createQuery("Select sum(e.cantidad) from Existencia e where e.producto= :producto and e.unidadMedida= :unidadmedida")
                        .setParameter("producto", p)
                        .setParameter("unidadmedida", um)
                        .getSingleResult();

                p = abms.getEM().find(Producto.class, p.getId());

                p.setStock(cantidadAcumulada);
                generaAuditoria(p);
                abms.getEM().merge(p);

                System.out.println("Afecte stock: " + cantidadAcumulada);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    private void generaAuditoria(IAuditable d) {
        d.setFechaUltimaModificacion(new Date());
        d.setEstado(Estado.ACTIVO);
        d.setEmpresa(abms.getCredencial().getEmpresa());
        String usuario = abms.getCredencial().getUsuario() != null ? abms.getCredencial().getUsuario().getNombre() + ", " + abms.getCredencial().getUsuario().getUserName() : "";
        d.setUsuarioUltimaModificacion(usuario);
    }

}
