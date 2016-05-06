/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IGrupoDAO;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.configuracion.model.Grupo;
import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.Query;

/*
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class GrupoDAO implements IGrupoDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Grupo create(Grupo entity) {
        EntityManager em = abmService.getEM();
        String usuario = abmService.getCredencial().getUsuario() != null ? abmService.getCredencial().getUsuario().getNombre() + ", " + abmService.getCredencial().getUsuario().getUserName() : "";
        entity.setEstado(Estado.ACTIVO);
        entity.setFechaRegitro(new Date());
        entity.setUsuarioUltimaModificacion(usuario);
        em.persist(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }

    @Override
    public Grupo edit(Grupo entity) {
        try {
            EntityManager em = abmService.getEM();
            String usuario = abmService.getCredencial().getUsuario() != null ? abmService.getCredencial().getUsuario().getNombre() + ", " + abmService.getCredencial().getUsuario().getUserName() : "";
            entity.setFechaUltimaModificacion(new Date());
            entity.setUsuarioUltimaModificacion(usuario);
            entity = em.merge(entity);
            em.flush();
            em.refresh(entity);
        } catch (OptimisticLockException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public void remove(Grupo entity) {
        entity.setEstado(Estado.BORRADO);
        edit(entity);
    }

    @Override
    public Grupo find(Object id) {
        return abmService.find(id, Grupo.class);
    }

    @Override
    public Grupo find(String nombre) {
        Grupo R = null;

        try {
            R = (Grupo) abmService.getEM().createQuery("SELECT u FROM Grupo u WHERE u.nombre = :login AND u.estado = :estado")
                    .setParameter("login", nombre)
                    .setParameter("estado", Estado.ACTIVO)
                    .getSingleResult();
        } catch (Exception e) {
        }

        return R;
    }

    @Override
    public List<Grupo> findAll() {
        return abmService.getEM().createQuery("select obj from Grupo obj where obj.estado = :estado")
                .setParameter("estado", Estado.ACTIVO)
                .getResultList();
    }

    @Override
    public List<Grupo> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

    @Override
    public List<Grupo> findAll(String query, QueryParameter params, int firt, int pageSize) {
        return abmService.findByQuery(query, params.parameters(), firt, pageSize);
    }

    @Override
    public List<Grupo> findFilter(String consulta, int first, int pageSize) {
        List<Grupo> items = new ArrayList<>();
        if (consulta != null) {
            Query query = abmService.getEM().createNativeQuery(consulta, Grupo.class);
            if (first > 0) {
                query.setFirstResult(first);
            }

            if (pageSize > 0) {
                query.setMaxResults(pageSize);
            }

            items = (List<Grupo>) query.getResultList();

        }
        return items;
    }

    @Override
    public int countFilter(String consulta) {
        int R = 0;

        try {
            Query query = abmService.getEM().createNativeQuery(consulta);
            R = ((Long) query.getSingleResult()).intValue();

        } catch (Exception e) {
        }

        return R;
    }

    @Override
    public List<Grupo> completar(String matchText) {
        List<Grupo> sugerencias = new ArrayList<>();

        if (matchText != null && matchText.length() > 0) {
            String consulta = "select * from grupo where estado = 'ACTIVO' and upper(nombre) like '%" + matchText.toUpperCase().trim() + "%' order by nombre";
            Query query = abmService.getEM().createNativeQuery(consulta, Grupo.class);
            query.setMaxResults(AbstractDAO.AUTOCOMPLETE_MAX_RESULS);
            sugerencias = query.getResultList();
        }

        return sugerencias;
    }
}
