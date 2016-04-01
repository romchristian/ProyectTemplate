/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces;

import com.ideaspymes.proyecttemplate.configuracion.model.EstadoCivil;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Acer
 */
@Local
public interface IEstadoCivilDAO extends AbstractDAO<EstadoCivil> {

    @Override
    EstadoCivil create(EstadoCivil entity);

    @Override
    EstadoCivil edit(EstadoCivil entity);

    @Override
    EstadoCivil find(Object id);

    @Override
    List<EstadoCivil> findAll();

    @Override
    List<EstadoCivil> findAll(String query, QueryParameter params);

    @Override
    void remove(EstadoCivil entity);

    @Override
    List<EstadoCivil> completar(String query);

}
