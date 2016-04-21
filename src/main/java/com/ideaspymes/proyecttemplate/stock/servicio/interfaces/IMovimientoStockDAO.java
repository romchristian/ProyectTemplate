/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.interfaces;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.model.MovimientoStock;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Acer
 */
@Local
public interface IMovimientoStockDAO extends AbstractDAO<MovimientoStock> {

    @Override
    MovimientoStock create(MovimientoStock entity);

    @Override
    MovimientoStock edit(MovimientoStock entity);

    @Override
    MovimientoStock find(Object id);

    @Override
    List<MovimientoStock> findAll();

    @Override
    List<MovimientoStock> findAll(String query, QueryParameter params);

    @Override
    void remove(MovimientoStock entity);

}
