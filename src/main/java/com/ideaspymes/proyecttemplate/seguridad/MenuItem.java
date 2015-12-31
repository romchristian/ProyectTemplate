/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.seguridad;

/**
 *
 * @author cromero
 */
public class MenuItem {

    private Submenu submenu;
    private String url;
    private String value;
    private String icon;

    public MenuItem(Submenu submenu, String url, String value, String icon) {
        this.submenu = submenu;
        this.url = url;
        this.value = value;
        this.icon = icon;
    }

    public Submenu getSubmenu() {
        return submenu;
    }

    public void setSubmenu(Submenu submenu) {
        this.submenu = submenu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
