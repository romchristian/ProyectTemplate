/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.generico;

import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.TipoCosteo;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.TipoEtiqueta;
import com.ideaspymes.proyecttemplate.stock.enums.EstadoComprobanteStock;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 *
 * @author christian
 */
@Named
@ApplicationScoped
public class ProductorEnums implements Serializable {

    public SelectItem[] obtEstados() {
        SelectItem[] R = new SelectItem[Estado.values().length];
        Estado[] lista = Estado.values();
        for (int i = 0; i < lista.length; i++) {
            Estado e = lista[i];
            R[i] = new SelectItem(e, e.toString());
        }
        return R;
    }

    public SelectItem[] obtTiposPersonas() {
        SelectItem[] R = new SelectItem[TipoCosteo.values().length];
        TipoCosteo[] lista = TipoCosteo.values();
        for (int i = 0; i < lista.length; i++) {
            TipoCosteo tp = lista[i];
            R[i] = new SelectItem(tp, tp.toString());
        }
        return R;
    }

    public SelectItem[] obtTiposComprobante() {
        SelectItem[] R = new SelectItem[TipoCosteo.values().length];
        TipoCosteo[] lista = TipoCosteo.values();
        for (int i = 0; i < lista.length; i++) {
            TipoCosteo tp = lista[i];
            R[i] = new SelectItem(tp, tp.toString());
        }
        return R;
    }

    public EstadoComprobanteStock[] getEstadosComprobantesStock() {
        return EstadoComprobanteStock.values();
    }

    public TipoEtiqueta[] getTiposEtiquetas() {
        return TipoEtiqueta.values();
    }
}

//TipoCargo
