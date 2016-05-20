/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.interfaces;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.model.Familia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Acer
 */
@Local
public interface IFamiliaDAO extends AbstractDAO<Familia> {

    @Override
    Familia create(Familia entity);

    @Override
    Familia edit(Familia entity);

    @Override
    Familia find(Object id);

    @Override
    List<Familia> findAll();

    @Override
    List<Familia> findAll(String query, QueryParameter params);

    List<Familia> findHijos(Familia f);

    List<Familia> findSinPadre();

    @Override
    void remove(Familia entity);

}
