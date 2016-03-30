/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.web.converters;

import com.ideaspymes.proyecttemplate.configuracion.model.TipoDocumento;
import com.ideaspymes.proyecttemplate.generico.ConverterGenerico;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = TipoDocumento.class)
public class TipoDocumentoConverter extends ConverterGenerico<TipoDocumento> {

    @Override
    public String getBeanName() {
        return "tipoDocumentoBean";
    }
}
