/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.model.Existencia;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IProductoDAO;
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
public class ProductoDAO implements IProductoDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Producto create(Producto entity) {
        return abmService.create(entity);
    }

    @Override
    public Producto edit(Producto entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Producto entity) {
        abmService.delete(entity);
    }

    @Override
    public Producto find(Object id) {
        return abmService.find(id, Producto.class);
    }

    @Override
    public List<Producto> findAll() {
        return abmService.getEM().createQuery("select obj from Producto obj WHERE OBJ.estado = ?1")
                .setParameter(1, Estado.ACTIVO)
                .getResultList();
    }

    @Override
    public List<Producto> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

    @Override
    public List<Producto> findAll(String query, QueryParameter params, int first, int pageSize) {
        return abmService.findByQuery(query, params.parameters(), first, pageSize);
    }

    @Override
    public List<Producto> findFilter(String consulta, int first, int pageSize) {
        List<Producto> items = new ArrayList<>();
        if (consulta != null) {
            System.out.println("Consulta: " + consulta);
            Query query = abmService.getEM().createNativeQuery(consulta, Producto.class);
            if (first > 0) {
                query.setFirstResult(first);
            }

            if (pageSize > 0) {
                query.setMaxResults(pageSize);
            }

            items = (List<Producto>) query.getResultList();

        }
        return items;
    }

    @Override
    public List<Producto> completar(String matchText) {
        List<Producto> sugerencias = new ArrayList<>();

        if (matchText != null && matchText.length() > 0) {
            String consulta = "select * from producto where estado = 'ACTIVO' and upper(nombre) like '%" + matchText.toUpperCase().trim() + "%' order by nombre";
            Query query = abmService.getEM().createNativeQuery(consulta, Producto.class);
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
    
    
    public List<Existencia> findExistenciasPorProducto(Producto p){
        return abmService.getEM().createQuery("SELECT e FROM Existencia e WHERE e.cantidad > 0 AND e.producto = :producto")
                .setParameter("producto", p)
                .getResultList();
    }

}
