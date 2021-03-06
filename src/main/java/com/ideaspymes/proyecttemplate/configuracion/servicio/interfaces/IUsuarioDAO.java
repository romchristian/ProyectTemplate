/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces;

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
public interface IUsuarioDAO extends AbstractDAO<Usuario> {

    @Override
    Usuario create(Usuario entity);

    @Override
    Usuario edit(Usuario entity);

    @Override
    Usuario find(Object id);

    Usuario find(String username);

    @Override
    List<Usuario> findAll();

    @Override
    List<Usuario> findAll(String query, QueryParameter params);

    @Override
    void remove(Usuario entity);

    @Override
    List<Usuario> completar(String query);

}
