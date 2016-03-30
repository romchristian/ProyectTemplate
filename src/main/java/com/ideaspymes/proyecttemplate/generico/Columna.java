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
    private boolean entidad;
    private String campoDescripcion;

    public Columna(String descripcion,String campo,String tipo, boolean link, boolean entidad, String campoDescripcion) {
        this.campo = campo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.link = link;
        this.entidad = entidad;
        this.campoDescripcion = campoDescripcion;
    }

    public boolean isEntidad() {
        return entidad;
    }

    public void setEntidad(boolean entidad) {
        this.entidad = entidad;
    }

    public String getCampoDescripcion() {
        return campoDescripcion;
    }

    public void setCampoDescripcion(String campoDescripcion) {
        this.campoDescripcion = campoDescripcion;
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
