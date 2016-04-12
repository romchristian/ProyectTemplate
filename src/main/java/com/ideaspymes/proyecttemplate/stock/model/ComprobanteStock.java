/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.model;

import com.ideaspymes.proyecttemplate.configuracion.model.Contacto;
import com.ideaspymes.proyecttemplate.configuracion.model.Empresa;
import com.ideaspymes.proyecttemplate.configuracion.model.Sucursal;
import com.ideaspymes.proyecttemplate.configuracion.model.Usuario;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.Filtro;
import com.ideaspymes.proyecttemplate.generico.IAuditable;
import com.ideaspymes.proyecttemplate.generico.IConSucursal;
import com.ideaspymes.proyecttemplate.generico.Listado;
import com.ideaspymes.proyecttemplate.stock.enums.EstadoComprobanteStock;
import com.ideaspymes.proyecttemplate.stock.enums.TipoComprobanteStock;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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

    @OneToMany(mappedBy = "comprobanteStock", cascade = CascadeType.ALL)
    private List<DetComprobanteStock> detalles;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Listado(descripcion = "Nro.", mostrar = true, link = true)
    private Long id;
    @Version
    private Long version;
    @Enumerated(EnumType.STRING)
    private TipoComprobanteStock tipo;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Listado(descripcion = "Fecha", mostrar = true, link = true)
    @Filtro(descripcion = "Fecha", campo = "fecha", tipo = "rangoFecha")
    private Date fecha;
    private String refOrigen;// Debe ser Entidad:id ej.: "Cliente:123" o "Deposito:1":
    private String refDestino;

    private String refDocumento;
    @ManyToOne
    private Empresa empresa;
    @ManyToOne
    private Sucursal sucursal;

    @ManyToOne
    private Deposito origen;
    @ManyToOne
    private Deposito destino;
    @ManyToOne
    private Usuario resposable;

    @Listado(descripcion = "Estado Comprobate", mostrar = true,entidad = true, campoDescripcion = "label")
    @Enumerated(EnumType.STRING)
    private EstadoComprobanteStock estadoComprobate;

    //Auditoria
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaRegitro;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaUltimaModificacion;

    private String usuarioUltimaModificacion;

    public ComprobanteStock() {
        this.estadoComprobate = EstadoComprobanteStock.PENDIENTE_CONFIRMACION;
    }

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

    public Usuario getResposable() {
        return resposable;
    }

    public void setResposable(Usuario resposable) {
        this.resposable = resposable;
    }

    public EstadoComprobanteStock getEstadoComprobate() {
        return estadoComprobate;
    }

    public void setEstadoComprobate(EstadoComprobanteStock estadoComprobate) {
        this.estadoComprobate = estadoComprobate;
    }

    public List<DetComprobanteStock> getDetalles() {
        if (detalles == null) {
            detalles = new ArrayList<>();
            DetComprobanteStock det = new DetComprobanteStock();
            det.setComprobanteStock(this);
            det.setIndice(1);
            detalles.add(det);
        }
        return detalles;
    }

    public void addDetalle() {
        Comparator<DetComprobanteStock> comp = new Comparator<DetComprobanteStock>() {
            @Override
            public int compare(DetComprobanteStock o1, DetComprobanteStock o2) {
                return o1.getIndice() > o2.getIndice() ? -1 : 1;
            }
        };

        Comparator<DetComprobanteStock> comp2 = new Comparator<DetComprobanteStock>() {
            @Override
            public int compare(DetComprobanteStock o1, DetComprobanteStock o2) {
                return o1.getIndice() > o2.getIndice() ? 1 : -1;
            }
        };

        Collections.sort(detalles, comp);
        int lastIndice = 1;
        for (DetComprobanteStock d : detalles) {
            lastIndice = d.getIndice();
            break;
        }
        DetComprobanteStock det = new DetComprobanteStock();
        det.setComprobanteStock(this);
        det.setIndice(lastIndice + 1);
        detalles.add(det);

        Collections.sort(detalles, comp2);
    }

    public void removeDetalle(DetComprobanteStock d) {
        int indice = 0;

        for (int i = 0; i < detalles.size(); i++) {
            DetComprobanteStock dt = detalles.get(i);
            if (Objects.equals(d.getIndice(), dt.getIndice())) {
                indice = i;
                break;
            }
        }

        detalles.remove(indice);

        if (detalles.isEmpty()) {
            DetComprobanteStock det = new DetComprobanteStock();
            det.setComprobanteStock(this);
            det.setIndice(1);
            detalles.add(det);

        }
    }

    public void setDetalles(List<DetComprobanteStock> detalles) {
        this.detalles = detalles;
    }

    public Deposito getOrigen() {
        return origen;
    }

    public void setOrigen(Deposito origen) {
        this.origen = origen;
    }

    public Deposito getDestino() {
        return destino;
    }

    public void setDestino(Deposito destino) {
        this.destino = destino;
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
