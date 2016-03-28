/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.generico;

/**
 *
 * @author Christian
 */
public class Columna {

    private String campo;
    private String descripcion;
    private String tipo;
    private boolean link;

    public Columna(String descripcion, String campo, String tipo, boolean link) {
        this.descripcion = descripcion;
        this.campo = campo;
        this.tipo = tipo;
        this.link = link;
    }

    public boolean isLink() {
        return link;
    }

    public void setLink(boolean link) {
        this.link = link;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
