/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.stock.web.converters.UbicacionConverter;
import com.ideaspymes.proyecttemplate.stock.model.Ubicacion;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IUbicacionDAO;
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
public class UbicacionBean extends BeanGenerico<Ubicacion> implements Serializable {

    @EJB
    private IUbicacionDAO ejb;

    @Override
    public AbstractDAO<Ubicacion> getEjb() {
        return ejb;
    }

    @Override
    public Ubicacion getNuevo() {
        return new Ubicacion();
    }

    @Override
    public Converter getConverter() {
        return new UbicacionConverter();
    }

}
