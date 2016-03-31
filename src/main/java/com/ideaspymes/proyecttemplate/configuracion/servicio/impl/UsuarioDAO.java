/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IUsuarioDAO;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.configuracion.model.Usuario;
import com.ideaspymes.proyecttemplate.generico.ABMService;
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
public class UsuarioDAO implements IUsuarioDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Usuario create(Usuario entity, Usuario usuario) {
        EntityManager em = abmService.getEM();
        entity.setEstado(Estado.ACTIVO);
        entity.setFechaRegitro(new Date());

        em.persist(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }

    @Override
    public Usuario edit(Usuario entity, Usuario usuario) {
        try {
            EntityManager em = abmService.getEM();

            entity.setFechaUltimaModificacion(new Date());

            entity = em.merge(entity);
            em.flush();
            em.refresh(entity);
        } catch (OptimisticLockException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public void remove(Usuario entity, Usuario usuario) {
        abmService.delete(entity, usuario);
    }

    @Override
    public Usuario find(Object id) {
        return abmService.find(id, Usuario.class);
    }

    @Override
    public Usuario find(String login) {
        Usuario R = null;

        try {
            R = (Usuario) abmService.getEM().createQuery("SELECT u FROM Usuario u WHERE u.userName = :login ")
                    .setParameter("login", login).getSingleResult();
        } catch (Exception e) {
        }

        return R;
    }

    @Override
    public List<Usuario> findAll() {
        return abmService.getEM().createQuery("select obj from Usuario obj where obj.estado <> :estado")
                .setParameter("estado", Estado.BORRADO)
                .getResultList();
    }

    @Override
    public List<Usuario> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

    @Override
    public List<Usuario> findAll(String query, QueryParameter params, int firt, int pageSize) {
        return abmService.findByQuery(query, params.parameters(), firt, pageSize);
    }

    @Override
    public List<Usuario> findFilter(String consulta, int first, int pageSize) {
        List<Usuario> items = new ArrayList<>();
        if (consulta != null) {
            Query query = abmService.getEM().createNativeQuery(consulta, Usuario.class);
            if (first > 0) {
                query.setFirstResult(first);
            }

            if (pageSize > 0) {
                query.setMaxResults(pageSize);
            }

            items = (List<Usuario>) query.getResultList();

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
    public List<Usuario> completar(String matchText) {
        List<Usuario> sugerencias = new ArrayList<>();

        if (matchText != null && matchText.length() > 0) {
            String consulta = "select * from usuario where estado = 'ACTIVO' and upper(nombre) like '%" + matchText.toUpperCase().trim() + "%' order by nombre";
            Query query = abmService.getEM().createNativeQuery(consulta, Usuario.class);
            query.setMaxResults(20);
            sugerencias = query.getResultList();
        }

        return sugerencias;
    }
}
