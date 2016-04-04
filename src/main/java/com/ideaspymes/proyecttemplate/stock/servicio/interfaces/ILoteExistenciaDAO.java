/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.interfaces;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.QueryParameter;
import com.ideaspymes.proyecttemplate.stock.model.LoteExistencia;
import java.util.List;
import javax.ejb.Local;





/**
 *
 * @author Acer
 */
@Local
public interface ILoteExistenciaDAO extends AbstractDAO<LoteExistencia> {

    @Override
    LoteExistencia create(LoteExistencia entity);

    @Override
    LoteExistencia edit(LoteExistencia entity);

    @Override
    LoteExistencia find(Object id);

    @Override
    List<LoteExistencia> findAll();

    @Override
    List<LoteExistencia> findAll(String query, QueryParameter params);

    @Override
    void remove(LoteExistencia entity);
    
}
