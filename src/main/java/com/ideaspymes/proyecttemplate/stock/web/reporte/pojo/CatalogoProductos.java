/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web.reporte.pojo;

import java.util.ArrayList;

/**
 *
 * @author Acer
 */
public class CatalogoProductos {
    
    private byte[] imagen;
    private String producto;
    private String descripcion;
    private ArrayList<String> ubicaciones;
    private Double stock;

    public CatalogoProductos(byte[] imagen, String producto, String descripcion, ArrayList<String> ubicaciones, Double stock) {
        this.imagen = imagen;
        this.producto = producto;
        this.descripcion = descripcion;
        this.ubicaciones = ubicaciones;
        this.stock = stock;
    }
    
    

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<String> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(ArrayList<String> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }
 
}