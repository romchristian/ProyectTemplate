/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web.converters;

import com.ideaspymes.proyecttemplate.generico.ConverterGenerico;
import com.ideaspymes.proyecttemplate.stock.model.ComprobanteStock;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = ComprobanteStock.class)
public class ComprobanteStockConverter extends ConverterGenerico<ComprobanteStock> {

    @Override
    public String getBeanName() {
        return "comprobanteStockBean";
    }
}
