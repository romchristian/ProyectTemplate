/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.impl;

import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.model.ProductoUnidadMedida;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IProductoUnidadMedidaDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

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
        return abmService.getEM().createQuery("select obj from ProductoUnidadMedida obj").getResultList();
    }

    @Override
    public List<ProductoUnidadMedida> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

    @Override
    public List<ProductoUnidadMedida> findAll(String query, QueryParameter params, int first, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductoUnidadMedida> findFilter(String query, int first, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductoUnidadMedida> completar(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countFilter(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
