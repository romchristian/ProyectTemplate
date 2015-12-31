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
public class Modulo {

    private String nombre;
    private String iconFont;
    private String defaultpage;
    private String subtitle;
    private List<Submenu> menus;

    public Modulo(String nombre, String iconFont, String defaultpage, String subtitle) {
        this.nombre = nombre;
        this.iconFont = iconFont;
        this.defaultpage = defaultpage;
        this.subtitle = subtitle;
    }

    public List<Submenu> getMenus() {
        return menus;
    }

    public void setMenus(List<Submenu> menus) {
        this.menus = menus;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDefaultpage() {
        return defaultpage;
    }

    public void setDefaultpage(String defaultpage) {
        this.defaultpage = defaultpage;
    }

    public String getIconFont() {
        return iconFont;
    }

    public void setIconFont(String iconFont) {
        this.iconFont = iconFont;
    }

}
