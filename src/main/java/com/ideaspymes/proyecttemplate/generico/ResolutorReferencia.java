/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.generico;

import com.ideaspymes.proyecttemplate.configuracion.model.Contacto;
import com.ideaspymes.proyecttemplate.stock.model.Deposito;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Acer Esta clase debe extraer de una propiedad de tipo "Clase:id" el
 * nombre de la clase y el id del objeto referenciado
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ResolutorReferencia {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    public Contacto getCliente(String referencia) {
        Object o = getRefObject(referencia);
        if (o != null && o instanceof Contacto) {
            return (Contacto) o;
        } else {
            return null;
        }
    }

    public Deposito getDeposito(String referencia) {
        Object o = getRefObject(referencia);
        if (o != null && o instanceof Deposito) {
            return (Deposito) o;
        } else {
            return null;
        }
    }

    public Object getRefObject(String referencia) {
        Object R = null;
        try {
            if (referencia != null) {
                String[] arrayRef = referencia.split(":");
                System.out.println("Class: " + arrayRef[0]);
                Class c = Class.forName(arrayRef[0]);
                System.out.println("ID: " + arrayRef[1]);
                Long id = Long.parseLong(arrayRef[1]);
                R = abmService.getEM().find(c, id);
                System.out.println("Paso");
            }
        } catch (Exception e) {
        }
        return R;
    }

}
