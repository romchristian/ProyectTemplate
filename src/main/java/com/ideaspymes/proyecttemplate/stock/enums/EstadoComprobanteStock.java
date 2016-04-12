/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.enums;

/**
 *
 * @author cromero
 */
public enum EstadoComprobanteStock {
    PENDIENTE_CONFIRMACION("Pendiente"),
    CONFIRMADO("Confirmado");
    
    private String label;

    private EstadoComprobanteStock(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    
}
