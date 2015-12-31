/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.seguridad;

import java.util.List;

/**
 *
 * @author cromero
 */
public class Submenu {

    private Modulo modulo;
    private String label;
    private String icon;
    private List<MenuItem> items;

    public Submenu(Modulo modulo, String label, String icon) {
        this.modulo = modulo;
        this.label = label;
        this.icon = icon;
    }
    

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

}
