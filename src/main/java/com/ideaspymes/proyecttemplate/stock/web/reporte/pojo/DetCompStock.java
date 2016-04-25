/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web.reporte.pojo;

/**
 *
 * @author Christian
 */
public class DetCompStock {

    private Integer indice;
    private String producto;
    private Double cantidad;
    private String unidadMedida;

    public DetCompStock(Integer indice, String producto, Double cantidad, String unidadMedida) {
        this.indice = indice;
        this.producto = producto;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

}
