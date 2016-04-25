/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.generico;

import com.ideaspymes.proyecttemplate.configuracion.model.Empresa;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author Christian
 */
public abstract class ReporteGenerico<T> {

    @Inject
    private Credencial credencial;
    @Inject
    private ReporteController reporteController;
    private Map<String, Object> params;

    public void generar() {
        cargaParamsEmpresa();
        cargaParams();
        reporteController.generaPDF(getParams(), getDetalles(), getPath(), getNombre());
    }

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    

    private void cargaParamsEmpresa() {
        Empresa e = credencial.getEmpresa();
        getParams().put("empresa", e.getNombre());
        getParams().put("direccion", e.getContactoDireccion());
        getParams().put("telefono", e.getContactoTelefono());
        getParams().put("email", e.getContactoEmail());
        getParams().put("logo", e.getImagen());
    }
    
    
    public abstract List<T> getDetalles();

    public abstract String getPath();

    public abstract String getNombre();

    public abstract void cargaParams();
}
