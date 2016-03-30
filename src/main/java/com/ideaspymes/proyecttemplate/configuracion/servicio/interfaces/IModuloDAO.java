/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces;

import com.ideaspymes.proyecttemplate.configuracion.model.Modulo;
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
public interface IModuloDAO extends AbstractDAO<Modulo> {

    @Override
    Modulo create(Modulo entity, Usuario usuario);

    @Override
    Modulo edit(Modulo entity, Usuario usuario);

    @Override
    Modulo find(Object id);

    @Override
    List<Modulo> findAll();

    @Override
    List<Modulo> findAll(String query, QueryParameter params);

    @Override
    void remove(Modulo entity, Usuario usuario);

    @Override
    List<Modulo> completar(String query);

}
