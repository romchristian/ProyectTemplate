/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.exception;

import com.ideaspymes.proyecttemplate.stock.model.Deposito;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import javax.ejb.ApplicationException;

/**
 *
 * @author Elias
 */
@ApplicationException(rollback = true)
public class SinStockException extends Exception {

    public SinStockException(Producto producto, Deposito deposito) {
        super("Stock insuficiente para : " + producto.getNombre() + " en " + deposito.getNombre());
    }

}
