/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.enums.TipoMovimientoStock;
import com.ideaspymes.proyecttemplate.stock.model.TipoComprobanteStock;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.ITipoComprobanteStockDAO;
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
public class TipoComprobanteStockDAO implements ITipoComprobanteStockDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public TipoComprobanteStock create(TipoComprobanteStock entity) {
        return abmService.create(entity);
    }

    @Override
    public TipoComprobanteStock edit(TipoComprobanteStock entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(TipoComprobanteStock entity) {
        abmService.delete(entity);
    }

    @Override
    public TipoComprobanteStock find(Object id) {
        return abmService.find(id, TipoComprobanteStock.class);
    }
    
    public TipoComprobanteStock findPorNombre(String nombre) {
        
        return (TipoComprobanteStock) abmService.getEM().createQuery("select obj from TipoComprobanteStock obj WHERE OBJ.estado = ?1 AND OBJ.nombre = ?2")
                .setParameter(1, Estado.ACTIVO)
                .setParameter(2, nombre)
                .getSingleResult();
    }

    @Override
    public List<TipoComprobanteStock> findAll() {
        return abmService.getEM().createQuery("select obj from TipoComprobanteStock obj WHERE OBJ.estado = ?1 AND OBJ.mostrar = true")
                .setParameter(1, Estado.ACTIVO)
                .getResultList();
    }
    
    
    @Override
    public List<TipoComprobanteStock> findAllPorTipo(TipoMovimientoStock tipo) {
        return abmService.getEM().createQuery("select obj from TipoComprobanteStock obj WHERE OBJ.estado = ?1 AND OBJ.mostrar = true AND OBJ.tipo = ?2")
                .setParameter(1, Estado.ACTIVO)
                .setParameter(2, tipo)
                .getResultList();
    }
    
    
    @Override
    public List<TipoComprobanteStock> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

    @Override
    public List<TipoComprobanteStock> findAll(String query, QueryParameter params, int first, int pageSize) {
        return abmService.findByQuery(query, params.parameters(), first, pageSize);
    }

    @Override
    public List<TipoComprobanteStock> findFilter(String consulta, int first, int pageSize) {
        List<TipoComprobanteStock> items = new ArrayList<>();
        if (consulta != null) {
            System.out.println("Consulta: " + consulta);
            Query query = abmService.getEM().createNativeQuery(consulta, TipoComprobanteStock.class);
            if (first > 0) {
                query.setFirstResult(first);
            }

            if (pageSize > 0) {
                query.setMaxResults(pageSize);
            }

            items = (List<TipoComprobanteStock>) query.getResultList();

        }
        return items;
    }

    @Override
    public List<TipoComprobanteStock> completar(String matchText) {
        List<TipoComprobanteStock> sugerencias = new ArrayList<>();

        if (matchText != null && matchText.length() > 0) {
            String consulta = "select * from tipoComprobanteStock where estado = 'ACTIVO' and upper(nombre) like '%" + matchText.toUpperCase().trim() + "%' order by nombre";
            Query query = abmService.getEM().createNativeQuery(consulta, TipoComprobanteStock.class);
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
