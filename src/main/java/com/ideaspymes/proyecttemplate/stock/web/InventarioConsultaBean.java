/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.ConsultaGenerico;
import com.ideaspymes.proyecttemplate.stock.model.Inventario;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IInventarioDAO;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Christian
 */
@Named
@ViewScoped
public class InventarioConsultaBean extends ConsultaGenerico<Inventario> {

    @EJB
    private IInventarioDAO ejb;

    @Override
    public Class<Inventario> getClazz() {
        return Inventario.class;
    }

    @Override
    public AbstractDAO<Inventario> getEjb() {
        return ejb;
    }

}
