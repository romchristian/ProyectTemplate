/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces;

import com.ideaspymes.proyecttemplate.configuracion.model.Contacto;
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
public interface IContactoDAO extends AbstractDAO<Contacto> {

    @Override
    Contacto create(Contacto entity, Usuario usuario);

    @Override
    Contacto edit(Contacto entity, Usuario usuario);

    @Override
    Contacto find(Object id);

    @Override
    List<Contacto> findAll();

    @Override
    List<Contacto> findAll(String query, QueryParameter params);

    @Override
    void remove(Contacto entity, Usuario usuario);

    @Override
    List<Contacto> completar(String query);

}
