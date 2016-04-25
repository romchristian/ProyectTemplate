/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.model;

import com.ideaspymes.proyecttemplate.configuracion.model.*;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.Filtro;
import com.ideaspymes.proyecttemplate.generico.IAuditable;
import com.ideaspymes.proyecttemplate.generico.Listado;
import com.ideaspymes.proyecttemplate.stock.enums.TipoMovimientoStock;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author christian.romero
 */
@Entity
public class TipoComprobanteStock implements Serializable, IAuditable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Informacion principal
    @Listado(descripcion = "Nombre", mostrar = true, link = true)
    @Filtro(descripcion = "Nombre", tipo = "like", campo = "nombre")
    private String nombre;
    @Enumerated(EnumType.STRING)
    private TipoMovimientoStock tipo;

    @ManyToOne
    @Listado(descripcion = "Empresa", mostrar = true, entidad = true, campoDescripcion = "nombre")
    private Empresa empresa;

    //Auditoria
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaRegitro;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaUltimaModificacion;
    private String usuarioUltimaModificacion;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoMovimientoStock getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimientoStock tipo) {
        this.tipo = tipo;
    }

    @Override
    public Empresa getEmpresa() {
        return empresa;
    }

    @Override
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public Estado getEstado() {
        return estado;
    }

    @Override
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public Date getFechaRegitro() {
        return fechaRegitro;
    }

    @Override
    public void setFechaRegitro(Date fechaRegitro) {
        this.fechaRegitro = fechaRegitro;
    }

    @Override
    public Date getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    @Override
    public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    @Override
    public String getUsuarioUltimaModificacion() {
        return usuarioUltimaModificacion;
    }

    @Override
    public void setUsuarioUltimaModificacion(String usuarioUltimaModificacion) {
        this.usuarioUltimaModificacion = usuarioUltimaModificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoComprobanteStock)) {
            return false;
        }
        TipoComprobanteStock other = (TipoComprobanteStock) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return nombre;
    }

}
