/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.web.converters;

import com.ideaspymes.proyecttemplate.configuracion.model.Contacto;
import com.ideaspymes.proyecttemplate.generico.ConverterGenerico;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = Contacto.class,value = "contactoConverter")
public class ContactoConverter extends ConverterGenerico<Contacto> {

    @Override
    public String getBeanName() {
        return "contactoBean";
    }
}
