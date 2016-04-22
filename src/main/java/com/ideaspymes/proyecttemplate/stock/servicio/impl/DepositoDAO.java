/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.enums.TipoDeposito;
import com.ideaspymes.proyecttemplate.stock.model.Deposito;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IDepositoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class DepositoDAO implements IDepositoDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Deposito create(Deposito entity) {
        return abmService.create(entity);
    }

    @Override
    public Deposito edit(Deposito entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Deposito entity) {
        abmService.delete(entity);
    }

    @Override
    public Deposito find(Object id) {
        return abmService.find(id, Deposito.class);
    }
    
     public Deposito findPorTipo(TipoDeposito tipo) {
         Deposito R = null;
         try {
             R = (Deposito) abmService.getEM().createQuery("SELECT d FROM Deposito d where d.tipoDeposito = :tipo AND d.estado = :estado")
                     .setParameter("tipo",tipo)
                     .setParameter("estado",Estado.ACTIVO)
                     .getSingleResult();
         } catch (Exception e) {
         }
        return R;
    }


    @Override
    public List<Deposito> findAll() {
        return abmService.getEM().createQuery("select obj from Deposito obj WHERE OBJ.estado = ?1 AND OBJ.tipoDeposito = ?2")
                .setParameter(1, Estado.ACTIVO)
                .setParameter(2, TipoDeposito.NORMAL)
                .getResultList();
    }

    @Override
    public List<Deposito> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

    @Override
    public Deposito findDefault() {
        Deposito R = null;
        try {
            R = (Deposito) abmService.getEM().createQuery("SELECT d FROM Deposito d")
                    .setMaxResults(1).getSingleResult();

        } catch (Exception e) {
        }
        return R;
    }

    @Override
    public List<Deposito> findAll(String query, QueryParameter params, int first, int pageSize) {
        return abmService.findByQuery(query, params.parameters(), first, pageSize);
    }

    @Override
    public List<Deposito> findFilter(String consulta, int first, int pageSize) {
        List<Deposito> items = new ArrayList<>();
        if (consulta != null) {
            System.out.println("Consulta: " + consulta);
            Query query = abmService.getEM().createNativeQuery(consulta, Deposito.class);
            if (first > 0) {
                query.setFirstResult(first);
            }

            if (pageSize > 0) {
                query.setMaxResults(pageSize);
            }

            items = (List<Deposito>) query.getResultList();

        }
        return items;
    }

    @Override
    public List<Deposito> completar(String matchText) {
        List<Deposito> sugerencias = new ArrayList<>();

        if (matchText != null && matchText.length() > 0) {
            String consulta = "select * from deposito where estado = 'ACTIVO' and upper(nombre) like '%" + matchText.toUpperCase().trim() + "%' order by nombre";
            Query query = abmService.getEM().createNativeQuery(consulta, Deposito.class);
            query.setMaxResults(AbstractDAO.AUTOCOMPLETE_MAX_RESULS);
            sugerencias = query.getResultList();
        }

        return sugerencias;
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

}
