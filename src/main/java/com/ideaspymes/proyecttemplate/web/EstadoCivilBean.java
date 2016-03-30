/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.web;

import com.ideaspymes.proyecttemplate.configuracion.model.EstadoCivil;
import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IEstadoCivilDAO;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.web.converters.EstadoCivilConverter;
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
public class EstadoCivilBean extends BeanGenerico<EstadoCivil> implements Serializable {

    @EJB
    private IEstadoCivilDAO ejb;

    @Override
    public AbstractDAO<EstadoCivil> getEjb() {
        return ejb;
    }

    @Override
    public EstadoCivil getNuevo() {
        return new EstadoCivil();
    }

    @Override
    public Converter getConverter() {
        return new EstadoCivilConverter();
    }

}
