/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.controlador.util;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Asus
 */
@ManagedBean(name="dtEditView")
@ViewScoped
public class EditarController {
    public String redir()  {
        System.out.println("si hace");
        return "pm:unidad";
    }
    
    public String handleClose() {
        System.out.println("si hace");
        return "pm:unidad";
    }
 
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


public String navigate() {
    return "#pm:unidad";
}

}
