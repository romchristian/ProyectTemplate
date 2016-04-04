/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;


import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.stock.web.converters.UnidadMedidaConverter;
import com.ideaspymes.proyecttemplate.stock.model.UnidadMedida;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IUnidadMedidaDAO;
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
public class UnidadMedidaBean extends BeanGenerico<UnidadMedida> implements Serializable {

    @EJB
    private IUnidadMedidaDAO ejb;

    @Override
    public AbstractDAO<UnidadMedida> getEjb() {
        return ejb;
    }

    @Override
    public UnidadMedida getNuevo() {
        return new UnidadMedida();
    }

    @Override
    public Converter getConverter() {
        return new UnidadMedidaConverter();
    }

}
