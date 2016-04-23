/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.impl;

import com.ideaspymes.proyecttemplate.stock.model.LoteExistencia;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.ICostoService;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.ILoteExistenciaDAO;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.ProductoCosto;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Elias
 */
@Stateless
public class CostoService implements ICostoService {

    @EJB
    private ILoteExistenciaDAO loteDAO;

    @Override
    public Double getCosto(Producto p) {
        Double R = 0d;
        
        
        List<LoteExistencia> lotes = loteDAO.findAllPorProducto(p);
        
        Double saldoTotal = 0d;
        for(LoteExistencia l: lotes){
            saldoTotal += l.getCantidadSaldoStock();
        }
        
        for(LoteExistencia l: lotes){
            Double ponderacion = l.getCantidadSaldoStock() / saldoTotal;
            Double costoProrrateo = l.getCostoUnitario() * ponderacion;
            R += costoProrrateo;
        }
        
        
        return R;
    }

    @Override
    public List<ProductoCosto> getCostos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
