/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.interfaces;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.enums.TipoDeposito;
import com.ideaspymes.proyecttemplate.stock.model.Deposito;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Acer
 */
@Local
public interface IDepositoDAO extends AbstractDAO<Deposito> {

    @Override
    Deposito create(Deposito entity);

    @Override
    Deposito edit(Deposito entity);

    @Override
    Deposito find(Object id);

    @Override
    List<Deposito> findAll();

    @Override
    List<Deposito> findAll(String query, QueryParameter params);

    @Override
    void remove(Deposito entity);

    Deposito findDefault();

    Deposito findPorTipo(TipoDeposito tipo);

}
