/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.seguridad;

import com.ideaspymes.proyecttemplate.configuracion.model.MenuItem;
import com.ideaspymes.proyecttemplate.configuracion.model.Modulo;
import com.ideaspymes.proyecttemplate.configuracion.model.SubMenu;
import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IModuloDAO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author cromero
 */
@Named
@SessionScoped
public class MenuController implements Serializable {

    @EJB
    private IModuloDAO moduloDAO;
    private List<Modulo> modulos;
    private Modulo moduloActual;
    private MenuModel menuModel;
    private MenuModel breadcrumb;
    
    private long idMenu;

    public long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(long idMenu) {
        this.idMenu = idMenu;
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public Modulo getModuloActual() {
        return moduloActual;
    }

    public void setModuloActual(Modulo moduloActual) {
        this.moduloActual = moduloActual;
    }

    public List<Modulo> getModulos() {
        if (modulos == null) {
            cargaModulos();
        }
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }

    public void cargaModulos() {
        if (modulos == null) {
            modulos = moduloDAO.findAll();
        }

        for (Modulo m : modulos) {
            if (m.getId() == idMenu) {
                moduloActual = m;
                break;
            }
        }

        if (moduloActual != null) {
            menuModel = new DefaultMenuModel();

            for (SubMenu s : moduloActual.getMenus()) {
                DefaultSubMenu submenu = new DefaultSubMenu(s.getLabel(), s.getIcon());
                for (MenuItem mi : s.getItems()) {
                    DefaultMenuItem item = new DefaultMenuItem(mi.getValor(), mi.getIcon(), mi.getUrl()+"?idMenu="+mi.getSubmenu().getModulo().getId());
                    submenu.addElement(item);
                }

                menuModel.addElement(submenu);
            }
        }

    }

}
