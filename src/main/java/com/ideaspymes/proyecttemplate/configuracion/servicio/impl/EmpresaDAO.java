/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IEmpresaDAO;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.configuracion.model.Empresa;
import com.ideaspymes.proyecttemplate.configuracion.model.Usuario;
import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.generico.Credencial;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.Query;

/*
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class EmpresaDAO implements IEmpresaDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;
    

    @Override
    public Empresa create(Empresa entity, Usuario usuario) {
        EntityManager em = abmService.getEM();
        entity.setEstado(Estado.ACTIVO);
        entity.setFechaRegitro(new Date());
        entity.setUsuarioUltimaModificacion(usuario);
        em.persist(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }

    @Override
    public Empresa edit(Empresa entity, Usuario usuario) {
        try {
            EntityManager em = abmService.getEM();

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
    public void remove(Empresa entity, Usuario usuario) {
        abmService.delete(entity, usuario);
    }

    @Override
    public Empresa find(Object id) {
        return abmService.find(id, Empresa.class);
    }

    @Override
    public List<Empresa> findAll() {
        return abmService.getEM().createQuery("select obj from Empresa obj where obj.estado = :estado")
                .setParameter("estado", Estado.ACTIVO)
                .getResultList();
    }

    @Override
    public List<Empresa> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

    @Override
    public List<Empresa> findAll(String query, QueryParameter params, int firt, int pageSize) {
        return abmService.findByQuery(query, params.parameters(), firt, pageSize);
    }

    @Override
    public List<Empresa> findFilter(String consulta, int first, int pageSize) {
        List<Empresa> items = new ArrayList<>();
        if (consulta != null) {
            System.out.println("Consulta: " + consulta);
            Query query = abmService.getEM().createNativeQuery(consulta, Empresa.class);
            if (first > 0) {
                query.setFirstResult(first);
            }

            if (pageSize > 0) {
                query.setMaxResults(pageSize);
            }

            items = (List<Empresa>) query.getResultList();

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
    public List<Empresa> completar(String matchText) {
        List<Empresa> sugerencias = new ArrayList<>();

        if (matchText != null && matchText.length() > 0) {
            String consulta = "select * from empresa where estado = 'ACTIVO' and upper(nombre) like '%" + matchText.toUpperCase().trim() + "%' order by nombre";
            Query query = abmService.getEM().createNativeQuery(consulta, Empresa.class);
            query.setMaxResults(20);
            sugerencias = query.getResultList();
        }

        return sugerencias;
    }

    @Override
    public Empresa getEmpresaDefault(Usuario usuario) {
        return find(1L);
    }

}
