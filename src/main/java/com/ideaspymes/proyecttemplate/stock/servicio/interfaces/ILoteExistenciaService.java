/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.interfaces;

import com.ideaspymes.proyecttemplate.stock.model.LoteExistencia;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.model.UnidadMedida;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Acer
 */


@Local
public interface ILoteExistenciaService {
    public LoteExistencia creaLoteExitencia(LoteExistencia l);
    public LoteExistencia guardaLoteExitencia(LoteExistencia l);
    public void afectaLotesExistenciaMasCovenientes(Producto p, UnidadMedida um, Double cantidad);
    public Double afectaCantidadUsadaLoteExitencia(LoteExistencia l, Double cantidad);
    public List<LoteExistencia>  getLotesExitenciaMasAntiguos(Producto p , UnidadMedida um, Double cantidad);
    public List<LoteExistencia>  getLotesExitenciaMasRecientes(Producto p , UnidadMedida um, Double cantidad);
    public List<LoteExistencia>  getLotesExitenciaVencimientosMasProximos(Producto p , UnidadMedida um, Double cantidad);
    public Double getCostoPonderado(Producto p);
    public Double getCostoLifo(Producto p);
    public Double getCostoFifo(Producto p);
}
