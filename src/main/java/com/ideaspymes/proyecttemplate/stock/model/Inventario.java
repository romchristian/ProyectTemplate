/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.model;

import com.ideaspymes.proyecttemplate.configuracion.model.*;
import com.ideaspymes.proyecttemplate.generico.IAuditable;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.Filtro;
import com.ideaspymes.proyecttemplate.generico.FiltroGenerico;
import com.ideaspymes.proyecttemplate.generico.IConSucursal;
import com.ideaspymes.proyecttemplate.generico.Listado;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author christian.romero
 */
@Entity
public class Inventario implements Serializable, IConSucursal, IAuditable {

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Listado(descripcion = "Fecha", mostrar = true)
    @Filtro(descripcion = "Fecha", campo = "fecha",tipo = FiltroGenerico.TIPO_RANGO_FECHA)
    private Date fecha;
    @Listado(descripcion = "Responsable", mostrar = true,campo = "responsable",entidad = true,campoDescripcion = "nombre", modulo = "configuracion",outcome = "/main/configuracion/usuario/vista")
    @Filtro(descripcion = "Responsable", campo = "responsable",tipo = FiltroGenerico.TIPO_AUTOCOMPLETE,campoDescripcion = "nombre")
    @ManyToOne
    private Usuario responsable;
    @Listado(descripcion = "Supervisor", mostrar = true,campo = "Supervisor",entidad = true,campoDescripcion = "nombre", modulo = "configuracion",outcome = "/main/configuracion/usuario/vista")
    @ManyToOne
    private Usuario supervisor;
    @Listado(descripcion = "Lugar", mostrar = true,campo = "deposito",entidad = true,campoDescripcion = "nombre", modulo = "stock",outcome = "/main/stock/deposito/vista")
    @Filtro(descripcion = "Lugar", campo = "deposito",tipo = FiltroGenerico.TIPO_SELECT_ONE,campoDescripcion = "nombre")
    @ManyToOne
    private Deposito deposito;
    @Listado(descripcion = "Ubicación", mostrar = true,campo = "ubicacion",entidad = true,campoDescripcion = "nombre", modulo = "stock",outcome = "/main/stock/ubicacion/vista")
    @ManyToOne
    private Ubicacion ubicacion;

    @OneToMany(mappedBy = "inventario",cascade =CascadeType.ALL,orphanRemoval = true)
    private List<DetInventario> detalles;

    @ManyToOne
    @Listado(descripcion = "Empresa", mostrar = true, entidad = true, campoDescripcion = "nombre",outcome = "/main/configuracion/empresa/vista")
    private Empresa empresa;

    @ManyToOne
    @Listado(descripcion = "Sucursal", mostrar = true, entidad = true, campoDescripcion = "nombre",outcome = "/main/configuracion/empresa/vista")
    private Sucursal sucursal;

    //Auditoria
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaRegitro;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaUltimaModificacion;
    private String usuarioUltimaModificacion;

    public List<DetInventario> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetInventario> detalles) {
        this.detalles = detalles;
    }

    
    
    
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public Sucursal getSucursal() {
        return sucursal;
    }

    @Override
    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getResponsable() {
        return responsable;
    }

    public void setResponsable(Usuario responsable) {
        this.responsable = responsable;
    }

    public Usuario getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Usuario supervisor) {
        this.supervisor = supervisor;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
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
        if (!(object instanceof Inventario)) {
            return false;
        }
        Inventario other = (Inventario) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Inv Nro. "+ id;
    }

}
