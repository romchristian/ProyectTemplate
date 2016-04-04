/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.web;

import com.ideaspymes.proyecttemplate.configuracion.model.TipoContacto;
import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.ITipoContactoDAO;
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
public class TipoContactoConsultaBean extends ConsultaGenerico<TipoContacto> {

    @EJB
    private ITipoContactoDAO ejb;

    @Override
    public Class<TipoContacto> getClazz() {
        return TipoContacto.class;
    }

    @Override
    public AbstractDAO<TipoContacto> getEjb() {
        return ejb;
    }

}
