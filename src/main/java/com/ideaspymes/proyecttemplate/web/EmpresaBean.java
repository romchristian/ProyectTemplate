/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.web;


import com.ideaspymes.proyecttemplate.configuracion.model.Empresa;
import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IEmpresaDAO;
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
public class EmpresaBean extends BeanGenerico<Empresa> implements Serializable{

    @EJB private IEmpresaDAO ejb;
    
    
    @Override
    public AbstractDAO<Empresa> getEjb() {
        return ejb;
    }

    @Override
    public Empresa getNuevo() {
        return new Empresa();
    }

    
}
