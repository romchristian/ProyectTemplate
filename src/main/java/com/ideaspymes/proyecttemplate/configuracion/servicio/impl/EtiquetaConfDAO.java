/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IEtiquetaConfDAO;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.configuracion.model.EtiquetaConf;
import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

/*
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class EtiquetaConfDAO implements IEtiquetaConfDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public EtiquetaConf create(EtiquetaConf entity) {
        return abmService.create(entity);
    }

    @Override
    public EtiquetaConf edit(EtiquetaConf entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(EtiquetaConf entity) {
        abmService.delete(entity);
    }

    @Override
    public EtiquetaConf find(Object id) {
        return abmService.find(id, EtiquetaConf.class);
    }

    @Override
    public List<EtiquetaConf> findAll() {
        return abmService.getEM().createQuery("select obj from EtiquetaConf obj where obj.estado = :estado")
                .setParameter("estado", Estado.ACTIVO)
                .getResultList();
    }

    @Override
    public List<EtiquetaConf> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

    @Override
    public List<EtiquetaConf> findAll(String query, QueryParameter params, int firt, int pageSize) {
        return abmService.findByQuery(query, params.parameters(), firt, pageSize);
    }

    @Override
    public List<EtiquetaConf> findFilter(String consulta, int first, int pageSize) {
        List<EtiquetaConf> items = new ArrayList<>();
        if (consulta != null) {
            System.out.println("Consulta: " + consulta);
            Query query = abmService.getEM().createNativeQuery(consulta, EtiquetaConf.class);
            if (first > 0) {
                query.setFirstResult(first);
            }

            if (pageSize > 0) {
                query.setMaxResults(pageSize);
            }

            items = (List<EtiquetaConf>) query.getResultList();

        }
        return items;
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

    @Override
    public List<EtiquetaConf> completar(String matchText) {
        List<EtiquetaConf> sugerencias = new ArrayList<>();

        if (matchText != null && matchText.length() > 0) {
            String consulta = "select * from etiquetaconf where estado = 'ACTIVO' and upper(nombre) like '%" + matchText.toUpperCase().trim() + "%' order by nombre";
            Query query = abmService.getEM().createNativeQuery(consulta, EtiquetaConf.class);
            query.setMaxResults(20);
            sugerencias = query.getResultList();
        }

        return sugerencias;
    }

    @Override
    public EtiquetaConf getEtiquetaConfDefault() {
        EtiquetaConf R = null;
        try {
            R = (EtiquetaConf) abmService.getEM().createQuery("SELECT e FROM EtiquetaConf e WHERE e.predeterminado = ?1")
                    .setParameter(1, true)
                    .getSingleResult();
        } catch (Exception e) {
        }
        return R;
    }

}
