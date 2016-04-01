/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces;

import com.ideaspymes.proyecttemplate.configuracion.model.Empresa;
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
public interface IEmpresaDAO extends AbstractDAO<Empresa> {

    @Override
    Empresa create(Empresa entity);

    @Override
    Empresa edit(Empresa entity);

    @Override
    Empresa find(Object id);

    @Override
    List<Empresa> findAll();

    @Override
    List<Empresa> findAll(String query, QueryParameter params);

    @Override
    void remove(Empresa entity);

    @Override
    List<Empresa> completar(String query);

    Empresa getEmpresaDefault(Usuario usuario);

}
