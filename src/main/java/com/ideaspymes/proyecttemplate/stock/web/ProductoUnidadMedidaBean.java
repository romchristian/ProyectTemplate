/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;


import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.stock.web.converters.ProductoUnidadMedidaConverter;
import com.ideaspymes.proyecttemplate.stock.model.ProductoUnidadMedida;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IProductoUnidadMedidaDAO;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author christian
 */
@Named
@ViewScoped
public class ProductoUnidadMedidaBean extends BeanGenerico<ProductoUnidadMedida> implements Serializable {

    @EJB
    private IProductoUnidadMedidaDAO ejb;

    @Override
    public AbstractDAO<ProductoUnidadMedida> getEjb() {
        return ejb;
    }

    @Override
    public ProductoUnidadMedida getNuevo() {
        return new ProductoUnidadMedida();
    }

    @Override
    public Converter getConverter() {
        return new ProductoUnidadMedidaConverter();
    }
   
}
