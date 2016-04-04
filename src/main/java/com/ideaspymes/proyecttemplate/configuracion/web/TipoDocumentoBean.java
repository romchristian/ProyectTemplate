/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.web;

import com.ideaspymes.proyecttemplate.configuracion.model.TipoDocumento;
import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.ITipoDocumentoDAO;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.configuracion.web.converters.TipoDocumentoConverter;
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
public class TipoDocumentoBean extends BeanGenerico<TipoDocumento> implements Serializable {

    @EJB
    private ITipoDocumentoDAO ejb;

    @Override
    public AbstractDAO<TipoDocumento> getEjb() {
        return ejb;
    }

    @Override
    public TipoDocumento getNuevo() {
        return new TipoDocumento();
    }

    @Override
    public Converter getConverter() {
        return new TipoDocumentoConverter();
    }

}
