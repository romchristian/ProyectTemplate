/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web.reporte;

import com.ideaspymes.proyecttemplate.generico.Credencial;
import com.ideaspymes.proyecttemplate.generico.Reporte;
import com.ideaspymes.proyecttemplate.generico.ReporteGenerico;
import com.ideaspymes.proyecttemplate.stock.model.Deposito;
import com.ideaspymes.proyecttemplate.stock.model.Existencia;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.model.Ubicacion;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IProductoDAO;
import com.ideaspymes.proyecttemplate.stock.web.reporte.pojo.CatalogoProductos;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Acer
 */
@Named
@Reporte
@ViewScoped
public class ReporteCatalogoProducto extends ReporteGenerico<CatalogoProductos> implements Serializable {

    private CatalogoProductos catalogoProductos;
    private Deposito deposito;
    private Ubicacion ubicacion;
    @Inject
    private Credencial credencial;

    @EJB
    private IProductoDAO dao;

    public CatalogoProductos getCatalogoProductos() {
        return this.catalogoProductos;
    }

    @Override
    public void cargaParams() {

        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy", new Locale("es", "PY"));

        getParams().put("fecha", date.format(new Date()));
        getParams().put("ubicacion", (ubicacion != null ? ubicacion.getNombre() : "todos"));
        getParams().put("deposito", (deposito != null ? deposito.getNombre() : "todos"));
        getParams().put("usuario", credencial.getUsuario().getUserName());
    }

    @Override
    public List<CatalogoProductos> getDetalles() {
        ArrayList<CatalogoProductos> detalles = new ArrayList<>();

        List<Existencia> existencia = dao.findExistenciaPorDeposito(deposito, ubicacion);

        Map<Producto, List<Existencia>> mapa = new HashMap<>();

        for (Existencia e : existencia) {
            List<Existencia> lista = mapa.get(e.getProducto());
            if (lista == null) {
                lista = new ArrayList<>();
                lista.add(e);
                mapa.put(e.getProducto(), lista);
            } else {
                lista.add(e);
            }
        }
        for (Map.Entry<Producto, List<Existencia>> entry : mapa.entrySet()) {
            Producto p = entry.getKey();
            List<Existencia> value = entry.getValue();
            String ubicaciones = "";
            double stock = 0;
            System.out.println("Existencias: " + value);
            for (Existencia e : value) {
                ubicaciones += e.getCantidad() + " " + e.getUnidadMedida().getNombre() + " en " + e.getDeposito().getNombre() + " - " + e.getUbicacion().getNombre() + "\n";
                stock += e.getCantidad();
            }
            detalles.add(new CatalogoProductos(p.getImagen(), p.getNombre(), p.getDescripcion(), ubicaciones, stock, p.getCodigo(), p.getFamilia() != null ? p.getFamilia().getNombre() : "No definido"));
        }

        System.out.println("Detalles:  " + detalles);

        return detalles;
    }

    @Override
    public String getPath() {
        return "reportes/stock/CatalogoProducto.jasper";
    }

    @Override
    public String getNombre() {
        return "catalogo";
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

}
