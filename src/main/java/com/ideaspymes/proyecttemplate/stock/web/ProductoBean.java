/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;

import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.BeanGenerico;
import com.ideaspymes.proyecttemplate.stock.web.converters.ProductoConverter;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IProductoDAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.imageio.stream.FileImageOutputStream;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
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
    private CroppedImage croppedImage;
    private String currentImageName;
    private String newImageName;

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

    public String toggleActivavion() {
        if (getActual().getEstado() == Estado.ACTIVO) {
            getActual().setEstado(Estado.INACTIVO);
        } else {
            getActual().setEstado(Estado.ACTIVO);
        }

        getEjb().edit(getActual());
        return "listado.xhtml";
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

        if (croppedImage != null) {
            try {
                //getActual().setImagen(readImageOldWay(file.getInputstream()));
                //getActual().setImagen(readImageOldWay(cropperBean.getCroppedImage().getBytes()));
                System.out.println("croppedImage: " + croppedImage.getBytes());
                getActual().setImagen(croppedImage.getBytes());

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

    public void fileUploadAction(FileUploadEvent event) {
        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

            FacesContext aFacesContext = FacesContext.getCurrentInstance();
            ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();

            String realPath = context.getRealPath("/");

            File file = new File(realPath + "/imagens/prof/");
            file.mkdirs();

            byte[] arquivo = event.getFile().getContents();
            String caminho = realPath + "/imagens/prof/" + event.getFile().getFileName();
            setCurrentImageName(event.getFile().getFileName());

            FileOutputStream fos = new FileOutputStream(caminho);
            fos.write(arquivo);
            fos.close();

        } catch (Exception ex) {
            System.out.println("Erro no upload de imagem" + ex);
        }
    }

    public String crop() {
        if (croppedImage == null) {
            return null;
        }
        setNewImageName(getRandomImageName());
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = servletContext.getRealPath("") + File.separator + "imagens" + File.separator + "prof" + File.separator + getNewImageName() + ".jpg";

        FileImageOutputStream imageOutput;

        try {

            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(croppedImage.getBytes(), 0, croppedImage.getBytes().length);
            imageOutput.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getRandomImageName() {
        int i = (int) (Math.random() * 100000);

        return String.valueOf(i);
    }

    public String getNewImageName() {
        return newImageName;
    }

    public void setNewImageName(String newImageName) {
        this.newImageName = newImageName;
    }

    public String getCurrentImageName() {
        return currentImageName;
    }

    public void setCurrentImageName(String currentImageName) {
        this.currentImageName = currentImageName;
    }

    public CroppedImage getCroppedImage() {
        return croppedImage;
    }

    public void setCroppedImage(CroppedImage croppedImage) {
        this.croppedImage = croppedImage;
    }
}
