/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.interfaces;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.model.Deposito;
import com.ideaspymes.proyecttemplate.stock.model.Existencia;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.model.Ubicacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Acer
 */
@Local
public interface IProductoDAO extends AbstractDAO<Producto> {

    @Override
    Producto create(Producto entity);

    @Override
    Producto edit(Producto entity);

    @Override
    Producto find(Object id);

    Producto findPorCodigo(String codigo);

    @Override
    List<Producto> findAll();

    @Override
    List<Producto> findAll(String query, QueryParameter params);

    @Override
    void remove(Producto entity);

    List<Existencia> findExistenciasPorProducto(Producto p);
    
    List<Producto> findFilterAll(String query);    

    public List<Existencia> findExistenciaPorDeposito(Deposito d, Ubicacion u);

    Existencia findExistenciasPorProductoUbicacion(Producto p, Deposito d, Ubicacion u);

}
