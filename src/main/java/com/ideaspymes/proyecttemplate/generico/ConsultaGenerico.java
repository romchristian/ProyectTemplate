/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.generico;

import com.ideaspymes.proyecttemplate.configuracion.model.Empresa;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Christian
 */
public abstract class ConsultaGenerico<T> extends LazyDataModel<T> implements Serializable {

    @Inject
    private Credencial credencial;
    private List<FiltroGenerico> filterOptions;
    private List<Columna> columnas;
    private List<T> lista;

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }

    public List<FiltroGenerico> getFilterOptions() {
        if (filterOptions == null) {
            loadFilters();
        }
        return filterOptions;
    }

    public void setFilterOptions(List<FiltroGenerico> filterOptions) {
        this.filterOptions = filterOptions;
    }

    public List<Columna> getColumnas() {
        if (columnas == null) {
            loadFields();
        }
        return columnas;
    }

    public void setColumnas(List<Columna> columnas) {
        this.columnas = columnas;
    }

    public abstract Class<T> getClazz();

    public void loadFields() {
        Field[] fields = getClazz().getDeclaredFields();
        columnas = new ArrayList<>();

        for (Field f : fields) {
            if (f.getAnnotation(Listado.class) != null && f.getAnnotation(Listado.class).mostrar()) {
                String descripcion = f.getAnnotation(Listado.class).descripcion();
                columnas.add(new Columna(descripcion, f.getName(), f.getGenericType().getTypeName(), f.getAnnotation(Listado.class).link(), f.getAnnotation(Listado.class).entidad(), f.getAnnotation(Listado.class).campoDescripcion()));
            }
        }

    }

    public void loadFilters() {
        Field[] fields = getClazz().getDeclaredFields();
        filterOptions = new ArrayList<>();

        for (Field f : fields) {
            if (f.getAnnotation(Filtro.class) != null && f.getAnnotation(Filtro.class).campo().length() > 0) {
                filterOptions.add(new FiltroGenerico(f.getAnnotation(Filtro.class).descripcion(), f.getAnnotation(Filtro.class).campo(), f.getAnnotation(Filtro.class).tipo(), f.getGenericType().getTypeName(), f.getAnnotation(Filtro.class).campoDescripcion()));
            }
        }

    }

    public void buscar() {
        construyeConsulta();
    }

    public List<T> findAll() {
        return getEjb().findAll();
    }

    public Credencial getCredencial() {
        return credencial;
    }

    public abstract AbstractDAO<T> getEjb();

    private void construyeConsulta() {

    }

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

        if (credencial.getEmpresa() != null && tieneCampoEmpresa) {
            sb.append(" WHERE empresa_id =  ");
            sb.append(credencial.getEmpresa().getId());
            sb.append(" AND estado <> 'BORRADO' ");
        } else {
            sb.append(" WHERE estado <> 'BORRADO' ");
        }

        if (filterOptions == null) {
            loadFilters();
        }

        for (FiltroGenerico f : filterOptions) {
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

    private String construyeCount() {
        String consulta = "SELECT count(*) FROM " + getClazz().getSimpleName().toLowerCase() + "  ";
        StringBuilder sb = new StringBuilder(consulta);
        sb.append(" WHERE 1 = 1 ");
        for (FiltroGenerico f : filterOptions) {
            if (f.tieneValor()) {
                sb.append(f.getCadenaFiltro());
            }
        }

        return sb.toString();
    }

    @Override
    public List<T> load(
            int first, int pageSize,
            String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {

        lista = new ArrayList<>();

        //sort
        lista = getEjb().findFilter(construyeFilters(sortField, sortOrder), first, pageSize);

        setRowCount(getEjb().countFilter(construyeCount()));
        return lista;
    }

    public SelectItem[] obtItemsAvailableSelectMany(String campo) {
        return getController(FacesContext.getCurrentInstance(), campo).getItemsAvailableSelectMany();
    }

    public SelectItem[] obtItemsAvailableSelectOne(String campo) {
        return getController(FacesContext.getCurrentInstance(), campo).getItemsAvailableSelectOne();
    }

    public Converter obtConverter(String campo) {
        if (campo != null && campo.length() > 0) {
            BeanGenerico bean = getController(FacesContext.getCurrentInstance(), campo);
            return bean.getConverter();
        }
        return null;
    }

    public ConsultaGenerico obtConsultaBean(String campo) {
        ConsultaGenerico bean = null;
        if (campo != null && campo.length() > 0) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            bean = (ConsultaGenerico) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, campo + "ConsultaBean");
        }
        return bean;
    }

    private BeanGenerico getController(FacesContext facesContext, String campo) {
        return (BeanGenerico) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, campo + "Bean");
    }

    public List<T> completar(String query) {
        return getEjb().completar(query);
    }

}
