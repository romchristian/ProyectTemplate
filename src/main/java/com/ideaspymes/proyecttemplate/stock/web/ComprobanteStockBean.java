/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.generico.JsfUtil;
import com.ideaspymes.proyecttemplate.generico.Reporte;
import com.ideaspymes.proyecttemplate.stock.exception.SinStockException;
import com.ideaspymes.proyecttemplate.stock.web.converters.ComprobanteStockConverter;
import com.ideaspymes.proyecttemplate.stock.model.ComprobanteStock;
import com.ideaspymes.proyecttemplate.stock.model.DetComprobanteStock;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IComprobanteStockDAO;
import com.ideaspymes.proyecttemplate.stock.web.reporte.ReporteComprobanteStock;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author christian
 */
@Named
@ViewScoped
public class ComprobanteStockBean extends BeanGenerico<ComprobanteStock> implements Serializable {

    @Inject
    @Reporte
    private ReporteComprobanteStock reporteComprobanteStock;

    @EJB
    private IComprobanteStockDAO ejb;
    private Producto productoElegido;

    @Override
    public AbstractDAO<ComprobanteStock> getEjb() {
        return ejb;
    }

    @Override
    public ComprobanteStock getNuevo() {
        ComprobanteStock R = new ComprobanteStock();
        R.setFecha(new Date());
        R.setResposable(getCredencial().getUsuario());
        return R;
    }

    @Override
    public Converter getConverter() {
        return new ComprobanteStockConverter();
    }

    @Override
    public String create() {
        limpiarDetallesVacios();
        return super.create();
    }

    public void confirmar() {

        try {
            limpiarDetallesVacios();
            ejb.confirmar(getActual());
        } catch (SinStockException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
            Logger.getLogger(ComprobanteStockBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void imprimir() {
        reporteComprobanteStock.setComprobanteStock(getActual());
        reporteComprobanteStock.generar();
    }

    public Producto getProductoElegido() {
        return productoElegido;
    }

    public void setProductoElegido(Producto productoElegido) {
        this.productoElegido = productoElegido;
    }

    public void limpiarDetallesVacios() {

        Iterator<DetComprobanteStock> it = getActual().getDetalles().iterator();

        while (it.hasNext()) {
            DetComprobanteStock d = it.next();
            if (d.getProducto() == null) {
                it.remove();
            }
        }
    }
}
