/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.ConsultaGenerico;
import com.ideaspymes.proyecttemplate.stock.model.MovimientoStock;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IMovimientoStockDAO;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Christian
 */
@Named
@ViewScoped
public class MovimientoStockConsultaBean extends ConsultaGenerico<MovimientoStock> {

    @EJB
    private IMovimientoStockDAO ejb;

    @Override
    public Class<MovimientoStock> getClazz() {
        return MovimientoStock.class;
    }

    @Override
    public AbstractDAO<MovimientoStock> getEjb() {
        return ejb;
    }

}
