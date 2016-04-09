/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.web.converters;

import com.ideaspymes.proyecttemplate.configuracion.model.Moneda;
import com.ideaspymes.proyecttemplate.generico.ConverterGenerico;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = Moneda.class)
public class MonedaConverter extends ConverterGenerico<Moneda> {

    @Override
    public String getBeanName() {
        return "monedaBean";
    }
}
