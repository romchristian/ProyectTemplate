/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.configuracion.servicio.impl;

import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.ITipoDocumentoDAO;
import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.configuracion.model.TipoDocumento;
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
public class TipoDocumentoDAO implements ITipoDocumentoDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public TipoDocumento create(TipoDocumento entity, Usuario usuario) {
        return abmService.create(entity, usuario);
    }

    @Override
    public TipoDocumento edit(TipoDocumento entity, Usuario usuario) {
        return abmService.update(entity, usuario);
    }

    @Override
    public void remove(TipoDocumento entity, Usuario usuario) {
        abmService.delete(entity, usuario);
    }

    @Override
    public TipoDocumento find(Object id) {
        return abmService.find(id, TipoDocumento.class);
    }

    @Override
    public List<TipoDocumento> findAll() {
        return abmService.getEM().createQuery("select obj from TipoDocumento obj where obj.estado = :estado")
                .setParameter("estado", Estado.ACTIVO)
                .getResultList();
    }

    @Override
    public List<TipoDocumento> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

    @Override
    public List<TipoDocumento> findAll(String query, QueryParameter params, int firt, int pageSize) {
        return abmService.findByQuery(query, params.parameters(), firt, pageSize);
    }

    @Override
    public List<TipoDocumento> findFilter(String consulta, int first, int pageSize) {
        List<TipoDocumento> items = new ArrayList<>();
        if (consulta != null) {
            System.out.println("Consulta: " + consulta);
            Query query = abmService.getEM().createNativeQuery(consulta, TipoDocumento.class);
            if (first > 0) {
                query.setFirstResult(first);
            }

            if (pageSize > 0) {
                query.setMaxResults(pageSize);
            }

            items = (List<TipoDocumento>) query.getResultList();

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
    public List<TipoDocumento> completar(String matchText) {
        List<TipoDocumento> sugerencias = new ArrayList<>();

        if (matchText != null && matchText.length() > 0) {
            String consulta = "select * from tipodocumento where estado = 'ACTIVO' and upper(nombre) like '%" + matchText.toUpperCase().trim() + "%' order by nombre";
            Query query = abmService.getEM().createNativeQuery(consulta, TipoDocumento.class);
            query.setMaxResults(20);
            sugerencias = query.getResultList();
        }
        
        return sugerencias;
    }

}
