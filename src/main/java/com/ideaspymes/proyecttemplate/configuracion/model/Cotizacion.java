/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.model;

import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.Filtro;
import com.ideaspymes.proyecttemplate.generico.IAuditable;
import com.ideaspymes.proyecttemplate.generico.Listado;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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
public class Cotizacion implements Serializable, IAuditable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Informacion principal
    @Listado(descripcion = "Fecha",mostrar = true, link = true)
    @Temporal(javax.persistence.TemporalType.DATE)
     @Filtro(descripcion = "Fecha",tipo = "rangoFecha",campo = "fecha")
    private Date fecha;
    @Listado(descripcion = "Moneda",mostrar=true, link = false,campoDescripcion = "nombre",entidad = true,modulo = "configuracion")
    @Filtro(descripcion = "Moneda",tipo = "selectOne",campo = "moneda",campoDescripcion = "nombre")
    @ManyToOne
    private Moneda moneda;
    @Listado(descripcion = "Compra",mostrar = true)
    private Double compra;
    @Listado(descripcion = "Venta",mostrar = true)
    private Double venta;
    @ManyToOne
    private Empresa empresa;

    //Auditoria
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaRegitro;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaUltimaModificacion;
    private String usuarioUltimaModificacion;

    public Cotizacion() {
        fecha = new Date();
    }

    
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Double getCompra() {
        return compra;
    }

    public void setCompra(Double compra) {
        this.compra = compra;
    }

    public Double getVenta() {
        return venta;
    }

    public void setVenta(Double venta) {
        this.venta = venta;
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
        if (!(object instanceof Cotizacion)) {
            return false;
        }
        Cotizacion other = (Cotizacion) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fecha)+" - "+moneda.getNombre();
    }

}
