/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.interfaces;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.model.ProductoUnidadMedida;
import com.ideaspymes.proyecttemplate.stock.model.UnidadMedida;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Acer
 */
@Local
public interface IProductoUnidadMedidaDAO extends AbstractDAO<ProductoUnidadMedida> {

    @Override
    ProductoUnidadMedida create(ProductoUnidadMedida entity);

    @Override
    ProductoUnidadMedida edit(ProductoUnidadMedida entity);

    @Override
    ProductoUnidadMedida find(Object id);
    
    
    ProductoUnidadMedida find(Producto p, UnidadMedida umDe, UnidadMedida umA);

    @Override
    List<ProductoUnidadMedida> findAll();

    @Override
    List<ProductoUnidadMedida> findAll(String query, QueryParameter params);

    @Override
    void remove(ProductoUnidadMedida entity);

}
