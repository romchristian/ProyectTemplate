/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.generico;

import com.ideaspymes.proyecttemplate.web.EmpresaConsultaBean;
import com.ideaspymes.proyecttemplate.web.converters.EmpresaConverter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.convert.Converter;

/**
 *
 * @author Christian
 */
public class FiltroGenerico {

    private String campo;
    private String descripcion;
    private String tipo;
    private String tipoCampo;
    private String campoDescripcion;
    private String valorString;
    private Integer valorInteger;
    private Double valorDouble;
    private BigDecimal valorBigDecimal;
    private Long valorLong;
    private Date valorDateInicio;
    private Date valorDateFin;
    private Object valorEntidadId;

    public FiltroGenerico(String descripcion, String campo, String tipo, String tipoCampo, String campoDescripcion) {
        this.descripcion = descripcion;
        this.campo = campo;
        this.tipo = tipo;
        this.tipoCampo = tipoCampo;
        this.campoDescripcion = campoDescripcion;
    }

    public String getCampoDescripcion() {
        return campoDescripcion;
    }

    public void setCampoDescripcion(String campoDescripcion) {
        this.campoDescripcion = campoDescripcion;
    }

    public Object getValorEntidadId() {
        return valorEntidadId;
    }

    public void setValorEntidadId(Object valorEntidadId) {
        this.valorEntidadId = valorEntidadId;
    }

    public String getTipoCampo() {
        return tipoCampo;
    }

    public void setTipoCampo(String tipoCampo) {
        this.tipoCampo = tipoCampo;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValorString() {
        return valorString;
    }

    public void setValorString(String valorString) {
        this.valorString = valorString;
    }

    public Integer getValorInteger() {
        return valorInteger;
    }

    public void setValorInteger(Integer valorInteger) {
        this.valorInteger = valorInteger;
    }

    public Double getValorDouble() {
        return valorDouble;
    }

    public void setValorDouble(Double valorDouble) {
        this.valorDouble = valorDouble;
    }

    public BigDecimal getValorBigDecimal() {
        return valorBigDecimal;
    }

    public void setValorBigDecimal(BigDecimal valorBigDecimal) {
        this.valorBigDecimal = valorBigDecimal;
    }

    public Long getValorLong() {
        return valorLong;
    }

    public void setValorLong(Long valorLong) {
        this.valorLong = valorLong;
    }

    public Date getValorDateInicio() {
        return valorDateInicio;
    }

    public void setValorDateInicio(Date valorDateInicio) {
        this.valorDateInicio = valorDateInicio;
    }

    public Date getValorDateFin() {
        return valorDateFin;
    }

    public void setValorDateFin(Date valorDateFin) {
        this.valorDateFin = valorDateFin;
    }

    public String getCadenaFiltro() {
        String R = "";
        switch (tipo) {
            case "like":
                R = " AND " + campo + " like '%" + valorString + "%' ";
                break;
            case "rangoFecha":
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                R = " AND " + campo + " between '" + sdf.format(valorDateInicio) + "' AND '" + sdf.format(valorDateFin) + "' ";
                break;
            case "numero":
                R = " AND " + campo + " = " + valorInteger + " ";
                break;
            case "selectOne":
                R = " AND " + campo + "_id = " + ((IAuditable) valorEntidadId).getId() + " ";
                break;

            case "autocomplete":
                R = " AND " + campo + "_id = " + ((IAuditable) valorEntidadId).getId() + " ";
                break;

        }
        return R;
    }

    public boolean tieneValor() {
        boolean R = false;
        if (valorString != null && valorString.length() > 0) {
            R = true;
        }

        if (valorInteger != null && valorInteger > 0) {
            R = true;
        }

        if (valorDouble != null  && valorDouble > 0) {
            R = true;
        }

        if (valorLong != null  && valorLong > 0) {
            R = true;
        }

        if (valorBigDecimal != null  && valorBigDecimal.compareTo(BigDecimal.ZERO) > 0) {
            R = true;
        }

        if (valorDateInicio != null) {
            R = true;
        }

        if (valorDateFin != null) {
            R = true;
        }

        System.out.println("Valor Entidad : " + valorEntidadId);
        if (valorEntidadId != null) {
            R = true;
            System.out.println("Valor Entidad Entre: " + valorEntidadId);
        }
        return R;
    }

}
