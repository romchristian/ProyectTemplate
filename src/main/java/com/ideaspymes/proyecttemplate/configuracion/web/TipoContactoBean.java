/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.web;

import com.ideaspymes.proyecttemplate.configuracion.model.TipoContacto;
import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.ITipoContactoDAO;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.configuracion.web.converters.TipoContactoConverter;
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
public class TipoContactoBean extends BeanGenerico<TipoContacto> implements Serializable {

    @EJB
    private ITipoContactoDAO ejb;

    @Override
    public AbstractDAO<TipoContacto> getEjb() {
        return ejb;
    }

    @Override
    public TipoContacto getNuevo() {
        return new TipoContacto();
    }

    @Override
    public Converter getConverter() {
        return new TipoContactoConverter();
    }

}
