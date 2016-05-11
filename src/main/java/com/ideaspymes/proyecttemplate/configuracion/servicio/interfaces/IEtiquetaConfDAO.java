/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces;

import com.ideaspymes.proyecttemplate.configuracion.model.EtiquetaConf;
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
public interface IEtiquetaConfDAO extends AbstractDAO<EtiquetaConf> {

    @Override
    EtiquetaConf create(EtiquetaConf entity);

    @Override
    EtiquetaConf edit(EtiquetaConf entity);

    @Override
    EtiquetaConf find(Object id);

    @Override
    List<EtiquetaConf> findAll();

    @Override
    List<EtiquetaConf> findAll(String query, QueryParameter params);

    @Override
    void remove(EtiquetaConf entity);

    @Override
    List<EtiquetaConf> completar(String query);

    EtiquetaConf getEtiquetaConfDefault();

}
