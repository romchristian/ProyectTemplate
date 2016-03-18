/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.model;

import com.ideaspymes.proyecttemplate.generico.IAuditable;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.TipoCosteo;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author christian.romero
 */
@Entity
public class Empresa implements Serializable, IAuditable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Informacion principal
    private String nombre;
    private String ruc;

    // Información de contacto
    private String contactoDireccion;
    private String contactoCiudad;
    private String contactoPais;
    private String contactoTelefono;
    private String contactoEmail;
    private String contactoWeb;

    //Informacion Juridica
    private String nombreRepresentanteLegal;
    private String rucRepresentanteLegal;

    //Financiamiento
    private Double tasaAnualInteres;
    private Double tasaMensualInteres;
    private boolean sumarInteresAlPrecio;
    private boolean verificarLineaCreditoCliente;

    //Información avanzada
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date permitirOperacionDesde;
    @Enumerated(EnumType.STRING)
    private TipoCosteo tipoCosteo;
    private boolean seleccionCentrosCostosEnCompras;

    //Retencion
    private boolean esRetentor;
    private BigDecimal retencionMontoMinimo;
    private Double tasaRetencionIva;
    private Double tasaRetencionRenta;

    
    //Auditoria
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaRegitro;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaUltimaModificacion;
    @ManyToOne
    private Usuario usuarioUltimaModificacion;

    @ManyToMany(mappedBy = "empresa")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "empresa")
    private List<Sucursal> sucursales;

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

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getContactoDireccion() {
        return contactoDireccion;
    }

    public void setContactoDireccion(String contactoDireccion) {
        this.contactoDireccion = contactoDireccion;
    }

    public String getContactoCiudad() {
        return contactoCiudad;
    }

    public void setContactoCiudad(String contactoCiudad) {
        this.contactoCiudad = contactoCiudad;
    }

    public String getContactoPais() {
        return contactoPais;
    }

    public void setContactoPais(String contactoPais) {
        this.contactoPais = contactoPais;
    }

    public String getContactoTelefono() {
        return contactoTelefono;
    }

    public void setContactoTelefono(String contactoTelefono) {
        this.contactoTelefono = contactoTelefono;
    }

    public String getContactoEmail() {
        return contactoEmail;
    }

    public void setContactoEmail(String contactoEmail) {
        this.contactoEmail = contactoEmail;
    }

    public String getContactoWeb() {
        return contactoWeb;
    }

    public void setContactoWeb(String contactoWeb) {
        this.contactoWeb = contactoWeb;
    }

    public String getNombreRepresentanteLegal() {
        return nombreRepresentanteLegal;
    }

    public void setNombreRepresentanteLegal(String nombreRepresentanteLegal) {
        this.nombreRepresentanteLegal = nombreRepresentanteLegal;
    }

    public String getRucRepresentanteLegal() {
        return rucRepresentanteLegal;
    }

    public void setRucRepresentanteLegal(String rucRepresentanteLegal) {
        this.rucRepresentanteLegal = rucRepresentanteLegal;
    }

    public Double getTasaAnualInteres() {
        return tasaAnualInteres;
    }

    public void setTasaAnualInteres(Double tasaAnualInteres) {
        this.tasaAnualInteres = tasaAnualInteres;
    }

    public Double getTasaMensualInteres() {
        return tasaMensualInteres;
    }

    public void setTasaMensualInteres(Double tasaMensualInteres) {
        this.tasaMensualInteres = tasaMensualInteres;
    }

    public boolean isSumarInteresAlPrecio() {
        return sumarInteresAlPrecio;
    }

    public void setSumarInteresAlPrecio(boolean sumarInteresAlPrecio) {
        this.sumarInteresAlPrecio = sumarInteresAlPrecio;
    }

    public boolean isVerificarLineaCreditoCliente() {
        return verificarLineaCreditoCliente;
    }

    public void setVerificarLineaCreditoCliente(boolean verificarLineaCreditoCliente) {
        this.verificarLineaCreditoCliente = verificarLineaCreditoCliente;
    }

    public Date getPermitirOperacionDesde() {
        return permitirOperacionDesde;
    }

    public void setPermitirOperacionDesde(Date permitirOperacionDesde) {
        this.permitirOperacionDesde = permitirOperacionDesde;
    }

    public boolean isEsRetentor() {
        return esRetentor;
    }

    public void setEsRetentor(boolean esRetentor) {
        this.esRetentor = esRetentor;
    }

    public BigDecimal getRetencionMontoMinimo() {
        return retencionMontoMinimo;
    }

    public void setRetencionMontoMinimo(BigDecimal retencionMontoMinimo) {
        this.retencionMontoMinimo = retencionMontoMinimo;
    }

    public Double getTasaRetencionIva() {
        return tasaRetencionIva;
    }

    public void setTasaRetencionIva(Double tasaRetencionIva) {
        this.tasaRetencionIva = tasaRetencionIva;
    }

    public Double getTasaRetencionRenta() {
        return tasaRetencionRenta;
    }

    public void setTasaRetencionRenta(Double tasaRetencionRenta) {
        this.tasaRetencionRenta = tasaRetencionRenta;
    }

    public TipoCosteo getTipoCosteo() {
        return tipoCosteo;
    }

    public void setTipoCosteo(TipoCosteo tipoCosteo) {
        this.tipoCosteo = tipoCosteo;
    }

    public boolean isSeleccionCentrosCostosEnCompras() {
        return seleccionCentrosCostosEnCompras;
    }

    public void setSeleccionCentrosCostosEnCompras(boolean seleccionCentrosCostosEnCompras) {
        this.seleccionCentrosCostosEnCompras = seleccionCentrosCostosEnCompras;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
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

    public Usuario getUsuarioUltimaModificacion() {
        return usuarioUltimaModificacion;
    }

    public void setUsuarioUltimaModificacion(Usuario usuarioUltimaModificacion) {
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
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
