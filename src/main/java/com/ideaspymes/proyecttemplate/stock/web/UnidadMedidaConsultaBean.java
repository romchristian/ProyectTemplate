/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.ConsultaGenerico;
import com.ideaspymes.proyecttemplate.stock.model.UnidadMedida;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IUnidadMedidaDAO;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Christian
 */
@Named
@ViewScoped
public class UnidadMedidaConsultaBean extends ConsultaGenerico<UnidadMedida> {

    @EJB
    private IUnidadMedidaDAO ejb;

    @Override
    public Class<UnidadMedida> getClazz() {
        return UnidadMedida.class;
    }

    @Override
    public AbstractDAO<UnidadMedida> getEjb() {
        return ejb;
    }

}
