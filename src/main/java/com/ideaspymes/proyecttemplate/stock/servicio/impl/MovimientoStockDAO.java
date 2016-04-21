/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.model.MovimientoStock;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IMovimientoStockDAO;
import java.util.ArrayList;
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
public class MovimientoStockDAO implements IMovimientoStockDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public MovimientoStock create(MovimientoStock entity) {
        return abmService.create(entity);
    }

    @Override
    public MovimientoStock edit(MovimientoStock entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(MovimientoStock entity) {
        abmService.delete(entity);
    }

    @Override
    public MovimientoStock find(Object id) {
        return abmService.find(id, MovimientoStock.class);
    }

    @Override
    public List<MovimientoStock> findAll() {
        return abmService.getEM().createQuery("select obj from MovimientoStock obj WHERE OBJ.estado = ?1")
                .setParameter(1, Estado.ACTIVO)
                .getResultList();
    }

    @Override
    public List<MovimientoStock> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

    @Override
    public List<MovimientoStock> findAll(String query, QueryParameter params, int first, int pageSize) {
        return abmService.findByQuery(query, params.parameters(), first, pageSize);
    }

    @Override
    public List<MovimientoStock> findFilter(String consulta, int first, int pageSize) {
        List<MovimientoStock> items = new ArrayList<>();
        if (consulta != null) {
            System.out.println("Consulta: " + consulta);
            Query query = abmService.getEM().createNativeQuery(consulta, MovimientoStock.class);
            if (first > 0) {
                query.setFirstResult(first);
            }

            if (pageSize > 0) {
                query.setMaxResults(pageSize);
            }

            items = (List<MovimientoStock>) query.getResultList();

        }
        return items;
    }

    @Override
    public List<MovimientoStock> completar(String matchText) {
        List<MovimientoStock> sugerencias = new ArrayList<>();

        if (matchText != null && matchText.length() > 0) {
            String consulta = "select * from movimientostock where estado = 'ACTIVO' and upper(nombre) like '%" + matchText.toUpperCase().trim() + "%' order by nombre";
            Query query = abmService.getEM().createNativeQuery(consulta, MovimientoStock.class);
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
