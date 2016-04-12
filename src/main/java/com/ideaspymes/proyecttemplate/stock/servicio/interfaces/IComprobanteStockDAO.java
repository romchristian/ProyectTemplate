/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.interfaces;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.model.ComprobanteStock;
import com.ideaspymes.proyecttemplate.stock.model.LoteExistencia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Acer
 */
@Local
public interface IComprobanteStockDAO extends AbstractDAO<ComprobanteStock> {

    @Override
    ComprobanteStock create(ComprobanteStock entity);

    ComprobanteStock create(ComprobanteStock entity, List<LoteExistencia> lotes);

    @Override
    ComprobanteStock edit(ComprobanteStock entity);

    ComprobanteStock confirmar(ComprobanteStock entity);

    @Override
    ComprobanteStock find(Object id);

    @Override
    List<ComprobanteStock> findAll();

    @Override
    List<ComprobanteStock> findAll(String query, QueryParameter params);

    @Override
    void remove(ComprobanteStock entity);

}
