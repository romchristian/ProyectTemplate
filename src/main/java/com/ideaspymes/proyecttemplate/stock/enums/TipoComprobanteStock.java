/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.enums;

/**
 *
 * @author Acer
 */
public enum TipoComprobanteStock {
    VENTA("Venta"),
    COMPRA("Compra"),
    TRANSFERENCIA_INTERNA("Transferencia Interna"),
    TRANSFERENCIA_EXTERNA("Transferencia Externa");

    private String label;

    private TipoComprobanteStock(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
