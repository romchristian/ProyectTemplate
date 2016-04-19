/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.generico;

import com.ideaspymes.proyecttemplate.stock.model.Familia;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IFamiliaDAO;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IProductoDAO;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author christian.romero
 */
@Named
@SessionScoped
public class ImageResolutor implements Serializable {

    @EJB
    private IProductoDAO productoDAO;
    @EJB
    private IFamiliaDAO familiaDAO;

    public StreamedContent getStreamedImageById() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Get ID value from actual request param.
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            String entidad = context.getExternalContext().getRequestParameterMap().get("entidad");
            StreamedContent R = null;
            if (entidad != null && entidad.length() > 0) {
                switch (entidad) {
                    case "producto":
                        Producto producto = productoDAO.find(Long.valueOf(id));
                        R = new DefaultStreamedContent(new ByteArrayInputStream(producto.getImagen()));
                        break;
                    case "familia":
                        Familia familia = familiaDAO.find(Long.valueOf(id));
                        R = new DefaultStreamedContent(new ByteArrayInputStream(familia.getImagen()));
                        break;
                }
            }

            if (R == null) {
                R = new DefaultStreamedContent();
            }

            return R;
        }
    }
}
