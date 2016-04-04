/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.ConsultaGenerico;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IProductoDAO;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Christian
 */
@Named
@ViewScoped
public class ProductoConsultaBean extends ConsultaGenerico<Producto> {

    @EJB
    private IProductoDAO ejb;

    @Override
    public Class<Producto> getClazz() {
        return Producto.class;
    }

    @Override
    public AbstractDAO<Producto> getEjb() {
        return ejb;
    }

}
