/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.generico;

import com.ideaspymes.proyecttemplate.configuracion.model.enums.Estado;
import com.ideaspymes.proyecttemplate.stock.model.ComprobanteStock;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.imageio.stream.FileImageOutputStream;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.coobird.thumbnailator.Thumbnails;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author christian
 */
public abstract class BeanGenerico<T> implements Serializable {

    @Inject
    private Credencial credencial;
    private long id;

    private T actual;

    private List<T> listado = new ArrayList<>();

    public static final String OBJ = "obj";

    private UIComponent dataGridDisponibles;

    private Map<String, Double> mapTotales = new HashMap<>();
    private boolean muestraTotales = false;

    private UploadedFile file;
    private CroppedImage croppedImage;
    private String currentImageName;
    private String newImageName;

    public void upload() {

        if (croppedImage != null) {
            try {

                if (getActual() instanceof IConImagen) {

                    ((IConImagen) getActual()).setImagen(croppedImage.getBytes());

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
                }

            } catch (Exception e) {
                System.out.println("Exception-File Upload." + e.getMessage());
            }
        }
    }

//Para el autocomplete
    public List<T> completar(String query) {
        List<T> sugerencias = new ArrayList<>();

        for (T p : getEjb().findAll()) {
            if (p.toString().toUpperCase().startsWith(query.toUpperCase())) {
                sugerencias.add(p);
            }
        }

        return sugerencias;
    }

    //Para el autocomplete
    public void siCambiaAutocomplete(SelectEvent event) {
    }

    public T getActual() {
        if (actual == null) {
            actual = getNuevo();
        }
        return actual;
    }

    public void setActual(T actual) {
        this.actual = actual;
    }

    public Credencial getCredencial() {
        return credencial;
    }

    public abstract AbstractDAO<T> getEjb();

    public abstract T getNuevo();

    public abstract Converter getConverter();

    public String create() {
        if (getActual() instanceof IConImagen) {
            upload();
        }
        if (getEjb().create(getActual()) != null) {

            JsfUtil.addSuccessMessage("Se creó exitosamente!");
            setActual(null);
            return "listado.xhtml?faces-redirect=true";
        } else {
            return null;
        }

    }

    public String edit() {
        if (getActual() instanceof IConImagen) {
            upload();
        }

        if (getEjb().edit(getActual()) == null) {

            JsfUtil.addErrorMessage("Otro usuario realizó una modificación sobre el mismo dato,y pruebe de nuevo");
            return null;
        }

        JsfUtil.addSuccessMessage("Se guardó exitosamente!");
        setActual(null);
        return "listado.xhtml?faces-redirect=true";
    }

    public String remove() {
        getEjb().remove(getActual());
        setActual(null);
        JsfUtil.addSuccessMessage("Se removió exitosamente!");
        return "listado.xhtml?faces-redirect=true";
    }

    public T find(Object id) {
        return (T) getEjb().find(id);
    }

    public List<T> findAll() {
        return getEjb().findAll();
    }

    public List<T> findAllFiltros() {
        if (listado == null) {
            listado = new ArrayList<>();
        }

        return listado;
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(findAll(), true);
    }

    public String preparaEdicion(T obj) {
        setActual(obj);
        return "edita.xhtml?faces-redirect=true";
    }

    public String preparaCreacion() {
        setActual(null);
        return "nuevo.xhtml?faces-redirect=true";
    }

    public String nuevo() {
        setActual(null);
        return "nuevo.xhtml?faces-redirect=true";
    }

    public UIComponent getDataGridDisponibles() {
        return dataGridDisponibles;
    }

    public void setDataGridDisponibles(UIComponent dataGridDisponibles) {
        this.dataGridDisponibles = dataGridDisponibles;
    }

    public List<T> getListado() {
        return listado;
    }

    public void setListado(List<T> listado) {
        this.listado = listado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String cargaDatos() {
        if (id > 0) {
            setActual(getEjb().find(id));
        }
        return null;
    }

    public Map<String, Double> getMapTotales() {
        return mapTotales;
    }

    public void setMapTotales(Map<String, Double> mapTotales) {
        this.mapTotales = mapTotales;
    }

    public boolean isMuestraTotales() {
        return muestraTotales;
    }

    public void setMuestraTotales(boolean muestraTotales) {
        this.muestraTotales = muestraTotales;
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

            File imagenCortada = new File(newFileName);
            System.out.println("Imagen Original: " + imagenCortada.getUsableSpace());
            
            Thumbnails.of(imagenCortada).size(100,100)
                    .toFile(new File(newFileName+"optimizado.png"));
            
            File optimizado = new File(newFileName+"optimizado.png");
            System.out.println("Imagen Optimizada: " + imagenCortada.getUsableSpace());
            
            imageOutput = new FileImageOutputStream(optimizado);
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

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String toggleActivavion() {
        if (getActual() instanceof IAuditable) {
            if (((IAuditable) getActual()).getEstado() == Estado.ACTIVO) {
                ((IAuditable) getActual()).setEstado(Estado.INACTIVO);
            } else {
                ((IAuditable) getActual()).setEstado(Estado.ACTIVO);
            }

            getEjb().edit(getActual());
        }
        return "listado.xhtml";
    }
}
