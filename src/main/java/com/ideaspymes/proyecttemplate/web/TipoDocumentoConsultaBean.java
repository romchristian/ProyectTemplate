/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.web;

import com.ideaspymes.proyecttemplate.configuracion.model.TipoDocumento;
import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.ITipoDocumentoDAO;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.ConsultaGenerico;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Christian
 */
@Named
@ViewScoped
public class TipoDocumentoConsultaBean extends ConsultaGenerico<TipoDocumento> {

    @EJB
    private ITipoDocumentoDAO ejb;

    @Override
    public Class<TipoDocumento> getClazz() {
        return TipoDocumento.class;
    }

    @Override
    public AbstractDAO<TipoDocumento> getEjb() {
        return ejb;
    }

}
