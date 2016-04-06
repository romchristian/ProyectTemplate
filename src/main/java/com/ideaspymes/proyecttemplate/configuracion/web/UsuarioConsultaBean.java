/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.web;

import com.ideaspymes.proyecttemplate.configuracion.model.Usuario;
import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IUsuarioDAO;
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
public class UsuarioConsultaBean extends ConsultaGenerico<Usuario> {

    @EJB
    private IUsuarioDAO ejb;

    @Override
    public Class<Usuario> getClazz() {
        return Usuario.class;
    }

    @Override
    public AbstractDAO<Usuario> getEjb() {
        return ejb;
    }

}
