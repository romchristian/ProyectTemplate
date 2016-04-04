/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.web;

import com.ideaspymes.proyecttemplate.configuracion.model.Sucursal;
import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.ISucursalDAO;
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
public class SucursalConsultaBean extends ConsultaGenerico<Sucursal> {

    @EJB
    private ISucursalDAO ejb;

    @Override
    public Class<Sucursal> getClazz() {
        return Sucursal.class;
    }

    @Override
    public AbstractDAO<Sucursal> getEjb() {
        return ejb;
    }

}
