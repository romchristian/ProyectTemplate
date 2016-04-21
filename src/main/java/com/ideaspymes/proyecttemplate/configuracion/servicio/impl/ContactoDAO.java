/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IContactoDAO;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.configuracion.model.Contacto;
import com.ideaspymes.proyecttemplate.configuracion.model.Usuario;
import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
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
public class ContactoDAO implements IContactoDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Contacto create(Contacto entity) {
        return abmService.create(entity);
    }

    @Override
    public Contacto edit(Contacto entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Contacto entity) {
        abmService.delete(entity);
    }

    @Override
    public Contacto find(Object id) {
        return abmService.find(id, Contacto.class);
    }

    @Override
    public List<Contacto> findAll() {
        return abmService.getEM().createQuery("select obj from Contacto obj where obj.estado = :estado")
                .setParameter("estado", Estado.ACTIVO)
                .getResultList();
    }

    @Override
    public List<Contacto> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

    @Override
    public List<Contacto> findAll(String query, QueryParameter params, int firt, int pageSize) {
        return abmService.findByQuery(query, params.parameters(), firt, pageSize);
    }

    @Override
    public List<Contacto> findFilter(String consulta, int first, int pageSize) {
        List<Contacto> items = new ArrayList<>();
        if (consulta != null) {
            System.out.println("Consulta: " + consulta);
            Query query = abmService.getEM().createNativeQuery(consulta, Contacto.class);
            if (first > 0) {
                query.setFirstResult(first);
            }

            if (pageSize > 0) {
                query.setMaxResults(pageSize);
            }

            items = (List<Contacto>) query.getResultList();

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
    public List<Contacto> completar(String matchText) {
        List<Contacto> sugerencias = new ArrayList<>();

        if (matchText != null && matchText.length() > 0) {
            String consulta = "select * from contacto where estado = 'ACTIVO' and upper(nombre) like '%" + matchText.toUpperCase().trim() + "%' order by nombre";
            Query query = abmService.getEM().createNativeQuery(consulta, Contacto.class);
            query.setMaxResults(AbstractDAO.AUTOCOMPLETE_MAX_RESULS);
            sugerencias = query.getResultList();
        }

        return sugerencias;
    }

}
