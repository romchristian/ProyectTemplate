/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IUsuarioDAO;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
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
public class UsuarioDAO implements IUsuarioDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Usuario create(Usuario entity, Usuario usuario) {
        return abmService.create(entity, usuario);
    }

    @Override
    public Usuario edit(Usuario entity, Usuario usuario) {
        return abmService.update(entity, usuario);
    }

    @Override
    public void remove(Usuario entity, Usuario usuario) {
        abmService.delete(entity, usuario);
    }

    @Override
    public Usuario find(Object id) {
        return abmService.find(id, Usuario.class);
    }

    @Override
    public List<Usuario> findAll() {
        return abmService.getEM().createQuery("select obj from Usuario obj where obj.estado <> :estado")
                .setParameter("estado", Estado.BORRADO)
                .getResultList();
    }

    @Override
    public List<Usuario> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

}
