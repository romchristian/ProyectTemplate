/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.model;

import com.ideaspymes.proyecttemplate.configuracion.model.Empresa;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.Filtro;
import com.ideaspymes.proyecttemplate.generico.FiltroGenerico;
import com.ideaspymes.proyecttemplate.generico.IAuditable;
import com.ideaspymes.proyecttemplate.generico.Listado;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Acer
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"producto_id", "unidadmedidade_id", "unidadmedidaa_id"}))
public class ProductoUnidadMedida implements Serializable, IAuditable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @Listado(descripcion = "Producto", mostrar = true, entidad = true, campoDescripcion = "nombre", link = true, modulo = "stock")
    @Filtro(descripcion = "Producto", campo = "producto", campoDescripcion = "nombre", tipo = FiltroGenerico.TIPO_AUTOCOMPLETE)
    private Producto producto;
    @ManyToOne
    @Listado(descripcion = "U.M. De", mostrar = true, entidad = true, campoDescripcion = "nombre", modulo = "stock", campo = "unidadMedidaDe", outcome = "/main/stock/unidadMedida/vista")
    //@Filtro(descripcion = "U.M. De",campo = "unidadMedidaDe",campoDescripcion = "nombre",tipo = "selectOne")
    private UnidadMedida unidadMedidaDe;
    @ManyToOne
    @Listado(descripcion = "U.M. A", mostrar = true, entidad = true, campoDescripcion = "nombre", modulo = "stock", campo = "unidadMedidaA", outcome = "/main/stock/unidadMedida/vista")
    //@Filtro(descripcion = "U.M. A",campo = "unidadMedidaA",campoDescripcion = "nombre",tipo = "selectOne")
    private UnidadMedida unidadMedidaA;
    @Listado(descripcion = "Formula", mostrar = true)
    private String formula;

    @ManyToOne
    @Listado(descripcion = "Empresa", mostrar = true, entidad = true, campoDescripcion = "nombre", modulo = "configuracion")
    private Empresa empresa;

    //Auditoria
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaRegitro;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaUltimaModificacion;
    private String formulaDescoversion;

    private String usuarioUltimaModificacion;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormulaDescoversion() {
        return formulaDescoversion;
    }

    public void setFormulaDescoversion(String formulaDescoversion) {
        this.formulaDescoversion = formulaDescoversion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public UnidadMedida getUnidadMedidaDe() {
        return unidadMedidaDe;
    }

    public void setUnidadMedidaDe(UnidadMedida unidadMedidaDe) {
        this.unidadMedidaDe = unidadMedidaDe;
    }

    public UnidadMedida getUnidadMedidaA() {
        return unidadMedidaA;
    }

    public void setUnidadMedidaA(UnidadMedida unidadMedidaA) {
        this.unidadMedidaA = unidadMedidaA;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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
        if (!(object instanceof ProductoUnidadMedida)) {
            return false;
        }
        ProductoUnidadMedida other = (ProductoUnidadMedida) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.ideaspymes.facilerp.stock.persistencia.ProductoUnidadMedida[ id=" + id + " ]";
    }

}
