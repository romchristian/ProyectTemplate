/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces;

import com.ideaspymes.proyecttemplate.configuracion.model.Grupo;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Acer
 */
@Local
public interface IGrupoDAO extends AbstractDAO<Grupo> {

    @Override
    Grupo create(Grupo entity);

    @Override
    Grupo edit(Grupo entity);

    @Override
    Grupo find(Object id);

    Grupo find(String username);

    @Override
    List<Grupo> findAll();

    @Override
    List<Grupo> findAll(String query, QueryParameter params);

    @Override
    void remove(Grupo entity);

    @Override
    List<Grupo> completar(String query);

}
