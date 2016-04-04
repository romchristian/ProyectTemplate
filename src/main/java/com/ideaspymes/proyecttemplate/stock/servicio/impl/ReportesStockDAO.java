/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.servicio.impl;

import com.ideaspymes.proyecttemplate.generico.ABMService;
import com.ideaspymes.proyecttemplate.stock.model.Rendimiento;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IReportesStockDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;


/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ReportesStockDAO implements IReportesStockDAO {

    @EJB
    private ABMService abms;

    @Override
    public List<Rendimiento> getRendimientos() {
        List<Object[]> lista = abms.getEM()
                .createNativeQuery(ConsultasStock.getRendimiento())
                .getResultList();

        List<Rendimiento> R = new ArrayList<>();
        
        for (Object[] obj : lista) {
            R.add(new Rendimiento((String) obj[0], (Double) obj[1]));
        }
        return R;
    }
}
