/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.stock.web.converters.LoteExistenciaConverter;
import com.ideaspymes.proyecttemplate.stock.model.LoteExistencia;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.ILoteExistenciaDAO;
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
public class LoteExistenciaBean extends BeanGenerico<LoteExistencia> implements Serializable {

    @EJB
    private ILoteExistenciaDAO ejb;

    @Override
    public AbstractDAO<LoteExistencia> getEjb() {
        return ejb;
    }

    @Override
    public LoteExistencia getNuevo() {
        return new LoteExistencia();
    }

    @Override
    public Converter getConverter() {
        return new LoteExistenciaConverter();
    }

}
