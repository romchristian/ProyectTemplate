/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.generico;

import com.ideaspymes.proyecttemplate.configuracion.model.Empresa;
import com.ideaspymes.proyecttemplate.configuracion.model.Usuario;
import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IEmpresaDAO;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 *
 * @author cromero
 */
@Named
@SessionScoped
public class Credencial implements Serializable {

    private Usuario usuario;
    private Empresa empresa;
    @EJB
    private IEmpresaDAO empresaDAO;

    @Produces
    @UsuarioLogueado
    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Produces
    @EmpresaSeleccionada
    public Empresa getEmpresa() {
        if (empresa == null && usuario != null) {
            empresa = empresaDAO.getEmpresaDefault(usuario);
        }
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}
