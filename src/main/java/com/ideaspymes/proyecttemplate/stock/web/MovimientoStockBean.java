/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;


import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.stock.web.converters.MovimientoStockConverter;
import com.ideaspymes.proyecttemplate.stock.model.MovimientoStock;
import com.ideaspymes.proyecttemplate.stock.model.MovimientoStockCompra;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IMovimientoStockDAO;
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
public class MovimientoStockBean extends BeanGenerico<MovimientoStock> implements Serializable {

    @EJB
    private IMovimientoStockDAO ejb;

    @Override
    public AbstractDAO<MovimientoStock> getEjb() {
        return ejb;
    }

    @Override
    public MovimientoStock getNuevo() {
        return new MovimientoStockCompra();
    }

    @Override
    public Converter getConverter() {
        return new MovimientoStockConverter();
    }

}
