/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.model;

import com.ideaspymes.proyecttemplate.configuracion.model.Empresa;
import com.ideaspymes.proyecttemplate.configuracion.model.Impuesto;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.Filtro;
import com.ideaspymes.proyecttemplate.generico.FiltroGenerico;
import com.ideaspymes.proyecttemplate.generico.IAuditable;
import com.ideaspymes.proyecttemplate.generico.IConImagen;
import com.ideaspymes.proyecttemplate.generico.Listado;
import com.ideaspymes.proyecttemplate.stock.enums.TipoCosto;
import com.ideaspymes.proyecttemplate.stock.enums.TipoProducto;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author christian
 */
@Entity
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"codigo"}))
public class Producto implements Serializable, IAuditable, IConImagen {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Long version;
    @Listado(descripcion = "Código", mostrar = true, link = true)
    @Filtro(descripcion = "Código", campo = "codigo", tipo = "like")
    private String codigo;
    @Listado(descripcion = "Nombre", mostrar = true, link = true)
    @Filtro(descripcion = "Nombre", campo = "nombre", tipo = "like")
    private String nombre;
    private String descripcion;
    @Enumerated(EnumType.STRING)
    private TipoProducto tipoProducto;
    @ManyToOne
    private Impuesto impuesto;
    @Enumerated(EnumType.STRING)
    private TipoCosto tipoCosto;
    private Boolean costoCalculado;
    private Double costoDirecto;
    private Double costoFifo;
    private Double costoLifo;
    private Double costoPonderado;
    private Double costo;
    private Double precioVenta;
    private Double precioVentaMonedaExtranjera;
    @Listado(descripcion = "Inventariable", mostrar = true)
    private Boolean inventariable = true;
    private Boolean vendible = false;
    @Listado(descripcion = "Tiene Vencimiento", mostrar = true)
    private Boolean tieneVencimiento;
    private Double stockMinimo;
    @Listado(descripcion = "Stock", mostrar = true)
    private Double stock;
    @Transient
    private Integer cantidadEtiquetas;

    @ManyToOne
    private UnidadMedida unidadMedidaBase;

    private Boolean generarCodigo;

    @ManyToOne
    @Listado(descripcion = "Familia", mostrar = true, campo = "familia", campoDescripcion = "nombre", entidad = true, modulo = "stock")
    @Filtro(descripcion = "Familia", campo = "familia", tipo = FiltroGenerico.TIPO_SELECT_ONE, campoDescripcion = "nombre")
    private Familia familia;

    @Lob
    private byte[] imagen;
    @OneToMany(mappedBy = "productoPadre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingrediente> ingredientes;

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

    private String usuarioUltimaModificacion;

    @Transient
    private boolean imprimirEtiqueta;

    public Producto() {
        this.estado = Estado.ACTIVO;
    }

    public Producto(Long id, String codigo, String nombre) {
        this();
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Producto(Long id, String codigo, String nombre, Impuesto iva, Double precioVenta) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.impuesto = iva;
        this.precioVenta = precioVenta;
    }

    public Integer getCantidadEtiquetas() {
        if (cantidadEtiquetas == null) {
            cantidadEtiquetas = stock != null ? stock.intValue() : 1;
        }
        return cantidadEtiquetas;
    }

    public void setCantidadEtiquetas(Integer cantidadEtiquetas) {
        this.cantidadEtiquetas = cantidadEtiquetas;
    }

    public boolean isImprimirEtiqueta() {
        return imprimirEtiqueta;
    }

    public void setImprimirEtiqueta(boolean imprimirEtiqueta) {
        this.imprimirEtiqueta = imprimirEtiqueta;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Boolean getCostoCalculado() {
        return costoCalculado;
    }

    public void setCostoCalculado(Boolean costoCalculado) {
        this.costoCalculado = costoCalculado;
    }

    public Double getCostoDirecto() {
        return costoDirecto;
    }

    public void setCostoDirecto(Double costoDirecto) {
        this.costoDirecto = costoDirecto;
    }

    public Double getCostoFifo() {
        return costoFifo;
    }

    public void setCostoFifo(Double costoFifo) {
        this.costoFifo = costoFifo;
    }

    public Double getCostoLifo() {
        return costoLifo;
    }

    public void setCostoLifo(Double costoLifo) {
        this.costoLifo = costoLifo;
    }

    public Double getCostoPonderado() {
        return costoPonderado;
    }

    public void setCostoPonderado(Double costoPonderado) {
        this.costoPonderado = costoPonderado;
    }

    public Boolean getInventariable() {
        return inventariable;
    }

    public void setInventariable(Boolean inventariable) {
        this.inventariable = inventariable;
    }

    public Impuesto getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuesto iva) {
        this.impuesto = iva;
    }

    public TipoCosto getTipoCosto() {
        return tipoCosto;
    }

    public void setTipoCosto(TipoCosto tipoCosto) {
        this.tipoCosto = tipoCosto;
    }

    public Double getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Double stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public UnidadMedida getUnidadMedidaBase() {
        return unidadMedidaBase;
    }

    public void setUnidadMedidaBase(UnidadMedida unidadMedidaBase) {
        this.unidadMedidaBase = unidadMedidaBase;
    }

    public Boolean getGenerarCodigo() {
        return generarCodigo;
    }

    public void setGenerarCodigo(Boolean generarCodigo) {
        this.generarCodigo = generarCodigo;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Double getPrecioVentaMonedaExtranjera() {
        return precioVentaMonedaExtranjera;
    }

    public void setPrecioVentaMonedaExtranjera(Double precioVentaMonedaExtranjera) {
        this.precioVentaMonedaExtranjera = precioVentaMonedaExtranjera;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public Boolean getVendible() {
        return vendible;
    }

    public void setVendible(Boolean vendible) {
        this.vendible = vendible;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public Boolean getTieneVencimiento() {
        return tieneVencimiento;
    }

    public void setTieneVencimiento(Boolean tieneVencimiento) {
        this.tieneVencimiento = tieneVencimiento;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.stock.Producto[id=" + id + "]";
    }
}
