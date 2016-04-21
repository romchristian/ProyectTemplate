/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.interfaces;

import com.ideaspymes.proyecttemplate.stock.model.MovimientoStock;
import javax.ejb.Local;


/**
 *
 * @author Acer
 */


@Local
public interface IMovimientoStockService {
    public void creaMovimientoStock(MovimientoStock m);
}
