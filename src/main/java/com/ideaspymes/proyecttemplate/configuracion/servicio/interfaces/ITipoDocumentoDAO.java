/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces;

import com.ideaspymes.proyecttemplate.configuracion.model.TipoDocumento;
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
public interface ITipoDocumentoDAO extends AbstractDAO<TipoDocumento> {

    @Override
    TipoDocumento create(TipoDocumento entity);

    @Override
    TipoDocumento edit(TipoDocumento entity);

    @Override
    TipoDocumento find(Object id);

    @Override
    List<TipoDocumento> findAll();

    @Override
    List<TipoDocumento> findAll(String query, QueryParameter params);

    @Override
    void remove(TipoDocumento entity);

    @Override
    List<TipoDocumento> completar(String query);

}
