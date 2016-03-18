/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion;

/**
 *
 * @author cromero
 */
public class Producto {

    private String nombre;
    private Double cantidadVendida;
    private Double montoVendido;

    public Producto(String nombre, Double cantidadVendida, Double montoVendido) {
        this.nombre = nombre;
        this.cantidadVendida = cantidadVendida;
        this.montoVendido = montoVendido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(Double cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public Double getMontoVendido() {
        return montoVendido;
    }

    public void setMontoVendido(Double montoVendido) {
        this.montoVendido = montoVendido;
    }

}
