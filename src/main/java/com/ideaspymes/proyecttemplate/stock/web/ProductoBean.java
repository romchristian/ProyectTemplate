/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.stock.model.Existencia;
import com.ideaspymes.proyecttemplate.stock.web.converters.ProductoConverter;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.ICostoService;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IProductoDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class ProductoBean extends BeanGenerico<Producto> implements Serializable {

    @EJB
    private IProductoDAO ejb;
    @EJB
    private ICostoService costoService;

    @Override
    public AbstractDAO<Producto> getEjb() {
        return ejb;
    }

    @Override
    public Producto getNuevo() {

        return new Producto();
    }

    @Override
    public Converter getConverter() {
        return new ProductoConverter();
    }

    public Double obtCosto(Producto p) {
        return costoService.getCosto(p);
    }

    public List<Existencia> obtExistencias(Producto p) {
        List<Existencia> R = ejb.findExistenciasPorProducto(p);
        if (R == null) {
            R = new ArrayList<>();
        }
        return R;
    }
}
