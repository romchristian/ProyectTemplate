/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.model.Deposito;
import com.ideaspymes.proyecttemplate.stock.model.Existencia;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.model.Ubicacion;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IProductoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.primefaces.application.resource.barcode.EAN13Generator;

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
        Producto R = abmService.create(entity);
        //R.setCodigo(generaCodigoEAN13(R.getId()));
        R.setCodigo(generaCodigoCode39(R.getId()));
        edit(R);
        return R;
    }

    private String generaCodigoEAN13(Long id) {
        String R = "" + id;
        int size = 12;
        int res = size - R.length();
        String ceros = "";
        for (int i = 0; i < res; i++) {
            ceros += "0";
        }

        R = ceros + R;

        return R + "" + calcChecksum(R);
    }

    private String generaCodigoCode39(Long id) {
        String R = "" + id;
        int size = 3;
        int res = size - R.length();
        String ceros = "";
        for (int i = 0; i < res; i++) {
            ceros += "0";
        }

        R = ceros + R;

        return R;
    }

    private int calcChecksum(String first12digits) {
        char[] char12digits = first12digits.toCharArray();
        int[] ean13 = {1, 3};
        int sum = 0;
        for (int i = 0; i < char12digits.length; i++) {
            sum += Character.getNumericValue(char12digits[i]) * ean13[i % 2];
        }

        int checksum = 10 - sum % 10;
        if (checksum == 10) {
            checksum = 0;
        }

        return checksum;
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
    public Producto findPorCodigo(String codigo) {
        Producto R = null;
        try {
            R = (Producto) abmService.getEM().createQuery("select obj from Producto obj WHERE OBJ.estado = ?1 and OBJ.codigo = ?2")
                    .setParameter(1, Estado.ACTIVO)
                    .setParameter(2, codigo)
                    .getSingleResult();
        } catch (Exception e) {
        }

        return R;
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

    @Override
    public List<Existencia> findExistenciasPorProducto(Producto p) {
        return abmService.getEM().createQuery("SELECT e FROM Existencia e WHERE e.cantidad > 0 AND e.producto = :producto AND e.producto.estado = :estado ORDER BY e.producto.familia.nombre, e.producto.nombre")
                .setParameter("producto", p)
                .setParameter("estado", Estado.ACTIVO)
                .getResultList();
    }
    
    
    @Override
    public Existencia findExistenciasPorProductoUbicacion(Producto p,Deposito d, Ubicacion u) {
        
        Existencia R = null;
        try {
            R = (Existencia) abmService.getEM().createQuery("SELECT e FROM Existencia e WHERE e.producto = :producto AND e.producto.estado = :estado AND e.deposito = :deposito AND e.ubicacion = :ubicacion ORDER BY e.producto.familia.nombre, e.producto.nombre")
                .setParameter("producto", p)
                .setParameter("deposito", d)
                .setParameter("ubicacion", u)
                .setParameter("estado", Estado.ACTIVO)
                .getSingleResult();
        } catch (Exception e) {
        }
        return R;
    }

    @Override
    public List<Existencia> findExistenciaPorDeposito(Deposito d, Ubicacion u) {
        List<Existencia> result;
        if (d != null && u != null) {
            result = abmService.getEM().createQuery("SELECT e FROM Existencia e WHERE e.deposito = :deposito AND e.ubicacion = :ubicacion AND e.producto.estado = :estado ORDER BY e.producto.familia.nombre, e.producto.nombre")
                    .setParameter("deposito", d)
                    .setParameter("ubicacion", u)
                    .setParameter("estado", Estado.ACTIVO)
                    .getResultList();
        } else if (d != null) {
            result = abmService.getEM().createQuery("SELECT e FROM Existencia e WHERE e.deposito = :deposito AND e.producto.estado = :estado ORDER BY e.producto.familia.nombre, e.producto.nombre")
                    .setParameter("deposito", d)
                    .setParameter("estado", Estado.ACTIVO)
                    .getResultList();
        } else {
            result = abmService.getEM().createQuery("SELECT e FROM Existencia e WHERE e.producto.estado = :estado ORDER BY e.producto.familia.nombre, e.producto.nombre")
                    .setParameter("estado", Estado.ACTIVO)
                    .getResultList();
        }
        return result;
    }
    
}
