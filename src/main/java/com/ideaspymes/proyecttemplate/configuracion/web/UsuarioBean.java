/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.web;

import com.ideaspymes.proyecttemplate.configuracion.model.Usuario;
import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IUsuarioDAO;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.configuracion.web.converters.UsuarioConverter;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author christian
 */
@Named
@ViewScoped
public class UsuarioBean extends BeanGenerico<Usuario> implements Serializable {

    @EJB
    private IUsuarioDAO ejb;
    private String password1;
    private String password2;

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    @Override
    public AbstractDAO<Usuario> getEjb() {
        return ejb;
    }

    @Override
    public Usuario getNuevo() {
        return new Usuario();
    }

    @Override
    public Converter getConverter() {
        return new UsuarioConverter();
    }

    public String cambiarClave() {
        getCredencial().getUsuario().setPassword(password1);
        ejb.edit(getCredencial().getUsuario());
        return "/main/stock/home";
    }

}
