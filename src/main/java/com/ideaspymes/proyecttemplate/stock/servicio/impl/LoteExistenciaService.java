/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.impl;

import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.stock.enums.EstadoLote;
import com.ideaspymes.proyecttemplate.stock.model.LoteExistencia;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.model.UnidadMedida;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.ILoteExistenciaService;
import java.util.ArrayList;
import java.util.List;
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
public class LoteExistenciaService implements ILoteExistenciaService {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public LoteExistencia creaLoteExitencia(LoteExistencia l) {
        abmService.getEM().persist(l);
        abmService.getEM().flush();
        abmService.getEM().refresh(l);

        return l;
    }

    @Override
    public LoteExistencia guardaLoteExitencia(LoteExistencia l) {
        l = abmService.getEM().merge(l);
        abmService.getEM().flush();
        abmService.getEM().refresh(l);

        return l;
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
        double saldo = l.getCantidadUsada() - cantidad;
        if (saldo < 0) {
            l.setCantidadUsada(l.getCantidadIngresada());
            l.setCantidadSaldo(0d);
            R = saldo * -1;
        } else {
            l.setCantidadUsada(l.getCantidadUsada() - cantidad);
            l.setCantidadSaldo(l.getCantidadIngresada() - l.getCantidadUsada());
            R = 0d;
        }

        if (l.getCantidadSaldo() == 0) {
            l.setEstadoLote(EstadoLote.CERRADO);
        }
        abmService.getEM().merge(l);
        return R;
    }

    @Override
    public List<LoteExistencia> getLotesExitenciaMasAntiguos(Producto p, UnidadMedida um, Double cantidad) {

        List<LoteExistencia> lotesDisponibles = abmService.getEM().createQuery("SELECT l from LoteExistencia l WHERE l.estado = ?1 and l.producto = ?2 and l.unidadMedida = ?3 ORDER BY l.ingreso DESC")
                .setParameter(1, EstadoLote.ABIERTO)
                .setParameter(2, p)
                .setParameter(3, um)
                .getResultList();

        List<LoteExistencia> R = new ArrayList<>();

        double saldo = cantidad;
        for (LoteExistencia l : lotesDisponibles) {
            if ((l.getCantidadSaldo() - cantidad) >= 0) {
                R.add(l);
                break;
            } else {
                R.add(l);
                saldo = (l.getCantidadSaldo() - saldo) * 1; // esto tendria que ser negativo entonces multiplico por -1, para que en la siguiente iteracion no me sume
            }
        }

        return R;
    }

    @Override
    public List<LoteExistencia> getLotesExitenciaMasRecientes(Producto p, UnidadMedida um, Double cantidad) {
        List<LoteExistencia> lotesDisponibles = abmService.getEM().createQuery("SELECT l from LoteExistencia l WHERE l.estado = ?1 and l.producto = ?2 and l.unidadMedida = ?3 ORDER BY l.ingreso")
                .setParameter(1, EstadoLote.ABIERTO)
                .setParameter(2, p)
                .setParameter(3, um)
                .getResultList();

        List<LoteExistencia> R = new ArrayList<>();

        double saldo = cantidad;
        for (LoteExistencia l : lotesDisponibles) {
            if ((l.getCantidadSaldo() - cantidad) >= 0) {
                R.add(l);
                break;
            } else {
                R.add(l);
                saldo = (l.getCantidadSaldo() - saldo) * 1; // esto tendria que ser negativo entonces multiplico por -1, para que en la siguiente iteracion no me sume
            }
        }

        return R;
    }

    @Override
    public List<LoteExistencia> getLotesExitenciaVencimientosMasProximos(Producto p, UnidadMedida um, Double cantidad) {
        List<LoteExistencia> lotesDisponibles = abmService.getEM().createQuery("SELECT l from LoteExistencia l WHERE l.estado = ?1 and l.producto = ?2 and l.unidadMedida = ?3 ORDER BY l.vencimiento")
                .setParameter(1, EstadoLote.ABIERTO)
                .setParameter(2, p)
                .setParameter(3, um)
                .getResultList();

        List<LoteExistencia> R = new ArrayList<>();

        double saldo = cantidad;
        for (LoteExistencia l : lotesDisponibles) {
            if ((l.getCantidadSaldo() - cantidad) >= 0) {
                R.add(l);
                break;
            } else {
                R.add(l);
                saldo = (l.getCantidadSaldo() - saldo) * 1; // esto tendria que ser negativo entonces multiplico por -1, para que en la siguiente iteracion no me sume
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

}
