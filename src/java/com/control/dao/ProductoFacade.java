/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.dao;

import com.control.entidad.Producto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Asus
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {
    @PersistenceContext(unitName = "ControlPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
    public List<Producto> consultarProductosIngrediente(boolean v){
        try{
            Query q=getEntityManager().createNamedQuery("Producto.findByAdicional");
            q.setParameter("adicional", v);
            return q.getResultList();
        }catch(Exception e){}
        return null;
    }
    
    public double getCantidadDisponible(Producto p){
        try{
            Query q=getEntityManager().createNativeQuery("Select cantidad_disponible from kardex"
                    + " where producto_kardex=? and id_kardex=(select max("
                    + "t1.id_kardex) from kardex t1"
                    + " where producto_kardex=?)");
            q.setParameter(1, p.getId());
            q.setParameter(2, p.getId());
            return Double.parseDouble(q.getSingleResult().toString());
          
        }catch(Exception e){}
        return  0;
    }
    
}
