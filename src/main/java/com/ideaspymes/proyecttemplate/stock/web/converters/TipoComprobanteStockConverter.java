/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web.converters;

import com.ideaspymes.proyecttemplate.generico.ConverterGenerico;
import com.ideaspymes.proyecttemplate.stock.model.TipoComprobanteStock;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = TipoComprobanteStock.class)
public class TipoComprobanteStockConverter extends ConverterGenerico<TipoComprobanteStock> {

    @Override
    public String getBeanName() {
        return "tipoComprobanteStockBean";
    }
}
