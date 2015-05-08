/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.dao;

import com.control.entidad.VentaDetalle;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Asus
 */
@Stateless
public class VentaDetalleFacade extends AbstractFacade<VentaDetalle> {
    @PersistenceContext(unitName = "ControlPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentaDetalleFacade() {
        super(VentaDetalle.class);
    }
    
}
