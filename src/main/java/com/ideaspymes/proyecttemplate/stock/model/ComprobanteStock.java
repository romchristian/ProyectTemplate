/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.model;

import com.ideaspymes.proyecttemplate.configuracion.model.Empresa;
import com.ideaspymes.proyecttemplate.configuracion.model.Sucursal;
import com.ideaspymes.proyecttemplate.configuracion.model.Usuario;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.IAuditable;
import com.ideaspymes.proyecttemplate.generico.IConSucursal;
import com.ideaspymes.proyecttemplate.generico.Listado;
import com.ideaspymes.proyecttemplate.stock.enums.TipoComprobanteStock;
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
import javax.persistence.Version;

/**
 *
 * @author Acer
 */
@Entity
public class ComprobanteStock implements Serializable, IAuditable, IConSucursal {

    @OneToMany(mappedBy = "comprobanteStock", cascade = CascadeType.PERSIST)
    private List<DetComprobanteStock> detalles;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Listado(descripcion = "Nro.",mostrar = true,link = true)
    private Long id;
    @Version
    private Long version;
    @Enumerated(EnumType.STRING)
    private TipoComprobanteStock tipo;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha;
    private String refOrigen;// Debe ser Entidad:id ej.: "Cliente:123" o "Deposito:1":
    private String refDestino;
    @ManyToOne
    private Usuario usuario;
    private String refDocumento;
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
    
    

    public TipoComprobanteStock getTipo() {
        return tipo;
    }

    public void setTipo(TipoComprobanteStock tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRefOrigen() {
        return refOrigen;
    }

    public void setRefOrigen(String refOrigen) {
        this.refOrigen = refOrigen;
    }

    public String getRefDestino() {
        return refDestino;
    }

    public void setRefDestino(String refDestino) {
        this.refDestino = refDestino;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetComprobanteStock> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetComprobanteStock> detalles) {
        this.detalles = detalles;
    }

    public String getRefDocumento() {
        return refDocumento;
    }

    public void setRefDocumento(String refDocumento) {
        this.refDocumento = refDocumento;
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
        if (!(object instanceof ComprobanteStock)) {
            return false;
        }
        ComprobanteStock other = (ComprobanteStock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.ideaspymes.facilerp.pesistencia.stock.ComprobanteStock[ id=" + id + " ]";
    }

}
