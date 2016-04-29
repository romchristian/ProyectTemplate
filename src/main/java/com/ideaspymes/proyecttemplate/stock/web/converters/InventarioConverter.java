/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web.converters;

import com.ideaspymes.proyecttemplate.generico.ConverterGenerico;
import com.ideaspymes.proyecttemplate.stock.model.Inventario;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = Inventario.class,value = "inventarioConverter")
public class InventarioConverter extends ConverterGenerico<Inventario> {
    @Override
    public String getBeanName() {
        return "inventarioBean";
    }
}
