/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.web;

import com.ideaspymes.proyecttemplate.configuracion.model.Moneda;
import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IMonedaDAO;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.configuracion.web.converters.MonedaConverter;
import com.ideaspymes.proyecttemplate.generico.JsfUtil;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author christian
 */
@Named
@ViewScoped
public class MonedaBean extends BeanGenerico<Moneda> implements Serializable {

    @EJB
    private IMonedaDAO ejb;

    @Override
    public AbstractDAO<Moneda> getEjb() {
        return ejb;
    }

    @Override
    public Moneda getNuevo() {
        return new Moneda();
    }

    @Override
    public Converter getConverter() {
        return new MonedaConverter();
    }

    public SelectItem[] getItemsParaCotizacion() {
        return JsfUtil.getSelectItems(ejb.findAllParaCotizacion(), false);
    }

}
