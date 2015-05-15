/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.dao;

import com.control.entidad.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "ControlPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public List<Usuario> findUsuario(String usuario) {
        Query cq = getEntityManager().createNamedQuery("Usuario.findByUsuario");
        cq.setParameter("usuario", usuario);
        return cq.getResultList();
    }

    public void editar(Usuario usuario) {
        Query cq = getEntityManager().createNativeQuery("delete from usuario_rol_software where usuario=?");
        cq.setParameter(1, usuario.getUsuario());
        cq.executeUpdate();
        getEntityManager().merge(usuario);
    }
}
