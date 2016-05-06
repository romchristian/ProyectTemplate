/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.web.converters;

import com.ideaspymes.proyecttemplate.configuracion.model.Grupo;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = Grupo.class,value = "grupoConverter")
public class GrupoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }

        return getController(facesContext).find(getKey(value));
    }

    public BeanGenerico getController(FacesContext facesContext) {
        return (BeanGenerico) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "grupoBean");
    }

    java.lang.Long getKey(String value) {
        java.lang.Long key;
        try {
            key = Long.valueOf(value);
        } catch (Exception e) {
            key = 0L;
        }
        return key;
    }

    String getStringKey(java.lang.Long value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }

        try {

            Grupo o = (Grupo) object;
            return getStringKey(o.getId());
        } catch (Exception e) {
            return null;
        }

    }
}
