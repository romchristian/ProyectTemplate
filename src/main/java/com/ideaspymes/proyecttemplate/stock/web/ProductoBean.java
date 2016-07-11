/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;

import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.generico.IAuditable;
import com.ideaspymes.proyecttemplate.generico.JsfUtil;
import com.ideaspymes.proyecttemplate.stock.model.Deposito;
import com.ideaspymes.proyecttemplate.stock.model.Existencia;
import com.ideaspymes.proyecttemplate.stock.web.converters.ProductoConverter;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.model.Ubicacion;
import com.ideaspymes.proyecttemplate.stock.model.UnidadMedida;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.ICostoService;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IInventarioDAO;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IProductoDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author christian
 */
@Named
@ViewScoped
public class ProductoBean extends BeanGenerico<Producto> implements Serializable {

    @EJB
    private IProductoDAO ejb;
    @EJB
    private ICostoService costoService;
    @EJB
    private IInventarioDAO inventarioDAO;

    private String codigo;

    // Datos para actualización manual de stock
    private Deposito deposito;
    private Ubicacion ubicacion;
    private UnidadMedida unidadMedida;
    private Double cantidad;

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public AbstractDAO<Producto> getEjb() {
        return ejb;
    }

    @Override
    public Producto getNuevo() {

        return new Producto();
    }

    @Override
    public Converter getConverter() {
        return new ProductoConverter();
    }

    public Double obtCosto(Producto p) {
        return costoService.getCosto(p);
    }

    public List<Existencia> obtExistencias(Producto p) {
        List<Existencia> R = ejb.findExistenciasPorProducto(p);
        if (R == null) {
            R = new ArrayList<>();
        }
        return R;
    }

    public String buscarPorCodigo() {
        if (codigo == null) {
            return null;
        }

        Producto p = ejb.findPorCodigo(codigo);

        if (p == null) {
            return null;
        }

        return "/main/stock/producto/vista.xhtml?id=" + p.getId() + "&faces-redirect=true";
    }

    public String actualizarStock() {

        if (deposito == null) {
            JsfUtil.addErrorMessage("Debe elegir un lugar");
            return null;
        }

        if (ubicacion == null) {
            JsfUtil.addErrorMessage("Debe elegir una ubicación");
            return null;
        }

        if (unidadMedida == null) {
            JsfUtil.addErrorMessage("Debe elegir una unidad de medida");
            return null;
        }

        if (cantidad == null || cantidad == 0d) {
            JsfUtil.addErrorMessage("Debe ingresar una cantidad");
            return null;
        }

        if (inventarioDAO.actualizaInventario(deposito, ubicacion, unidadMedida, getActual(), cantidad)) {
            JsfUtil.addSuccessMessage("Se actualizó correctamente");
            return "/main/stock/producto/vista.xhtml?id=" + getActual().getId() + "&faces-redirect=true";
        } else {
            JsfUtil.addErrorMessage("No se actualizó correctamente");
            return null;
        }

    }

    @Override
    public String toggleActivacion() {

        if (getActual().getEstado() == Estado.ACTIVO && getActual().getStock() != null && getActual().getStock() > 0) {
            //JsfUtil.addErrorMessage("Para Inactivar, primero debe dar de baja todo el stock");
            List<Existencia> existencias = ejb.findExistenciasPorProducto(getActual());
            for(Existencia e: existencias){
                inventarioDAO.bajaInventario(e.getDeposito(), e.getUbicacion(), e.getUnidadMedida(), getActual(), e.getCantidad());
            }
            JsfUtil.addSuccessMessage("Se dió de baja el stock del producto");
            
            Producto productoResfresh = ejb.find(getActual().getId());
            productoResfresh.setEstado(Estado.INACTIVO);
            getEjb().edit(productoResfresh);
            
            return "listado.xhtml";
        } else if (getActual().getEstado() == Estado.ACTIVO && (getActual().getStock() == null || getActual().getStock() == 0)) {
            getActual().setEstado(Estado.INACTIVO);
            getEjb().edit(getActual());
        } else if (getActual().getEstado() == Estado.INACTIVO) {
            getActual().setEstado(Estado.ACTIVO);
            getEjb().edit(getActual());
        }

        return "listado.xhtml";
    }

}
