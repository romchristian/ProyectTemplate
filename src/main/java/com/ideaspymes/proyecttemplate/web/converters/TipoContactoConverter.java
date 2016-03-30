/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.web.converters;

import com.ideaspymes.proyecttemplate.configuracion.model.TipoContacto;
import com.ideaspymes.proyecttemplate.generico.ConverterGenerico;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = TipoContacto.class)
public class TipoContactoConverter extends ConverterGenerico<TipoContacto> {

    @Override
    public String getBeanName() {
        return "tipoContactoBean";
    }
}
