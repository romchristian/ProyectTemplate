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
public class MovimientoStockEntradaAjuste extends MovimientoStock {

    public MovimientoStockEntradaAjuste() {
        setTipo(TipoMovimientoStock.ENTRADA);
    }

    @Override
    public Double cantidadAAfectar() {
        return getCantidadStock()== null ? 0d : getCantidadStock();
    }

}
