/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsf.contolador;

import com.jsf.dao.UsuariosFacade;
import com.jsf.entidad.Usuarios;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author mateo
 */
@ManagedBean
@SessionScoped
public class UsuariosLoginController {
    @EJB
    private UsuariosFacade usuarioEjbFacade;
    private Usuarios usuario;
    private String clave;
    private boolean login;

    /**
     * Creates a new instance of UsuariosLoginController
     */
    public UsuariosLoginController() {
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    @PostConstruct
    public void iniciar(){
        this.login=false;
        this.usuario=new Usuarios();
    }
    
    
    public void loguear(){
        FacesContext context = FacesContext.getCurrentInstance();
        try{        
            List<Usuarios>usuarios=usuarioEjbFacade.findUsuario(this.usuario.getUsuario());
            if(usuarios.isEmpty()){
                login=false;            
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error usuario:", this.usuario.getUsuario() + " No existe"));
                return;
            }
            usuario=usuarios.get(0);
            if(usuario.getClave().equals(clave)){
                login=true;
                context.getExternalContext().redirect("faces/Producto.xhtml");
                return;
            }else{
                login=false;
                 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Clave Incorrecta:", this.usuario.getUsuario()));
                return;
            }
        }catch(Exception e){
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error:", this.usuario.getUsuario()));
        }
    }
    
    
}
