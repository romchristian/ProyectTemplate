/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.generico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author christian
 */
public abstract class BeanGenerico<T> implements Serializable {

    @Inject
    private Credencial credencial;
    private long id;

    private T actual;

    private List<T> listado = new ArrayList<>();

    public static final String OBJ = "obj";

    private UIComponent dataGridDisponibles;

    private Map<String, Double> mapTotales = new HashMap<>();
    private boolean muestraTotales = false;

    //Para el autocomplete
    public List<T> completar(String query) {
        List<T> sugerencias = new ArrayList<>();

        for (T p : getEjb().findAll()) {
            if (p.toString().toUpperCase().startsWith(query.toUpperCase())) {
                sugerencias.add(p);
            }
        }

        return sugerencias;
    }

    //Para el autocomplete
    public void siCambiaAutocomplete(SelectEvent event) {
    }

    public T getActual() {
        if (actual == null) {
            actual = getNuevo();
        }
        return actual;
    }

    public void setActual(T actual) {
        this.actual = actual;
    }

    public abstract AbstractDAO<T> getEjb();

    public abstract T getNuevo();
    
    public abstract Converter getConverter();

    public String create() {

        if (getEjb().create(getActual(), credencial.getUsuario()) != null) {
            JsfUtil.addSuccessMessage("Se creó exitosamente!");
            setActual(null);
            return "listado.xhtml?faces-redirect=true";
        } else {
            return null;
        }

    }

    public String edit() {
        if (getEjb().edit(getActual(), credencial.getUsuario()) == null) {
            JsfUtil.addErrorMessage("Otro usuario realizó una modificación sobre el mismo dato,y pruebe de nuevo");
            return null;
        }

        JsfUtil.addSuccessMessage("Se guardó exitosamente!");
        setActual(null);
        return "listado.xhtml?faces-redirect=true";
    }

    public String remove() {
        getEjb().remove(getActual(), credencial.getUsuario());
        setActual(null);
        JsfUtil.addSuccessMessage("Se removió exitosamente!");
        return "listado.xhtml?faces-redirect=true";
    }

    public T find(Object id) {
        return (T) getEjb().find(id);
    }

    public List<T> findAll() {
        return getEjb().findAll();
    }

    public List<T> findAllFiltros() {
        if (listado == null) {
            listado = new ArrayList<>();
        }

        return listado;
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(findAll(), true);
    }

    public String preparaEdicion(T obj) {
        setActual(obj);
        return "edita.xhtml?faces-redirect=true";
    }

    public String preparaCreacion() {
        setActual(null);
        return "nuevo.xhtml?faces-redirect=true";
    }

    public String nuevo() {
        setActual(null);
        return "nuevo.xhtml?faces-redirect=true";
    }

    public UIComponent getDataGridDisponibles() {
        return dataGridDisponibles;
    }

    public void setDataGridDisponibles(UIComponent dataGridDisponibles) {
        this.dataGridDisponibles = dataGridDisponibles;
    }

    public List<T> getListado() {
        return listado;
    }

    public void setListado(List<T> listado) {
        this.listado = listado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String cargaDatos() {
        if (id > 0) {
            setActual(getEjb().find(id));
        }
        return null;
    }

    public Map<String, Double> getMapTotales() {
        return mapTotales;
    }

    public void setMapTotales(Map<String, Double> mapTotales) {
        this.mapTotales = mapTotales;
    }

    public boolean isMuestraTotales() {
        return muestraTotales;
    }

    public void setMuestraTotales(boolean muestraTotales) {
        this.muestraTotales = muestraTotales;
    }
}
