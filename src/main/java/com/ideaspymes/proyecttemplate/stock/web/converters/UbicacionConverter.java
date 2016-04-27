/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web.converters;

import com.ideaspymes.proyecttemplate.generico.ConverterGenerico;
import com.ideaspymes.proyecttemplate.stock.model.Ubicacion;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = Ubicacion.class)
public class UbicacionConverter extends ConverterGenerico<Ubicacion> {

    @Override
    public String getBeanName() {
        return "ubicacionBean";
    }
}
