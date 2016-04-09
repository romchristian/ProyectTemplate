/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.web;

import com.ideaspymes.proyecttemplate.configuracion.model.Cotizacion;
import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.ICotizacionDAO;
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
public class CotizacionConsultaBean extends ConsultaGenerico<Cotizacion> {

    @EJB
    private ICotizacionDAO ejb;

    @Override
    public Class<Cotizacion> getClazz() {
        return Cotizacion.class;
    }

    @Override
    public AbstractDAO<Cotizacion> getEjb() {
        return ejb;
    }

}
