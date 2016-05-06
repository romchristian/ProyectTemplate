/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.web;

import com.ideaspymes.proyecttemplate.configuracion.model.Grupo;
import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IGrupoDAO;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.configuracion.web.converters.GrupoConverter;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author christian
 */
@Named
@ViewScoped
public class GrupoBean extends BeanGenerico<Grupo> implements Serializable {

    @EJB
    private IGrupoDAO ejb;

    @Override
    public AbstractDAO<Grupo> getEjb() {
        return ejb;
    }

    @Override
    public Grupo getNuevo() {
        return new Grupo();
    }

    @Override
    public Converter getConverter() {
        return new GrupoConverter();
    }

}
