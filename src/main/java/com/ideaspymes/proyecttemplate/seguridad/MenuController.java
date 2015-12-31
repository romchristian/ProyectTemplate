/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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

    private List<Modulo> modulos;
    private Modulo moduloActual;
    private MenuModel menuModel;

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    @PostConstruct
    public void init() {
        menuModel = new DefaultMenuModel();

        DefaultSubMenu menu1 = new DefaultSubMenu("Sesiones", "fa fa-money");

        menu1.addElement(new DefaultMenuItem("Su sesion", "fa fa-money", "/main/puntoventa/home.xhtml"));
        menu1.addElement(new DefaultMenuItem("Todas las sesiones", "fa fa-money", "/main/puntoventa/home.xhtml"));
        menu1.addElement(new DefaultMenuItem("Tickets", "fa fa-money", "/main/puntoventa/home.xhtml"));

        menuModel.addElement(menu1);

        DefaultSubMenu menu2 = new DefaultSubMenu("Productos", "fa fa-money");

        menu2.addElement(new DefaultMenuItem("Productos", "fa fa-money", "/main/puntoventa/home.xhtml"));
        menu2.addElement(new DefaultMenuItem("Categorias", "fa fa-money", "/main/puntoventa/home.xhtml"));
        menu2.addElement(new DefaultMenuItem("Unidades de Medida", "fa fa-money", "/main/puntoventa/home.xhtml"));

        menuModel.addElement(menu2);
        cargaModulos();
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

    private void cargaModulos() {
        modulos = new ArrayList<>();
        Modulo modulo1 = new Modulo("Punto de Venta", "icon-printer2 Opac80 Fs22", "/EsqueletoVolt/main/puntoventa/home.xhtml", "Tickets, Facturas..");
        Submenu menuSesiones = new Submenu(modulo1, "Sesiones", "icon-select");
        MenuItem mItemMiSesion = new MenuItem(menuSesiones, "#", "Mi Sesión", "icon-uniE675");
        List<MenuItem> listaItems1 = new ArrayList<>();
        listaItems1.add(mItemMiSesion);
        menuSesiones.setItems(listaItems1);
        Submenu menuProductos = new Submenu(modulo1, "Productos", "icon-select");
        MenuItem mItemProductos = new MenuItem(menuProductos, "#", "Productos", "icon-uniE675");
        List<MenuItem> listaItems2 = new ArrayList<>();
        listaItems1.add(mItemProductos);
        menuProductos.setItems(listaItems2);

        List<Submenu> submenus1 = new ArrayList<>();
        submenus1.add(menuSesiones);
        submenus1.add(menuProductos);

        modulo1.setMenus(submenus1);

        moduloActual = modulo1;
        modulos.add(new Modulo("Contabilidad", "icon-printer2 Opac80 Fs22", "/EsqueletoVolt/main/contabilidad/home.xhtml", "Cuentas, Asientos.."));
        modulos.add(new Modulo("Compras", "icon-printer2 Opac80 Fs22", "/EsqueletoVolt/main/compras/home.xhtml", "Órdenes de compras, etc."));
        modulos.add(new Modulo("Ventas", "icon-printer2 Opac80 Fs22", "/EsqueletoVolt/main/ventas/home.xhtml", "Presupuestos, Pedidos.."));
        modulos.add(new Modulo("Stock", "icon-printer2 Opac80 Fs22", "/EsqueletoVolt/main/stock/home.xhtml", "Stock, Movimientos.."));
        modulos.add(modulo1);
    }

}
