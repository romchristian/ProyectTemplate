/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web.converters;

import com.ideaspymes.proyecttemplate.generico.ConverterGenerico;
import com.ideaspymes.proyecttemplate.stock.model.LoteExistencia;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = LoteExistencia.class)
public class LoteExistenciaConverter extends ConverterGenerico<LoteExistencia> {

    @Override
    public String getBeanName() {
        return "loteExistenciaBean";
    }
}
