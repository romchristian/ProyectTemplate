/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.web;

import com.ideaspymes.proyecttemplate.configuracion.model.Cotizacion;
import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.ICotizacionDAO;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.configuracion.web.converters.CotizacionConverter;
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
public class CotizacionBean extends BeanGenerico<Cotizacion> implements Serializable {

    @EJB
    private ICotizacionDAO ejb;

    @Override
    public AbstractDAO<Cotizacion> getEjb() {
        return ejb;
    }

    @Override
    public Cotizacion getNuevo() {
        return new Cotizacion();
    }

    @Override
    public Converter getConverter() {
        return new CotizacionConverter();
    }

}
