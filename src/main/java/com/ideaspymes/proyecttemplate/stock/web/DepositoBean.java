/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;


import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.stock.web.converters.DepositoConverter;
import com.ideaspymes.proyecttemplate.stock.model.Deposito;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IDepositoDAO;
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
public class DepositoBean extends BeanGenerico<Deposito> implements Serializable {

    @EJB
    private IDepositoDAO ejb;

    @Override
    public AbstractDAO<Deposito> getEjb() {
        return ejb;
    }

    @Override
    public Deposito getNuevo() {
        return new Deposito();
    }

    @Override
    public Converter getConverter() {
        return new DepositoConverter();
    }
   
}
