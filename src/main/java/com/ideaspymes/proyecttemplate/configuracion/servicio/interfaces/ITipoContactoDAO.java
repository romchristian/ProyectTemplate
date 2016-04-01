/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces;

import com.ideaspymes.proyecttemplate.configuracion.model.TipoContacto;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Acer
 */
@Local
public interface ITipoContactoDAO extends AbstractDAO<TipoContacto> {

    @Override
    TipoContacto create(TipoContacto entity);

    @Override
    TipoContacto edit(TipoContacto entity);

    @Override
    TipoContacto find(Object id);

    @Override
    List<TipoContacto> findAll();

    @Override
    List<TipoContacto> findAll(String query, QueryParameter params);

    @Override
    void remove(TipoContacto entity);

    @Override
    List<TipoContacto> completar(String query);

}
