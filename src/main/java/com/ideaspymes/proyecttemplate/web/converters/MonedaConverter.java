/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.web.converters;

import com.ideaspymes.proyecttemplate.configuracion.model.Contacto;
import com.ideaspymes.proyecttemplate.generico.ConverterGenerico;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = Contacto.class)
public class MonedaConverter extends ConverterGenerico<Contacto> {

    @Override
    public String getBeanName() {
        return "monedaBean";
    }
}
