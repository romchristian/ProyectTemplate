/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.model;

import com.ideaspymes.proyecttemplate.configuracion.model.Empresa;
import com.ideaspymes.proyecttemplate.configuracion.model.Sucursal;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.Filtro;
import com.ideaspymes.proyecttemplate.generico.FiltroGenerico;
import com.ideaspymes.proyecttemplate.generico.IAuditable;
import com.ideaspymes.proyecttemplate.generico.IConSucursal;
import com.ideaspymes.proyecttemplate.generico.Listado;
import com.ideaspymes.proyecttemplate.stock.enums.TipoDeposito;
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
public class Deposito implements Serializable, IAuditable, IConSucursal {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Long version;
    @Listado(descripcion = "Nombre", mostrar = true, link = true)
    @Filtro(campo = "nombre", descripcion = "Nombre", tipo = FiltroGenerico.TIPO_LIKE)
    private String nombre;
    @Listado(descripcion = "Nombre", mostrar = true, enumeracion = true, campoDescripcion = "label")
    @Enumerated(EnumType.STRING)
    private TipoDeposito tipoDeposito;

    @Listado(descripcion = "Nombre", mostrar = true)
    private String direccion;
    @Listado(descripcion = "Nombre", mostrar = true)
    private String telefono;

    @OneToMany(mappedBy = "deposito", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Ubicacion> ubicaciones;

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

    public Deposito() {
        tipoDeposito = TipoDeposito.NORMAL;
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

    public String getNombre() {
        return nombre;
    }

    public TipoDeposito getTipoDeposito() {
        return tipoDeposito;
    }

    public void setTipoDeposito(TipoDeposito tipoDeposito) {
        this.tipoDeposito = tipoDeposito;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Ubicacion> getUbicaciones() {
        if (ubicaciones == null) {
            ubicaciones = new ArrayList<>();
            Ubicacion det = new Ubicacion();
            det.setDeposito(this);
            det.setIndice(1);
            ubicaciones.add(det);
        }
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public void addDetalle() {
        Comparator<Ubicacion> comp = new Comparator<Ubicacion>() {
            @Override
            public int compare(Ubicacion o1, Ubicacion o2) {
                return o1.getIndice() > o2.getIndice() ? -1 : 1;
            }
        };

        Comparator<Ubicacion> comp2 = new Comparator<Ubicacion>() {
            @Override
            public int compare(Ubicacion o1, Ubicacion o2) {
                return o1.getIndice() > o2.getIndice() ? 1 : -1;
            }
        };

        Collections.sort(getUbicaciones(), comp);
        int lastIndice = 1;
        for (Ubicacion d : getUbicaciones()) {
            lastIndice = d.getIndice();
            break;
        }
        Ubicacion det = new Ubicacion();
        det.setDeposito(this);
        det.setIndice(lastIndice + 1);
        getUbicaciones().add(det);

        Collections.sort(getUbicaciones(), comp2);
    }

    public void removeDetalle(Ubicacion d) {
        int indice = 0;

        for (int i = 0; i < getUbicaciones().size(); i++) {
            Ubicacion dt = getUbicaciones().get(i);
            if (Objects.equals(d.getIndice(), dt.getIndice())) {
                indice = i;
                break;
            }
        }

        getUbicaciones().remove(indice);

        if (getUbicaciones().isEmpty()) {
            Ubicacion det = new Ubicacion();
            det.setDeposito(this);
            det.setIndice(1);
            getUbicaciones().add(det);

        }
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
        if (!(object instanceof Deposito)) {
            return false;
        }
        Deposito other = (Deposito) object;
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
