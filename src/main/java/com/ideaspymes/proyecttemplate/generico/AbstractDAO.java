/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.generico;

import java.util.List;

/**
 *
 * @author christian
 */
public interface AbstractDAO<T> {
    
    public static final int AUTOCOMPLETE_MAX_RESULS = 10;

    public abstract T create(T entity);

    public abstract T edit(T entity);

    public abstract void remove(T entity);

    public abstract T find(Object id);

    public abstract List<T> findAll();

    public abstract List<T> findAll(String query, QueryParameter params);

    public abstract List<T> findAll(String query, QueryParameter params, int first, int pageSize);

    public abstract List<T> findFilter(String query, int first, int pageSize);

    public abstract List<T> completar(String query);

    public abstract int countFilter(String query);
}
