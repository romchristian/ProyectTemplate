/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web.converters;

import com.ideaspymes.proyecttemplate.generico.ConverterGenerico;
import com.ideaspymes.proyecttemplate.stock.model.ProductoUnidadMedida;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = ProductoUnidadMedida.class)
public class ProductoUnidadMedidaConverter extends ConverterGenerico<ProductoUnidadMedida> {

    @Override
    public String getBeanName() {
        return "productoUnidadMedidaBean";
    }
}
