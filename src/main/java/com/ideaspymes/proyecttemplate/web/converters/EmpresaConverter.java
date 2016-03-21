/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.web.converters;

import com.ideaspymes.proyecttemplate.configuracion.model.Empresa;
import com.ideaspymes.proyecttemplate.generico.ConverterGenerico;
import javax.faces.convert.FacesConverter;


/**
 *
 * @author christian
 */
@FacesConverter(forClass = Empresa.class)
public class EmpresaConverter extends ConverterGenerico<Empresa> {

    @Override
    public String getBeanName() {
        return "empresaBean";
    }
}
