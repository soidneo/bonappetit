/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.controlador.util;

import com.control.dao.UsuarioFacade;
import com.control.entidad.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author mateo
 */
@ManagedBean
@ApplicationScoped
public class UsuarioServicios {
    private List<Usuario> listaCleintes;
    @EJB
    private UsuarioFacade ejbFacade;

    public List<Usuario> getListaCleintes() {
        return listaCleintes;
    }

    public void setListaCleintes(List<Usuario> listaCleintes) {
        this.listaCleintes = listaCleintes;
    }
   
    public List<Usuario> consultarClientes(){
        return ejbFacade.findAll();
    }

    /**
     * Creates a new instance of UsuarioServicios
     */
    @PostConstruct
    public void iniciar(){
        this.listaCleintes=new ArrayList<Usuario>();
    }
    
    public UsuarioServicios() {
    }
}
