/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.model;

import com.ideaspymes.proyecttemplate.generico.IAuditable;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.Filtro;
import com.ideaspymes.proyecttemplate.generico.IConImagen;
import com.ideaspymes.proyecttemplate.generico.IConSucursal;
import com.ideaspymes.proyecttemplate.generico.Listado;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author christian.romero
 */
@Entity
public class Contacto implements Serializable, IConSucursal, IAuditable, IConImagen {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Informacion principal
    @Listado(descripcion = "Código", mostrar = true)
    @Filtro(descripcion = "Código", tipo = "like", campo = "codigo")
    private String codigo;

    @Listado(descripcion = "Nombre", mostrar = true, link = true)
    @Filtro(descripcion = "Nombre", tipo = "like", campo = "nombre")
    private String nombre;
    @ManyToOne
    @Filtro(descripcion = "Tipo", tipo = "selectOne", campo = "tipoContacto", campoDescripcion = "nombre")
    @Listado(descripcion = "Tipo", mostrar = true, entidad = true, campoDescripcion = "nombre", modulo = "configuracion")
    private TipoContacto tipoContacto;
    @ManyToOne
    @Filtro(descripcion = "Tipo Documento", tipo = "selectOne", campo = "tipoDocumento", campoDescripcion = "nombre")
    @Listado(descripcion = "Tipo Documento", mostrar = true, entidad = true, campoDescripcion = "nombre", modulo = "configuracion")
    private TipoDocumento tipoDocumento;
    @Listado(descripcion = "Id Documento", mostrar = true)
    @Filtro(descripcion = "Id Documento", tipo = "like", campo = "documentoId")
    private String documentoId;

    @Listado(descripcion = "Nacionalidad", mostrar = true)
    @Filtro(descripcion = "Nacionalidad", tipo = "like", campo = "nacionalidad")
    private String nacionalidad;

    @ManyToOne
    @Filtro(descripcion = "Estado Civil", tipo = "selectOne", campo = "estadoCivil", campoDescripcion = "nombre")
    @Listado(descripcion = "Estado Civil", mostrar = true, entidad = true, campoDescripcion = "nombre", modulo = "configuracion")
    private EstadoCivil estadoCivil;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;

    @ManyToOne
    @Listado(descripcion = "Empresa", mostrar = true, entidad = true, campoDescripcion = "nombre", modulo = "configuracion")
    private Empresa empresa;

    @ManyToOne
    @Listado(descripcion = "Sucursal", mostrar = true, entidad = true, campoDescripcion = "nombre", modulo = "configuracion")
    private Sucursal sucursal;

    //Informacion de contacto
    private String telefonoFijo;
    private String telefonoMovil;
    private String email;
    private String lugarDeTrabajo;

    //Información de cliente
    private boolean esCliente;
    private boolean esExentoImpuesto;
    private Integer tasaMaximaDescuentoVenta;

    private BigDecimal lineaCreditoMonedaLocal;
    private BigDecimal lineaCreditoMonedaExtranjera;

    //Información de Proveedor
    private boolean esProveedor;
    private boolean esFuncionario;
    @Lob
    private byte[] imagen;

    //Auditoria
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaRegitro;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaUltimaModificacion;

    private String usuarioUltimaModificacion;

    public boolean isEsProveedor() {
        return esProveedor;
    }

    public void setEsProveedor(boolean esProveedor) {
        this.esProveedor = esProveedor;
    }

    public boolean isEsFuncionario() {
        return esFuncionario;
    }

    public void setEsFuncionario(boolean esFuncionario) {
        this.esFuncionario = esFuncionario;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

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

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public Sucursal getSucursal() {
        return sucursal;
    }

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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public TipoContacto getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(TipoContacto tipoContacto) {
        this.tipoContacto = tipoContacto;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(String documentoId) {
        this.documentoId = documentoId;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLugarDeTrabajo() {
        return lugarDeTrabajo;
    }

    public void setLugarDeTrabajo(String lugarDeTrabajo) {
        this.lugarDeTrabajo = lugarDeTrabajo;
    }

    public boolean isEsCliente() {
        return esCliente;
    }

    public void setEsCliente(boolean esCliente) {
        this.esCliente = esCliente;
    }

    public boolean isEsExentoImpuesto() {
        return esExentoImpuesto;
    }

    public void setEsExentoImpuesto(boolean esExentoImpuesto) {
        this.esExentoImpuesto = esExentoImpuesto;
    }

    public Integer getTasaMaximaDescuentoVenta() {
        return tasaMaximaDescuentoVenta;
    }

    public void setTasaMaximaDescuentoVenta(Integer tasaMaximaDescuentoVenta) {
        this.tasaMaximaDescuentoVenta = tasaMaximaDescuentoVenta;
    }

    public BigDecimal getLineaCreditoMonedaLocal() {
        return lineaCreditoMonedaLocal;
    }

    public void setLineaCreditoMonedaLocal(BigDecimal lineaCreditoMonedaLocal) {
        this.lineaCreditoMonedaLocal = lineaCreditoMonedaLocal;
    }

    public BigDecimal getLineaCreditoMonedaExtranjera() {
        return lineaCreditoMonedaExtranjera;
    }

    public void setLineaCreditoMonedaExtranjera(BigDecimal lineaCreditoMonedaExtranjera) {
        this.lineaCreditoMonedaExtranjera = lineaCreditoMonedaExtranjera;
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
        if (!(object instanceof Contacto)) {
            return false;
        }
        Contacto other = (Contacto) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return nombre;
    }

}
