/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.generico;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Christian
 */

@ManagedBean
public class IndexManagedBean  {

    private UploadedFile file;
    private String path;
    private StreamedContent imagenCortada;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    private String imagemTemporaria;
    private CroppedImage croppedImage;
    private boolean exibeBotao = false;

    public StreamedContent getImagenCortada() {
        return imagenCortada;
    }

    public void setImagenCortada(StreamedContent imagenCortada) {
        this.imagenCortada = imagenCortada;
    }

    
    
    public String getImagemTemporaria() {
        return imagemTemporaria;
    }

    public void setImagemTemporaria(String imagemTemporaria) {
        this.imagemTemporaria = imagemTemporaria;
    }

    public CroppedImage getCroppedImage() {
        return croppedImage;
    }

    public void setCroppedImage(CroppedImage croppedImage) {
        this.croppedImage = croppedImage;
    }

    public boolean isExibeBotao() {
        return exibeBotao;
    }

    public void setExibeBotao(boolean exibeBotao) {
        this.exibeBotao = exibeBotao;
    }

  

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        if (event.getFile() != null) {

            file = event.getFile();
            imagemTemporaria = file.getFileName();
            System.out.println("FILE: " + file);
            guardar();
        }
    }

    public String crop() {
        System.out.println("CORTO!");
        //setImagenCortada(new DefaultStreamedContent(new ByteArrayInputStream(croppedImage.getBytes())));
        return null;
    }

    public void copyFile() {
        try {

            System.out.println("FILE en COPY: " + file);
            FacesContext fc = FacesContext.getCurrentInstance();
            String destination = fc.getExternalContext().getInitParameter("uploadDirectory");

            final String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            destination += uuid;

            imagemTemporaria = uuid + file.getFileName();
            OutputStream out = new FileOutputStream(new File(destination + file.getFileName()));

            System.out.println("DIR:  " + destination);
            int read = 0;
            byte[] bytes = new byte[1024];

            InputStream in = file.getInputstream();
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("New file created!");

        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
    }

    public void guardar() {
        if (file != null) {
            copyFile();
        }

    }
}
