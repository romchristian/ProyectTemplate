/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces;

import com.ideaspymes.proyecttemplate.configuracion.model.Empresa;
import com.ideaspymes.proyecttemplate.configuracion.model.MenuItem;
import com.ideaspymes.proyecttemplate.configuracion.model.Usuario;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Acer
 */
@Local
public interface IMenuItemDAO extends AbstractDAO<MenuItem> {

    @Override
    MenuItem create(MenuItem entity, Usuario usuario);

    @Override
    MenuItem edit(MenuItem entity, Usuario usuario);

    @Override
    MenuItem find(Object id);

    @Override
    List<MenuItem> findAll();

    @Override
    List<MenuItem> findAll(String query, QueryParameter params);

    @Override
    void remove(MenuItem entity, Usuario usuario);
    
    @Override
    List<MenuItem> completar(String query);

}
