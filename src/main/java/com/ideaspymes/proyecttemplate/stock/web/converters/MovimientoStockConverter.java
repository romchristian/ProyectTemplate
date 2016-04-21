/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web.converters;

import com.ideaspymes.proyecttemplate.generico.ConverterGenerico;
import com.ideaspymes.proyecttemplate.stock.model.MovimientoStock;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = MovimientoStock.class)
public class MovimientoStockConverter extends ConverterGenerico<MovimientoStock> {

    @Override
    public String getBeanName() {
        return "movimientoStockBean";
    }
}
