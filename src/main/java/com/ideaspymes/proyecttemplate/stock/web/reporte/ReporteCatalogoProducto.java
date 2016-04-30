/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web.reporte;

import com.ideaspymes.proyecttemplate.generico.Reporte;
import com.ideaspymes.proyecttemplate.generico.ReporteGenerico;
import com.ideaspymes.proyecttemplate.stock.model.Existencia;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IProductoDAO;
import com.ideaspymes.proyecttemplate.stock.web.reporte.pojo.CatalogoProductos;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Acer
 */
@Named
@Reporte
@RequestScoped
public class ReporteCatalogoProducto extends ReporteGenerico<CatalogoProductos>{
    
    private CatalogoProductos catalogoProductos;
    
    @EJB
    private IProductoDAO dao;
    
    public CatalogoProductos getCatalogoProductos(){
        return this.catalogoProductos;
    }
    
    

    @Override
    public void cargaParams() {
        
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy", new Locale("es","PY"));
        
       getParams().put("fecha",date.format(new Date()));
    }

    @Override
    public List<CatalogoProductos> getDetalles() {
        ArrayList<CatalogoProductos> detalles = new ArrayList<>();
        
        List<Producto> productos = dao.findAll(); 
        
        for (Producto p : productos) {
            List<Existencia> existencias = dao.findExistenciasPorProducto(p);
            
            ArrayList<String> ubicaciones = new ArrayList<>();
            
            for (Existencia e : existencias) {
                ubicaciones.add(e.getCantidad()+" "+ e.getUnidadMedida().getNombre()+" en "+e.getDeposito().getNombre()+" - "+e.getUbicacion().getNombre());
            }
            detalles.add(new CatalogoProductos(p.getImagen(), p.getNombre(), p.getDescripcion(), ubicaciones, p.getStock()));
        }
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
    
}
