/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.interfaces;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.model.TipoComprobanteStock;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Acer
 */
@Local
public interface ITipoComprobanteStockDAO extends AbstractDAO<TipoComprobanteStock> {

    @Override
    TipoComprobanteStock create(TipoComprobanteStock entity);

    @Override
    TipoComprobanteStock edit(TipoComprobanteStock entity);

    @Override
    TipoComprobanteStock find(Object id);

    @Override
    List<TipoComprobanteStock> findAll();

    @Override
    List<TipoComprobanteStock> findAll(String query, QueryParameter params);

    @Override
    void remove(TipoComprobanteStock entity);

    TipoComprobanteStock findPorNombre(String nombre);

}
