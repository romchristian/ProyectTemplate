/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;


import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.generico.JsfUtil;
import com.ideaspymes.proyecttemplate.stock.enums.TipoMovimientoStock;
import com.ideaspymes.proyecttemplate.stock.web.converters.TipoComprobanteStockConverter;
import com.ideaspymes.proyecttemplate.stock.model.TipoComprobanteStock;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.ITipoComprobanteStockDAO;
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
public class TipoComprobanteStockBean extends BeanGenerico<TipoComprobanteStock> implements Serializable {

    @EJB
    private ITipoComprobanteStockDAO ejb;

    @Override
    public AbstractDAO<TipoComprobanteStock> getEjb() {
        return ejb;
    }

    @Override
    public TipoComprobanteStock getNuevo() {
        return new TipoComprobanteStock();
    }

    @Override
    public Converter getConverter() {
        return new TipoComprobanteStockConverter();
    }
    
    public SelectItem[] getItemsEntradas(){
        return JsfUtil.getSelectItems(ejb.findAllPorTipo(TipoMovimientoStock.ENTRADA), false);
    }

}
