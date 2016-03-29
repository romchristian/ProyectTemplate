/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IMenuItemDAO;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.configuracion.model.MenuItem;
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
public class MenuItemDAO implements IMenuItemDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public MenuItem create(MenuItem entity, Usuario usuario) {
        return abmService.create(entity, usuario);
    }

    @Override
    public MenuItem edit(MenuItem entity, Usuario usuario) {
        return abmService.update(entity, usuario);
    }

    @Override
    public void remove(MenuItem entity, Usuario usuario) {
        abmService.delete(entity, usuario);
    }

    @Override
    public MenuItem find(Object id) {
        return abmService.find(id, MenuItem.class);
    }

    @Override
    public List<MenuItem> findAll() {
        return abmService.getEM().createQuery("select obj from MenuItem obj where obj.estado <> :estado")
                .setParameter("estado", Estado.BORRADO)
                .getResultList();
    }

    @Override
    public List<MenuItem> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

    @Override
    public List<MenuItem> findAll(String query, QueryParameter params, int firt, int pageSize) {
        return abmService.findByQuery(query, params.parameters(), firt, pageSize);
    }

    @Override
    public List<MenuItem> findFilter(String consulta, int first, int pageSize) {
        List<MenuItem> items = new ArrayList<>();
        if (consulta != null) {
            Query query = abmService.getEM().createNativeQuery(consulta, MenuItem.class);
            if (first > 0) {
                query.setFirstResult(first);
            }

            if (pageSize > 0) {
                query.setMaxResults(pageSize);
            }

            items = (List<MenuItem>) query.getResultList();

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
}
