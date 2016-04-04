/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.interfaces;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.model.UnidadMedida;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Acer
 */
@Local
public interface IUnidadMedidaDAO extends AbstractDAO<UnidadMedida> {

    @Override
    UnidadMedida create(UnidadMedida entity);

    @Override
    UnidadMedida edit(UnidadMedida entity);

    @Override
    UnidadMedida find(Object id);

    @Override
    List<UnidadMedida> findAll();

    @Override
    List<UnidadMedida> findAll(String query, QueryParameter params);

    @Override
    void remove(UnidadMedida entity);

}
