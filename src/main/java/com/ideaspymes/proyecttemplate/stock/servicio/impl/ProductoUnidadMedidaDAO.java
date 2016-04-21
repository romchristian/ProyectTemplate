/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.model.ProductoUnidadMedida;
import com.ideaspymes.proyecttemplate.stock.model.UnidadMedida;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IProductoUnidadMedidaDAO;
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
public class ProductoUnidadMedidaDAO implements IProductoUnidadMedidaDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public ProductoUnidadMedida create(ProductoUnidadMedida entity) {
        return abmService.create(entity);
    }

    @Override
    public ProductoUnidadMedida edit(ProductoUnidadMedida entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(ProductoUnidadMedida entity) {
        abmService.delete(entity);
    }

    @Override
    public ProductoUnidadMedida find(Object id) {
        return abmService.find(id, ProductoUnidadMedida.class);
    }

    @Override
    public List<ProductoUnidadMedida> findAll() {
        return abmService.getEM().createQuery("select obj from ProductoUnidadMedida obj WHERE OBJ.estado = ?1")
                .setParameter(1, Estado.ACTIVO)
                .getResultList();
    }

    @Override
    public List<ProductoUnidadMedida> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

    @Override
    public List<ProductoUnidadMedida> findAll(String query, QueryParameter params, int first, int pageSize) {
        return abmService.findByQuery(query, params.parameters(), first, pageSize);
    }

    @Override
    public List<ProductoUnidadMedida> findFilter(String consulta, int first, int pageSize) {
        List<ProductoUnidadMedida> items = new ArrayList<>();
        if (consulta != null) {
            System.out.println("Consulta: " + consulta);
            Query query = abmService.getEM().createNativeQuery(consulta, ProductoUnidadMedida.class);
            if (first > 0) {
                query.setFirstResult(first);
            }

            if (pageSize > 0) {
                query.setMaxResults(pageSize);
            }

            items = (List<ProductoUnidadMedida>) query.getResultList();

        }
        return items;
    }

    @Override
    public List<ProductoUnidadMedida> completar(String matchText) {
        List<ProductoUnidadMedida> sugerencias = new ArrayList<>();

        if (matchText != null && matchText.length() > 0) {
            String consulta = "select * from productounidadmedida where estado = 'ACTIVO' and upper(nombre) like '%" + matchText.toUpperCase().trim() + "%' order by nombre";
            Query query = abmService.getEM().createNativeQuery(consulta, ProductoUnidadMedida.class);
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

    @Override
    public ProductoUnidadMedida find(Producto p, UnidadMedida umDe, UnidadMedida umA) {
        ProductoUnidadMedida R = null;
        try {
            R = (ProductoUnidadMedida) abmService.getEM().createQuery("SELECT pu from ProductoUnidadMedida pu WHERE pu.producto = :producto AND pu.unidadMedidaDe = :umDe AND pu.unidadMedidaA = :umA")
                    .setParameter("producto", p)
                    .setParameter("umDe", umDe)
                    .setParameter("umA", umA)
                    .getSingleResult();
        } catch (Exception e) {
        }
        return R;
    }

}
