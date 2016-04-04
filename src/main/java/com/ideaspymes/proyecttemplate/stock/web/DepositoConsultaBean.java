/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.ConsultaGenerico;
import com.ideaspymes.proyecttemplate.stock.model.Deposito;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IDepositoDAO;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Christian
 */
@Named
@ViewScoped
public class DepositoConsultaBean extends ConsultaGenerico<Deposito> {

    @EJB
    private IDepositoDAO ejb;

    @Override
    public Class<Deposito> getClazz() {
        return Deposito.class;
    }

    @Override
    public AbstractDAO<Deposito> getEjb() {
        return ejb;
    }

}
