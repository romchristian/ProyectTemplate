/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces;

import com.ideaspymes.proyecttemplate.configuracion.model.Moneda;
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
public interface IMonedaDAO extends AbstractDAO<Moneda> {

    @Override
    Moneda create(Moneda entity);

    @Override
    Moneda edit(Moneda entity);

    @Override
    Moneda find(Object id);

    @Override
    List<Moneda> findAll();

    @Override
    List<Moneda> findAll(String query, QueryParameter params);

    @Override
    void remove(Moneda entity);

    @Override
    List<Moneda> completar(String query);

    Moneda getMonedaDefault(Usuario usuario);

}
