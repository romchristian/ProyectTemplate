/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web.reporte;

import com.ideaspymes.proyecttemplate.generico.Reporte;
import com.ideaspymes.proyecttemplate.generico.ReporteGenerico;
import com.ideaspymes.proyecttemplate.stock.model.ComprobanteStock;
import com.ideaspymes.proyecttemplate.stock.model.DetComprobanteStock;
import com.ideaspymes.proyecttemplate.stock.web.reporte.pojo.DetCompStock;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Acer
 */
@Named
@Reporte
@RequestScoped
public class ReporteComprobanteStock extends ReporteGenerico<DetCompStock> {

    private ComprobanteStock comprobanteStock;

    public ComprobanteStock getComprobanteStock() {
        return comprobanteStock;
    }

    public void setComprobanteStock(ComprobanteStock comprobanteStock) {
        this.comprobanteStock = comprobanteStock;
    }

    @Override
    public void cargaParams() {
        getParams().put("nroComprobante", comprobanteStock.getId()+"");
        getParams().put("responsable", comprobanteStock.getResposable().getNombre());
        getParams().put("contacto", comprobanteStock.getContacto().getNombre());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        getParams().put("fecha", sdf.format(comprobanteStock.getFecha()));
        getParams().put("deposito", comprobanteStock.getDepositoPivot().getNombre());
        getParams().put("estado", comprobanteStock.getEstadoComprobate().getLabel());
    }

    @Override
    public List<DetCompStock> getDetalles() {
        List<DetCompStock> detalles = new ArrayList<>();
        for (DetComprobanteStock d : comprobanteStock.getDetalles()) {
            detalles.add(new DetCompStock(d.getIndice(), d.getProducto().getNombre(), d.getCantidad(), d.getUnidadMedida().getNombre()));
        }

        return detalles;
    }

    @Override
    public String getPath() {
        return "reportes/stock/compStock.jasper";
    }

    @Override
    public String getNombre() {
        return "comprobate_stock_nro_" + comprobanteStock.getId();
    }
}
