/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.interfaces;

import com.ideaspymes.proyecttemplate.stock.model.Producto;

/**
 *
 * @author Elias
 */
public class ProductoCosto {

    private Producto producto;
    private Double costo;

    public ProductoCosto(Producto producto, Double costo) {
        this.producto = producto;
        this.costo = costo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

}
