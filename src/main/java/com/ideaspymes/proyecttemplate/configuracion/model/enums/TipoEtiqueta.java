/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.model.enums;

/**
 *
 * @author christian.romero
 */
public enum TipoEtiqueta {
    EAN13("EAN 13"), CODE39("CODE 39"), CODE128("CODE 128");

    private String label;

    private TipoEtiqueta(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
