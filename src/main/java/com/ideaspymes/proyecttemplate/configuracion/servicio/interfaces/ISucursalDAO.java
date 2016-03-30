/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces;

import com.ideaspymes.proyecttemplate.configuracion.model.Empresa;
import com.ideaspymes.proyecttemplate.configuracion.model.Sucursal;
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
public interface ISucursalDAO extends AbstractDAO<Sucursal> {

    @Override
    Sucursal create(Sucursal entity, Usuario usuario);

    @Override
    Sucursal edit(Sucursal entity, Usuario usuario);

    @Override
    Sucursal find(Object id);

    @Override
    List<Sucursal> findAll();

    @Override
    List<Sucursal> findAll(String query, QueryParameter params);

    @Override
    void remove(Sucursal entity, Usuario usuario);

    @Override
    List<Sucursal> completar(String query);
}
