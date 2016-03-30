/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IEstadoCivilDAO;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.configuracion.model.EstadoCivil;
import com.ideaspymes.proyecttemplate.configuracion.model.Usuario;
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
public class EstadoCivilDAO implements IEstadoCivilDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public EstadoCivil create(EstadoCivil entity, Usuario usuario) {
        return abmService.create(entity, usuario);
    }

    @Override
    public EstadoCivil edit(EstadoCivil entity, Usuario usuario) {
        return abmService.update(entity, usuario);
    }

    @Override
    public void remove(EstadoCivil entity, Usuario usuario) {
        abmService.delete(entity, usuario);
    }

    @Override
    public EstadoCivil find(Object id) {
        return abmService.find(id, EstadoCivil.class);
    }

    @Override
    public List<EstadoCivil> findAll() {
        return abmService.getEM().createQuery("select obj from EstadoCivil obj where obj.estado <> :estado")
                .setParameter("estado", Estado.BORRADO)
                .getResultList();
    }

    @Override
    public List<EstadoCivil> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

    @Override
    public List<EstadoCivil> findAll(String query, QueryParameter params, int firt, int pageSize) {
        return abmService.findByQuery(query, params.parameters(), firt, pageSize);
    }

    @Override
    public List<EstadoCivil> findFilter(String consulta, int first, int pageSize) {
        List<EstadoCivil> items = new ArrayList<>();
        if (consulta != null) {
            System.out.println("Consulta: " + consulta);
            Query query = abmService.getEM().createNativeQuery(consulta, EstadoCivil.class);
            if (first > 0) {
                query.setFirstResult(first);
            }

            if (pageSize > 0) {
                query.setMaxResults(pageSize);
            }

            items = (List<EstadoCivil>) query.getResultList();

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
    public List<EstadoCivil> completar(String matchText) {
        List<EstadoCivil> sugerencias = new ArrayList<>();

        if (matchText != null && matchText.length() > 0) {
            String consulta = "select * from estadocivil where estado = 'ACTIVO' and upper(nombre) like '%" + matchText.toUpperCase().trim() + "%' order by nombre";
            Query query = abmService.getEM().createNativeQuery(consulta, EstadoCivil.class);
            query.setMaxResults(20);
            sugerencias = query.getResultList();
        }
        
        return sugerencias;
    }

}
