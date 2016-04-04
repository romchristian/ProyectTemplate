/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.impl;

import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.model.Familia;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IFamiliaDAO;
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
public class FamiliaDAO implements IFamiliaDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Familia create(Familia entity) {
        return abmService.create(entity);
    }

    @Override
    public Familia edit(Familia entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Familia entity) {
        abmService.delete(entity);
    }

    @Override
    public Familia find(Object id) {
        return abmService.find(id, Familia.class);
    }

    @Override
    public List<Familia> findAll() {
        return abmService.getEM().createQuery("select obj from Familia obj").getResultList();
    }

    @Override
    public List<Familia> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

   @Override
    public List<Familia> findAll(String query, QueryParameter params, int first, int pageSize) {
        return abmService.findByQuery(query, params.parameters(), first, pageSize);
    }

    @Override
    public List<Familia> findFilter(String consulta, int first, int pageSize) {
        List<Familia> items = new ArrayList<>();
        if (consulta != null) {
            System.out.println("Consulta: " + consulta);
            Query query = abmService.getEM().createNativeQuery(consulta, Familia.class);
            if (first > 0) {
                query.setFirstResult(first);
            }

            if (pageSize > 0) {
                query.setMaxResults(pageSize);
            }

            items = (List<Familia>) query.getResultList();

        }
        return items;
    }

    @Override
    public List<Familia> completar(String matchText) {
        List<Familia> sugerencias = new ArrayList<>();

        if (matchText != null && matchText.length() > 0) {
            String consulta = "select * from familia where estado = 'ACTIVO' and upper(nombre) like '%" + matchText.toUpperCase().trim() + "%' order by nombre";
            Query query = abmService.getEM().createNativeQuery(consulta, Familia.class);
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


}
