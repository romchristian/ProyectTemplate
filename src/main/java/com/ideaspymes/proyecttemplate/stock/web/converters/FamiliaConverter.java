/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web.converters;

import com.ideaspymes.proyecttemplate.generico.ConverterGenerico;
import com.ideaspymes.proyecttemplate.stock.model.Familia;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = Familia.class)
public class FamiliaConverter extends ConverterGenerico<Familia> {

    @Override
    public String getBeanName() {
        return "familiaBean";
    }
}
