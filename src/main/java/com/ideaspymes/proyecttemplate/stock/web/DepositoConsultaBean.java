/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.ConsultaGenerico;
import com.ideaspymes.proyecttemplate.generico.FiltroGenerico;
import com.ideaspymes.proyecttemplate.stock.model.Deposito;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IDepositoDAO;
import java.lang.reflect.Field;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.SortOrder;

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

    
    @Override
    public String construyeFilters(String sortField, SortOrder sortOrder) {
        String consulta = "SELECT * FROM " + getClazz().getSimpleName().toLowerCase() + "  ";
        StringBuilder sb = new StringBuilder(consulta);
        boolean tieneCampoEmpresa = false;
        for (Field f : getClazz().getDeclaredFields()) {
            if (f.getName().compareToIgnoreCase("empresa") == 0) {
                tieneCampoEmpresa = true;
                break;
            }
        }

        if (getCredencial().getEmpresa() != null && tieneCampoEmpresa) {
            sb.append(" WHERE empresa_id =  ");
            sb.append(getCredencial().getEmpresa().getId());
            sb.append(" AND estado <> 'BORRADO' ");
            sb.append(" AND tipodeposito = 'NORMAL' ");
        } else {
            sb.append(" WHERE estado <> 'BORRADO' ");
            sb.append(" AND tipodeposito = 'NORMAL' ");
        }

        if (getFilterOptions() == null) {
            loadFilters();
        }

        for (FiltroGenerico f : getFilterOptions()) {
            if (f.tieneValor()) {
                sb.append(f.getCadenaFiltro());
            }
        }

        if (sortField != null) {
            sb.append(" ORDER BY  ");
            sb.append(sortField);
            sb.append(SortOrder.ASCENDING.equals(sortOrder) ? " ASC " : " DESC ");
        }

        System.out.println("Contruye Consulta: " + sb.toString());

        return sb.toString();
    }
}
