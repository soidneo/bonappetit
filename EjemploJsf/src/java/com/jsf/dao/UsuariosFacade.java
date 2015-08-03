/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsf.dao;

import com.jsf.entidad.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mateo
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "EjemploJsfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Usuarios> findUsuario(String usuario) {
        Query cq = getEntityManager().createNamedQuery("Usuarios.findByUsuario");
        cq.setParameter("usuario", usuario);
        return cq.getResultList();
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
}
