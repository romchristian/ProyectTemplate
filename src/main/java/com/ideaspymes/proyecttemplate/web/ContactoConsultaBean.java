/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.web;

import com.ideaspymes.proyecttemplate.configuracion.model.Contacto;
import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IContactoDAO;
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
public class ContactoConsultaBean extends ConsultaGenerico<Contacto> {

    @EJB
    private IContactoDAO ejb;

    @Override
    public Class<Contacto> getClazz() {
        return Contacto.class;
    }

    @Override
    public AbstractDAO<Contacto> getEjb() {
        return ejb;
    }

    
}