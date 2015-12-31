/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;

/**
 *
 * @author cromero
 */
@Named(value = "rulesBean")
@SessionScoped
public class RulesBean implements Serializable {

    @EJB
    private RuleEngine engine;
    private String oracion;
    private String resultado;

    public String getOracion() {
        return oracion;
    }

    public void setOracion(String oracion) {
        this.oracion = oracion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    /**
     * Creates a new instance of RulesBean
     */
    public RulesBean() {
    }

    public String ejecutar() throws UnsupportedEncodingException {
        if (oracion != null && oracion.length() > 0) {
            Map<String, String> mapConsultas = new HashMap<>();
            mapConsultas.put("Cuantos vendi hoy", "Traer resumen");
            mapConsultas.put("Comparar ventas de y de hoy", "Traer comparacion");
            String clave = oracion.split(" ")[0];
            String consecuencia = "";
            System.out.println("Clave: " + clave);
            for (String key : mapConsultas.keySet()) {
                if (key.contains(clave)) {
                    consecuencia = mapConsultas.get(key);
                    System.out.println("key: " + key);
                    break;
                }
            }

            System.out.println("Consecuencia: " + consecuencia);
            String R = engine.getKieSession(oracion, consecuencia);

            System.out.println("Resultado: " + R);
            resultado = R;
        }
        return null;
    }
}
