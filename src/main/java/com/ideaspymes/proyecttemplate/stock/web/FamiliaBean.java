/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;


import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.stock.web.converters.FamiliaConverter;
import com.ideaspymes.proyecttemplate.stock.model.Familia;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IFamiliaDAO;
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
public class FamiliaBean extends BeanGenerico<Familia> implements Serializable {

    @EJB
    private IFamiliaDAO ejb;

    @Override
    public AbstractDAO<Familia> getEjb() {
        return ejb;
    }

    @Override
    public Familia getNuevo() {
        return new Familia();
    }

    @Override
    public Converter getConverter() {
        return new FamiliaConverter();
    }

}
