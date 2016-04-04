/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.web;

import com.ideaspymes.proyecttemplate.configuracion.model.Contacto;
import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IContactoDAO;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.configuracion.web.converters.ContactoConverter;
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
public class ContactoBean extends BeanGenerico<Contacto> implements Serializable {

    @EJB
    private IContactoDAO ejb;

    @Override
    public AbstractDAO<Contacto> getEjb() {
        return ejb;
    }

    @Override
    public Contacto getNuevo() {
        return new Contacto();
    }

    @Override
    public Converter getConverter() {
        return new ContactoConverter();
    }

}
