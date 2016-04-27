/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.interfaces;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.model.Ubicacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Acer
 */
@Local
public interface IUbicacionDAO extends AbstractDAO<Ubicacion> {

    @Override
    Ubicacion create(Ubicacion entity);

    @Override
    Ubicacion edit(Ubicacion entity);

    @Override
    Ubicacion find(Object id);

    @Override
    List<Ubicacion> findAll();

    @Override
    List<Ubicacion> findAll(String query, QueryParameter params);

    @Override
    void remove(Ubicacion entity);

}
