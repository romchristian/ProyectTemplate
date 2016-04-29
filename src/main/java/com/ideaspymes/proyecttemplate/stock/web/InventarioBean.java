/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.stock.web.converters.InventarioConverter;
import com.ideaspymes.proyecttemplate.stock.model.Inventario;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IInventarioDAO;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author christian
 */
@Named
@ConversationScoped
public class InventarioBean extends BeanGenerico<Inventario> implements Serializable {

    @EJB
    private IInventarioDAO ejb;
    @Inject
    private Conversation conversation;

    @Override
    public AbstractDAO<Inventario> getEjb() {
        return ejb;
    }

    @Override
    public Inventario getNuevo() {
        Inventario R = new Inventario();
        R.setFecha(new Date());
        R.setResponsable(getCredencial().getUsuario());
        return R;
    }

    @Override
    public Converter getConverter() {
        return new InventarioConverter();
    }

    public String preparaNuevo() {
        beginConversation();
        return "nuevo.xhtml";
    }

    public String navUpload() {
        return "upload.jsp";
    }

    public String termina() {
        endConversation();
        return "listado.xhtml";
    }

    public void beginConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    public void endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

}
