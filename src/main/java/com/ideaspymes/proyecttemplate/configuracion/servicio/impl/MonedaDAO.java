/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IMonedaDAO;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.configuracion.model.Moneda;
import com.ideaspymes.proyecttemplate.configuracion.model.Usuario;
import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

/*
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class MonedaDAO implements IMonedaDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Moneda create(Moneda entity) {
        return abmService.create(entity);
    }

    @Override
    public Moneda edit(Moneda entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Moneda entity) {
        abmService.delete(entity);
    }

    @Override
    public Moneda find(Object id) {
        return abmService.find(id, Moneda.class);
    }

    @Override
    public List<Moneda> findAll() {
        return abmService.getEM().createQuery("select obj from Moneda obj where obj.estado = :estado")
                .setParameter("estado", Estado.ACTIVO)
                .getResultList();
    }

    @Override
    public List<Moneda> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

    public List<Moneda> findAllParaCotizacion() {

        return abmService.findByQuery("select m from Moneda m where m.estado = :estado and m.monedaLocal = :monedaLocal",
                QueryParameter.where("estado", Estado.ACTIVO).and("monedaLocal", false).parameters());
    }

    @Override
    public List<Moneda> findAll(String query, QueryParameter params, int firt, int pageSize) {
        return abmService.findByQuery(query, params.parameters(), firt, pageSize);
    }

    @Override
    public List<Moneda> findFilter(String consulta, int first, int pageSize) {
        List<Moneda> items = new ArrayList<>();
        if (consulta != null) {
            System.out.println("Consulta: " + consulta);
            Query query = abmService.getEM().createNativeQuery(consulta, Moneda.class);
            if (first > 0) {
                query.setFirstResult(first);
            }

            if (pageSize > 0) {
                query.setMaxResults(pageSize);
            }

            items = (List<Moneda>) query.getResultList();

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
    public List<Moneda> completar(String matchText) {
        List<Moneda> sugerencias = new ArrayList<>();

        if (matchText != null && matchText.length() > 0) {
            String consulta = "select * from moneda where estado = 'ACTIVO' and upper(nombre) like '%" + matchText.toUpperCase().trim() + "%' order by nombre";
            Query query = abmService.getEM().createNativeQuery(consulta, Moneda.class);
            query.setMaxResults(20);
            sugerencias = query.getResultList();
        }

        return sugerencias;
    }

    @Override
    public Moneda getMonedaDefault(Usuario usuario) {
        return find(1L);
    }

}
