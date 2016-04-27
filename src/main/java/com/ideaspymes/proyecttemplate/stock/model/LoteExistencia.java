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
import com.ideaspymes.proyecttemplate.stock.enums.EstadoLote;
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
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 *
 * @author cromero
 */
@Entity
public class LoteExistencia implements Serializable, IAuditable, IConSucursal {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Long version;
    private String codigo;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date ingreso;
    private String ubicacionDescripcion;
    private String refProveedor;
    private String refFactura;
    @ManyToOne
    private ComprobanteStock comprobanteStock;
    @ManyToOne
    private Deposito deposito;
    @ManyToOne
    private Ubicacion ubicacion;

    @ManyToOne
    private Producto producto;
    @ManyToOne
    private UnidadMedida unidadMedida;
    private Double cantidadIngresada;
    private Double costo;

    @ManyToOne
    private UnidadMedida unidadMedidaStock;
    private Double cantidadIngresadaStock;
    private Double cantidadUsadaStock;
    private Double cantidadReservadaStock;
    private Double cantidadSaldoStock;
    private Double costoUnitario;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date elaboracion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date vencimiento;
    @Enumerated(EnumType.STRING)
    private EstadoLote estadoLote;
    @Transient
    private boolean seleccionado = false;

    @ManyToOne
    private Empresa empresa;
    @ManyToOne
    private Sucursal sucursal;

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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getIngreso() {
        return ingreso;
    }

    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
    }

    public String getUbicacionDescripcion() {
        return ubicacionDescripcion;
    }

    public void setUbicacionDescripcion(String ubicacion) {
        this.ubicacionDescripcion = ubicacion;
    }

    public ComprobanteStock getComprobanteStock() {
        return comprobanteStock;
    }

    public void setComprobanteStock(ComprobanteStock comprobanteStock) {
        this.comprobanteStock = comprobanteStock;
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

    public Double getCantidadIngresada() {
        return cantidadIngresada;
    }

    public void setCantidadIngresada(Double cantidadIngresada) {
        this.cantidadIngresada = cantidadIngresada;
    }

    public Double getCantidadUsadaStock() {
        return cantidadUsadaStock;
    }

    public void setCantidadUsadaStock(Double cantidadUsada) {
        this.cantidadUsadaStock = cantidadUsada;
    }

    public Double getCantidadSaldoStock() {
        return cantidadSaldoStock;
    }

    public void setCantidadSaldoStock(Double cantidadSaldo) {
        this.cantidadSaldoStock = cantidadSaldo;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public UnidadMedida getUnidadMedidaStock() {
        return unidadMedidaStock;
    }

    public void setUnidadMedidaStock(UnidadMedida unidadMedidaStock) {
        this.unidadMedidaStock = unidadMedidaStock;
    }

    public Double getCantidadIngresadaStock() {
        return cantidadIngresadaStock;
    }

    public void setCantidadIngresadaStock(Double cantidadIngresadaStock) {
        this.cantidadIngresadaStock = cantidadIngresadaStock;
    }

    public Double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(Double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public Date getElaboracion() {
        return elaboracion;
    }

    public void setElaboracion(Date elaboracion) {
        this.elaboracion = elaboracion;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public Double getCantidadReservadaStock() {
        return cantidadReservadaStock;
    }

    public void setCantidadReservadaStock(Double cantidadReservada) {
        this.cantidadReservadaStock = cantidadReservada;
    }

    public EstadoLote getEstadoLote() {
        return estadoLote;
    }

    public void setEstadoLote(EstadoLote estadoLote) {
        this.estadoLote = estadoLote;
    }

    public String getRefProveedor() {
        return refProveedor;
    }

    public void setRefProveedor(String refProveedor) {
        this.refProveedor = refProveedor;
    }

    public String getRefFactura() {
        return refFactura;
    }

    public void setRefFactura(String refFactura) {
        this.refFactura = refFactura;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
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
        if (!(object instanceof LoteExistencia)) {
            return false;
        }
        LoteExistencia other = (LoteExistencia) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return codigo;
    }

}
