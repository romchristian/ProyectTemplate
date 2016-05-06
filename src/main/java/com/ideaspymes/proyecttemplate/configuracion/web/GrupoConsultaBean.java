/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.web;

import com.ideaspymes.proyecttemplate.configuracion.model.Grupo;
import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IGrupoDAO;
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
public class GrupoConsultaBean extends ConsultaGenerico<Grupo> {

    @EJB
    private IGrupoDAO ejb;

    @Override
    public Class<Grupo> getClazz() {
        return Grupo.class;
    }

    @Override
    public AbstractDAO<Grupo> getEjb() {
        return ejb;
    }

}
