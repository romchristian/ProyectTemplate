/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.generico.JsfUtil;
import com.ideaspymes.proyecttemplate.stock.model.DetInventario;
import com.ideaspymes.proyecttemplate.stock.web.converters.InventarioConverter;
import com.ideaspymes.proyecttemplate.stock.model.Inventario;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.model.UnidadMedida;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IInventarioDAO;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IProductoDAO;
import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.servlet.ServletContext;

/**
 *
 * @author christian
 */
@Named
@SessionScoped
public class InventarioInicialBean extends BeanGenerico<Inventario> implements Serializable {

    @EJB
    private IInventarioDAO ejb;
    @EJB
    private IProductoDAO ejbProductoDAO;

    private Producto productoActual;
    private Double cantidadActual = 0d;
    private UnidadMedida unidadMedida;

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Producto getProductoActual() {
        if (productoActual == null) {
            productoActual = new Producto();
        }
        return productoActual;
    }

    public void setProductoActual(Producto productoActual) {
        this.productoActual = productoActual;
    }

    public Double getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(Double cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

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

    public void preparaNuevo() {
        limpia();

    }

    public String navUpload() {
        return "upload";
    }

    public String navNuevoProducto() {
        return "nuevoProducto";
    }

    public String termina() {

        return "listado";
    }

    @Override
    public String create() {

        guardar();

        if (ejb.createInicial(getActual()) != null) {

            JsfUtil.addSuccessMessage("Se creó exitosamente!");
            limpia();
            return "listado.xhtml?faces-redirect=true";
        } else {
            return null;
        }
    }

    public String guardar() {
        System.out.println("Deposito: " + getActual().getDeposito());

        getProductoActual().setUnidadMedidaBase(unidadMedida); //TODO: Revisar esta logica
        upload();

        Producto p = ejbProductoDAO.create(getProductoActual());

        if (getActual().getDetalles() == null) {
            getActual().setDetalles(new ArrayList<DetInventario>());
        }

        DetInventario det = new DetInventario();
        det.setInventario(getActual());
        det.setProducto(p);
        det.setCantidad(cantidadActual);
        det.setUnidadMedida(getUnidadMedida());
        det.setUsuarioUltimaModificacion(getCredencial().getUsuario().getNombre());
        det.setEmpresa(getCredencial().getEmpresa());

        getActual().getDetalles().add(det);

        productoActual = null;
        cantidadActual = 0D;

        return "upload";
    }

    @Override
    public void upload() {

        if (getImageCurrent().getPath() != null) {
            try {

                String nombreImagen = getImageCurrent().getPath();

                System.out.println("Nombre Imagen: " + nombreImagen);
                //FileImageOutputStream imageOutput = new FileImageOutputStream(imagen);
                Path path = Paths.get(getImageCurrent().getPath());
                byte[] content = Files.readAllBytes(path);

                System.out.println("Contenido: " + content);
                getProductoActual().setImagen(content);

                FacesContext aFacesContext = FacesContext.getCurrentInstance();
                ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();

                String realPath = context.getRealPath("/");

                File index = new File(realPath + "/imagens/prof/");
                String[] entries = index.list();
                for (String s : entries) {
                    File currentFile = new File(index.getPath(), s);
                    currentFile.delete();
                }
                if (index.delete()) {
                    System.out.println("Se borro con exito");
                } else {
                    System.out.println("No se borro");
                }

            } catch (Exception e) {
                System.out.println("Exception-File Upload." + e.getMessage());
            }

        }
        getImageCurrent().setPath(null);
    }

    private void limpia() {
        setActual(null);
        productoActual = null;
        cantidadActual = 0d;
        unidadMedida = null;
    }
}
