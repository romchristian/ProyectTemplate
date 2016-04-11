/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web.converters;

import com.ideaspymes.proyecttemplate.generico.ConverterGenerico;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = Producto.class,value = "productoConverter")
public class ProductoConverter extends ConverterGenerico<Producto> {

    @Override
    public String getBeanName() {
        return "productoBean";
    }
}
