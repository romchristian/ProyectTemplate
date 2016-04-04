/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.model;

import com.ideaspymes.proyecttemplate.stock.enums.TipoMovimientoStock;
import javax.persistence.Entity;

/**
 *
 * @author Acer
 */
@Entity
public class MovimientoStockVenta extends MovimientoStock {

    public MovimientoStockVenta() {
        setTipo(TipoMovimientoStock.SALIDA);
    }

    @Override
    public Double cantidadAAfectar() {
        return (getCantidad() == null ? 0d : getCantidad()) * -1;
    }

}
