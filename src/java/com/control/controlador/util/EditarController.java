/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.controlador.util;

import com.control.entidad.Unidad;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Asus
 */
@ManagedBean(name="dtEditView")
@ViewScoped
public class EditarController {
    private List <Unidad>unidad;

    public List<Unidad> getUnidad() {
        return unidad;
    }

    public void setUnidad(List<Unidad> unidad) {
        this.unidad = unidad;
    }
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Unidad Edited", ((Unidad) event.getObject()).getIdUnidad().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Unidad) event.getObject()).getIdUnidad().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}
