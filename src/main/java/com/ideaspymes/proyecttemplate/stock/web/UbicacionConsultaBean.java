/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.ConsultaGenerico;
import com.ideaspymes.proyecttemplate.stock.model.Ubicacion;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IUbicacionDAO;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Christian
 */
@Named
@ViewScoped
public class UbicacionConsultaBean extends ConsultaGenerico<Ubicacion> {

    @EJB
    private IUbicacionDAO ejb;

    @Override
    public Class<Ubicacion> getClazz() {
        return Ubicacion.class;
    }

    @Override
    public AbstractDAO<Ubicacion> getEjb() {
        return ejb;
    }

}
