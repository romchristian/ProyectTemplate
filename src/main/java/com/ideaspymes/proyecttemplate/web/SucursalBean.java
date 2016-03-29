/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.web;

import com.ideaspymes.proyecttemplate.configuracion.model.Sucursal;
import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.ISucursalDAO;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author christian
 */
@Named
@ViewScoped
public class SucursalBean extends BeanGenerico<Sucursal> implements Serializable {

    @EJB
    private ISucursalDAO ejb;

    @Override
    public AbstractDAO<Sucursal> getEjb() {
        return ejb;
    }

    @Override
    public Sucursal getNuevo() {
        return new Sucursal();
    }

}
