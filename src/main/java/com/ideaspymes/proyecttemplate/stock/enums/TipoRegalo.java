/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.enums;

/**
 *
 * @author christian.romero
 */
public enum TipoRegalo {
    NACIONALES("Nacionales"), INTERNACIONALES("Internacionales");

    private String label;

    private TipoRegalo(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
