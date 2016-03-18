/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.ISubMenuDAO;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.configuracion.model.SubMenu;
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
public class SubMenuDAO implements ISubMenuDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public SubMenu create(SubMenu entity, Usuario usuario) {
        return abmService.create(entity, usuario);
    }

    @Override
    public SubMenu edit(SubMenu entity, Usuario usuario) {
        return abmService.update(entity, usuario);
    }

    @Override
    public void remove(SubMenu entity, Usuario usuario) {
        abmService.delete(entity, usuario);
    }

    @Override
    public SubMenu find(Object id) {
        return abmService.find(id, SubMenu.class);
    }

    @Override
    public List<SubMenu> findAll() {
        return abmService.getEM().createQuery("select obj from SubMenu obj where obj.estado <> :estado")
                .setParameter("estado", Estado.BORRADO)
                .getResultList();
    }

    @Override
    public List<SubMenu> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

}
