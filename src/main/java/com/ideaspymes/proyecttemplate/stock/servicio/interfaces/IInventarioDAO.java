/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.interfaces;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.model.Inventario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Acer
 */
@Local
public interface IInventarioDAO extends AbstractDAO<Inventario> {

    @Override
    Inventario create(Inventario entity);

    @Override
    Inventario edit(Inventario entity);

    @Override
    Inventario find(Object id);

    @Override
    List<Inventario> findAll();

    @Override
    List<Inventario> findAll(String query, QueryParameter params);

    @Override
    void remove(Inventario entity);

}
