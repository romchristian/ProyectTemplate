/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.web.converters;

import com.ideaspymes.proyecttemplate.configuracion.model.Sucursal;
import com.ideaspymes.proyecttemplate.generico.ConverterGenerico;
import javax.faces.convert.FacesConverter;


/**
 *
 * @author christian
 */
@FacesConverter(forClass = Sucursal.class)
public class SucursalConverter extends ConverterGenerico<Sucursal> {

    @Override
    public String getBeanName() {
        return "sucursalBean";
    }
}
