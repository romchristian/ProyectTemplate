/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.model.Inventario;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IInventarioDAO;
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
public class InventarioDAO implements IInventarioDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Inventario create(Inventario entity) {
        return abmService.create(entity);
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
