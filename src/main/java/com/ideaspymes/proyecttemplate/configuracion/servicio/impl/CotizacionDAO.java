/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.ICotizacionDAO;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.configuracion.model.Cotizacion;
import com.ideaspymes.proyecttemplate.configuracion.model.Usuario;
import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
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
public class CotizacionDAO implements ICotizacionDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Cotizacion create(Cotizacion entity) {
        return abmService.create(entity);
    }

    @Override
    public Cotizacion edit(Cotizacion entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Cotizacion entity) {
        abmService.delete(entity);
    }

    @Override
    public Cotizacion find(Object id) {
        return abmService.find(id, Cotizacion.class);
    }

    @Override
    public List<Cotizacion> findAll() {
        return abmService.getEM().createQuery("select obj from Cotizacion obj where obj.estado = :estado")
                .setParameter("estado", Estado.ACTIVO)
                .getResultList();
    }

    @Override
    public List<Cotizacion> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

    @Override
    public List<Cotizacion> findAll(String query, QueryParameter params, int firt, int pageSize) {
        return abmService.findByQuery(query, params.parameters(), firt, pageSize);
    }

    @Override
    public List<Cotizacion> findFilter(String consulta, int first, int pageSize) {
        List<Cotizacion> items = new ArrayList<>();
        if (consulta != null) {
            System.out.println("Consulta: " + consulta);
            Query query = abmService.getEM().createNativeQuery(consulta, Cotizacion.class);
            if (first > 0) {
                query.setFirstResult(first);
            }

            if (pageSize > 0) {
                query.setMaxResults(pageSize);
            }

            items = (List<Cotizacion>) query.getResultList();

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
    public List<Cotizacion> completar(String matchText) {
        List<Cotizacion> sugerencias = new ArrayList<>();

        if (matchText != null && matchText.length() > 0) {
            String consulta = "select z.* from cotizacion z inner join moneda m on z.moneda_id = m.id  where z.estado = 'ACTIVO' and upper(m.nombre) like '%" + matchText.toUpperCase().trim() + "%' order by m.nombre";
            Query query = abmService.getEM().createNativeQuery(consulta, Cotizacion.class);
            query.setMaxResults(AbstractDAO.AUTOCOMPLETE_MAX_RESULS);
            sugerencias = query.getResultList();
        }

        return sugerencias;
    }

    @Override
    public Cotizacion getCotizacionDefault(Usuario usuario) {
        return find(1L);
    }

}
