/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.generico;

import com.ideaspymes.proyecttemplate.configuracion.model.Usuario;
import java.util.List;

/**
 *
 * @author christian
 */
public interface AbstractDAO<T> {

    public abstract T create(T entity, Usuario usuario);

    public abstract T edit(T entity, Usuario usuario);

    public abstract void remove(T entity, Usuario usuario);

    public abstract T find(Object id);

    public abstract List<T> findAll();

    public abstract List<T> findAll(String query, QueryParameter params);

    public abstract List<T> findAll(String query, QueryParameter params, int first, int pageSize);
    
    public abstract List<T> findFilter(String query, int first, int pageSize);
    
    public abstract int countFilter(String query);
}
