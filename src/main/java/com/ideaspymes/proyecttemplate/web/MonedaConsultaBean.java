/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.web;

import com.ideaspymes.proyecttemplate.configuracion.model.Moneda;
import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IMonedaDAO;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.ConsultaGenerico;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Christian
 */
@Named
@ViewScoped
public class MonedaConsultaBean extends ConsultaGenerico<Moneda> {

    @EJB
    private IMonedaDAO ejb;

    @Override
    public Class<Moneda> getClazz() {
        return Moneda.class;
    }

    @Override
    public AbstractDAO<Moneda> getEjb() {
        return ejb;
    }

}
