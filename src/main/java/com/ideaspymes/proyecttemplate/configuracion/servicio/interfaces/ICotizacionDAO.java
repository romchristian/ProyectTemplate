/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces;

import com.ideaspymes.proyecttemplate.configuracion.model.Cotizacion;
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
public interface ICotizacionDAO extends AbstractDAO<Cotizacion> {

    @Override
    Cotizacion create(Cotizacion entity);

    @Override
    Cotizacion edit(Cotizacion entity);

    @Override
    Cotizacion find(Object id);

    @Override
    List<Cotizacion> findAll();

    @Override
    List<Cotizacion> findAll(String query, QueryParameter params);

    @Override
    void remove(Cotizacion entity);

    @Override
    List<Cotizacion> completar(String query);

    Cotizacion getCotizacionDefault(Usuario usuario);

}
