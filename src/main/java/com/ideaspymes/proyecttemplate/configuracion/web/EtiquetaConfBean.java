/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.web;

import com.ideaspymes.proyecttemplate.configuracion.model.EtiquetaConf;
import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IEtiquetaConfDAO;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.configuracion.web.converters.EtiquetaConfConverter;
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
public class EtiquetaConfBean extends BeanGenerico<EtiquetaConf> implements Serializable {

    @EJB
    private IEtiquetaConfDAO ejb;

    @Override
    public AbstractDAO<EtiquetaConf> getEjb() {
        return ejb;
    }

    @Override
    public EtiquetaConf getNuevo() {
        return new EtiquetaConf();
    }

    @Override
    public Converter getConverter() {
        return new EtiquetaConfConverter();
    }

}
