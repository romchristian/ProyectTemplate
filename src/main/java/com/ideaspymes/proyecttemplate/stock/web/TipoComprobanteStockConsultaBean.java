/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.ConsultaGenerico;
import com.ideaspymes.proyecttemplate.stock.model.TipoComprobanteStock;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.ITipoComprobanteStockDAO;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Christian
 */
@Named
@ViewScoped
public class TipoComprobanteStockConsultaBean extends ConsultaGenerico<TipoComprobanteStock> {

    @EJB
    private ITipoComprobanteStockDAO ejb;

    @Override
    public Class<TipoComprobanteStock> getClazz() {
        return TipoComprobanteStock.class;
    }

    @Override
    public AbstractDAO<TipoComprobanteStock> getEjb() {
        return ejb;
    }

    @Override
    public String getPath() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNombreReporte() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cargaParams() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
