/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.generico;

import com.ideaspymes.proyecttemplate.configuracion.model.Usuario;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.persistence.EntityManager;

/**
 *
 * @author cromero
 */
@Local
public interface ABMService {

    <T> T create(T t, Usuario usuario);

    void delete(Object t, Usuario usuario);

    <T> T update(T t, Usuario usuario);

    <T> T find(Object id, Class<T> type);

    <T> T findByNamedQuerySingleResult(String queryName, Map<String, Object> params);

    List findByNamedQuery(String queryName);

    List findByNamedQuery(String queryName, int resultLimit);

    List findByNamedQuery(String queryName, Map<String, Object> params);

    List findByNamedQuery(String queryName, Map<String, Object> params, int resultLimit);

    Long count(String nativeQuery);

    List findByQuery(String query, int resultLimit);

    List findByQuery(String query, Map<String, Object> params);

    List findByQuery(String query, Map<String, Object> params, int resultLimit);

    List findByQuery(String query, Map<String, Object> params, int first, int resultLimit);

    EntityManager getEM();

    Double findTotal(String query, Map<String, Object> params);

}
