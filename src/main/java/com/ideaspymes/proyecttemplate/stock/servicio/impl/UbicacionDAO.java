/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.model.Ubicacion;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IUbicacionDAO;
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
public class UbicacionDAO implements IUbicacionDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Ubicacion create(Ubicacion entity) {
        return abmService.create(entity);
    }

    @Override
    public Ubicacion edit(Ubicacion entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Ubicacion entity) {
        abmService.delete(entity);
    }

    @Override
    public Ubicacion find(Object id) {
        return abmService.find(id, Ubicacion.class);
    }
    
 

    @Override
    public List<Ubicacion> findAll() {
        return abmService.getEM().createQuery("select obj from Ubicacion obj WHERE OBJ.estado = ?1")
                .setParameter(1, Estado.ACTIVO)
                .getResultList();
    }

    @Override
    public List<Ubicacion> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

 
    @Override
    public List<Ubicacion> findAll(String query, QueryParameter params, int first, int pageSize) {
        return abmService.findByQuery(query, params.parameters(), first, pageSize);
    }

    @Override
    public List<Ubicacion> findFilter(String consulta, int first, int pageSize) {
        List<Ubicacion> items = new ArrayList<>();
        if (consulta != null) {
            System.out.println("Consulta: " + consulta);
            Query query = abmService.getEM().createNativeQuery(consulta, Ubicacion.class);
            if (first > 0) {
                query.setFirstResult(first);
            }

            if (pageSize > 0) {
                query.setMaxResults(pageSize);
            }

            items = (List<Ubicacion>) query.getResultList();

        }
        return items;
    }

    @Override
    public List<Ubicacion> completar(String matchText) {
        List<Ubicacion> sugerencias = new ArrayList<>();

        if (matchText != null && matchText.length() > 0) {
            String consulta = "select * from ubicacion where estado = 'ACTIVO' and upper(nombre) like '%" + matchText.toUpperCase().trim() + "%' order by nombre";
            Query query = abmService.getEM().createNativeQuery(consulta, Ubicacion.class);
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
