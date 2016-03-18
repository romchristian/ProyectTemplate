/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.generico;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author cromero
 * @param <T>
 */
public abstract class ConverterGenerico<T> implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }

        return getController(facesContext).find(getKey(value));
    }

    public abstract String getBeanName();

    public BeanGenerico getController(FacesContext facesContext) {
        return (BeanGenerico) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, getBeanName());
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

            IAuditable o = (IAuditable) object;
            return getStringKey(o.getId());
        } catch (Exception e) {
            return null;
        }

    }

}
