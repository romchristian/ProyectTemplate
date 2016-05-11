/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.web.converters;

import com.ideaspymes.proyecttemplate.configuracion.model.EtiquetaConf;
import com.ideaspymes.proyecttemplate.generico.ConverterGenerico;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = EtiquetaConf.class)
public class EtiquetaConfConverter extends ConverterGenerico<EtiquetaConf> {

    @Override
    public String getBeanName() {
        return "etiquetaConfBean";
    }
}
