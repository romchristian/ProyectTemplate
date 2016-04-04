/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.impl;

/**
 *
 * @author Acer
 */
public class ConsultasStock {

    public static String getRendimiento() {
        return "SELECT pp.nombre,min(x.stock / x.cantidad) as rendimiento from producto pp \n"
                + "join \n"
                + "(SELECT p.nombre,p.stock,i.cantidad,i.productopadre_id from ingrediente i\n"
                + "join producto p on p.id = i.producto_id) x\n"
                + "on pp.id = x.productopadre_id\n"
                + "GROUP BY 1";
    }
}
