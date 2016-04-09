/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.web.converters;

import com.ideaspymes.proyecttemplate.configuracion.model.Cotizacion;
import com.ideaspymes.proyecttemplate.generico.ConverterGenerico;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = Cotizacion.class)
public class CotizacionConverter extends ConverterGenerico<Cotizacion> {

    @Override
    public String getBeanName() {
        return "cotizacionBean";
    }
}
