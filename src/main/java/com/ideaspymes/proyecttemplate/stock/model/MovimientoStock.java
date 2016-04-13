/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.model;

import com.ideaspymes.proyecttemplate.configuracion.model.Empresa;
import com.ideaspymes.proyecttemplate.configuracion.model.Sucursal;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.IAuditable;
import com.ideaspymes.proyecttemplate.generico.IConSucursal;
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
import javax.persistence.Version;

/**
 *
 * @author Acer
 */
@Entity
public abstract class MovimientoStock implements Serializable, IAuditable, IConSucursal {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Long version;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha;
    @Enumerated(EnumType.STRING)
    private TipoMovimientoStock tipo;
    @ManyToOne
    private Producto producto;
    @ManyToOne
    private UnidadMedida unidadMedida;
    private Double cantidad;

    @ManyToOne
    private UnidadMedida unidadMedidaStock;
    private Double cantidadStock;
    @ManyToOne
    private ComprobanteStock comprobanteStock;
    @ManyToOne
    private Deposito deposito;

    @ManyToOne
    private Empresa empresa;
    @ManyToOne
    private Sucursal sucursal;
    @ManyToOne
    private LoteExistencia loteExistencia;

    //Auditoria
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaRegitro;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaUltimaModificacion;
    private String usuarioUltimaModificacion;

    public abstract Double cantidadAAfectar();

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TipoMovimientoStock getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimientoStock tipo) {
        this.tipo = tipo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public ComprobanteStock getComprobanteStock() {
        return comprobanteStock;
    }

    public void setComprobanteStock(ComprobanteStock comprobanteStock) {
        this.comprobanteStock = comprobanteStock;
    }

    public LoteExistencia getLoteExistencia() {
        return loteExistencia;
    }

    public void setLoteExistencia(LoteExistencia loteExistencia) {
        this.loteExistencia = loteExistencia;
    }

    public UnidadMedida getUnidadMedidaStock() {
        return unidadMedidaStock;
    }

    public void setUnidadMedidaStock(UnidadMedida unidadMedidaStock) {
        this.unidadMedidaStock = unidadMedidaStock;
    }

    public Double getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(Double cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Date getFechaRegitro() {
        return fechaRegitro;
    }

    public void setFechaRegitro(Date fechaRegitro) {
        this.fechaRegitro = fechaRegitro;
    }

    public Date getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    public String getUsuarioUltimaModificacion() {
        return usuarioUltimaModificacion;
    }

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
        if (!(object instanceof MovimientoStock)) {
            return false;
        }
        MovimientoStock other = (MovimientoStock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.ideaspymes.facilerp.stock.persistencia.MovimientoStock[ id=" + id + " ]";
    }

}
