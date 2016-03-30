/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.web.converters;

import com.ideaspymes.proyecttemplate.configuracion.model.EstadoCivil;
import com.ideaspymes.proyecttemplate.generico.ConverterGenerico;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = EstadoCivil.class)
public class EstadoCivilConverter extends ConverterGenerico<EstadoCivil> {

    @Override
    public String getBeanName() {
        return "estadoCivilBean";
    }
}
