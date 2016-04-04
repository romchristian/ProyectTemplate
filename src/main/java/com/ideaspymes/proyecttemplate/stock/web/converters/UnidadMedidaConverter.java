/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web.converters;

import com.ideaspymes.proyecttemplate.generico.ConverterGenerico;
import com.ideaspymes.proyecttemplate.stock.model.UnidadMedida;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = UnidadMedida.class)
public class UnidadMedidaConverter extends ConverterGenerico<UnidadMedida> {

    @Override
    public String getBeanName() {
        return "unidadMedidaBean";
    }
}
