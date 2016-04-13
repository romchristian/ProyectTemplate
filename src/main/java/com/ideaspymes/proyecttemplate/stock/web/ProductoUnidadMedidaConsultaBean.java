/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.ConsultaGenerico;
import com.ideaspymes.proyecttemplate.stock.model.ProductoUnidadMedida;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IProductoUnidadMedidaDAO;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Christian
 */
@Named
@ViewScoped
public class ProductoUnidadMedidaConsultaBean extends ConsultaGenerico<ProductoUnidadMedida> {

    @EJB
    private IProductoUnidadMedidaDAO ejb;

    @Override
    public Class<ProductoUnidadMedida> getClazz() {
        return ProductoUnidadMedida.class;
    }

    @Override
    public AbstractDAO<ProductoUnidadMedida> getEjb() {
        return ejb;
    }

   
}
