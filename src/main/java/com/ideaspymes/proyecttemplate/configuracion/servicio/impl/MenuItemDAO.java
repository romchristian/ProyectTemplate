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
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

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

}
