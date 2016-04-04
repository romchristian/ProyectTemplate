/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.stock.web.converters.ComprobanteStockConverter;
import com.ideaspymes.proyecttemplate.stock.model.ComprobanteStock;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IComprobanteStockDAO;
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
public class ComprobanteStockBean extends BeanGenerico<ComprobanteStock> implements Serializable {

    @EJB
    private IComprobanteStockDAO ejb;

    @Override
    public AbstractDAO<ComprobanteStock> getEjb() {
        return ejb;
    }

    @Override
    public ComprobanteStock getNuevo() {
        return new ComprobanteStock();
    }

    @Override
    public Converter getConverter() {
        return new ComprobanteStockConverter();
    }

}
