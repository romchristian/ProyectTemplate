/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.enums;

/**
 *
 * @author Acer
 */
public enum TipoComprobanteStock {

    COMPRA("Entrada por Compra"),
    CONSUMO_INTERNO("Salida por Consumo Interno"),
    VENTA("Salida por Venta"),
    AJUSTE_ENTRADA_INVENTARIO("Entrada por Ajuste Inventario"),
    AJUSTE_SALIDA_INVENTARIO("Salida por Ajuste Inventario"),
    TRANSFERENCIA_INTERNA("Transferencia Interna"),
    ENTRADA_PRODUCCION("Entrada por Producción"),
    PERDIDA("Salida por Perdida");

    private String label;

    private TipoComprobanteStock(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
