/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.ConsultaGenerico;
import com.ideaspymes.proyecttemplate.stock.model.LoteExistencia;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.ILoteExistenciaDAO;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Christian
 */
@Named
@ViewScoped
public class LoteExistenciaConsultaBean extends ConsultaGenerico<LoteExistencia> {

    @EJB
    private ILoteExistenciaDAO ejb;

    @Override
    public Class<LoteExistencia> getClazz() {
        return LoteExistencia.class;
    }

    @Override
    public AbstractDAO<LoteExistencia> getEjb() {
        return ejb;
    }

}
