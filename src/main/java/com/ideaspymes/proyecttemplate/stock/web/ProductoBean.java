/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.stock.web.converters.ProductoConverter;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IProductoDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author christian
 */
@Named
@ViewScoped
public class ProductoBean extends BeanGenerico<Producto> implements Serializable {

    @EJB
    private IProductoDAO ejb;
    private UploadedFile file;

    @Override
    public AbstractDAO<Producto> getEjb() {
        return ejb;
    }

    @Override
    public Producto getNuevo() {
        return new Producto();
    }

    @Override
    public Converter getConverter() {
        return new ProductoConverter();
    }

    @Override
    public String create() {
        upload();

        System.out.println("IMAGEN : " + getActual().getImagen());
        String R = super.create();
        return R;
    }

    @Override
    public String edit() {
        upload();
        String R = super.edit();
        return R;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {

        if (file != null) {
            try {
                getActual().setImagen(readImageOldWay(file.getInputstream()));
                //getActual().setImagen(file.getContents());
            } catch (Exception e) {
                System.out.println("Exception-File Upload." + e.getMessage());
            }
        }
    }

    public byte[] readImageOldWay(InputStream is) throws IOException {

        // Get the size of the file
        long length = file.getSize();
        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getFileName());
        }
        // Close the input stream and return bytes
        is.close();
        return bytes;
    }
}
