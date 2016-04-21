/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.model.LoteExistencia;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.ILoteExistenciaDAO;
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
public class LoteExistenciaDAO implements ILoteExistenciaDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public LoteExistencia create(LoteExistencia entity) {
        return abmService.create(entity);
    }

    @Override
    public LoteExistencia edit(LoteExistencia entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(LoteExistencia entity) {
        abmService.delete(entity);
    }

    @Override
    public LoteExistencia find(Object id) {
        return abmService.find(id, LoteExistencia.class);
    }

    @Override
    public List<LoteExistencia> findAll() {
        return abmService.getEM().createQuery("select obj from LoteExistencia obj WHERE OBJ.estado = ?1")
                .setParameter(1, Estado.ACTIVO)
                .getResultList();
    }

    @Override
    public List<LoteExistencia> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

    @Override
    public List<LoteExistencia> findAll(String query, QueryParameter params, int first, int pageSize) {
        return abmService.findByQuery(query, params.parameters(), first, pageSize);
    }

    @Override
    public List<LoteExistencia> findFilter(String consulta, int first, int pageSize) {
        List<LoteExistencia> items = new ArrayList<>();
        if (consulta != null) {
            System.out.println("Consulta: " + consulta);
            Query query = abmService.getEM().createNativeQuery(consulta, LoteExistencia.class);
            if (first > 0) {
                query.setFirstResult(first);
            }

            if (pageSize > 0) {
                query.setMaxResults(pageSize);
            }

            items = (List<LoteExistencia>) query.getResultList();

        }
        return items;
    }

    @Override
    public List<LoteExistencia> completar(String matchText) {
        List<LoteExistencia> sugerencias = new ArrayList<>();

        if (matchText != null && matchText.length() > 0) {
            String consulta = "select * from loteexistencia where estado = 'ACTIVO' and upper(nombre) like '%" + matchText.toUpperCase().trim() + "%' order by nombre";
            Query query = abmService.getEM().createNativeQuery(consulta, LoteExistencia.class);
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
