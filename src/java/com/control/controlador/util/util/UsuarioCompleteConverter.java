/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.controlador.util.util;

import com.control.controlador.util.UsuarioServicios;
import com.control.entidad.Usuario;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author mateo
 */
@FacesConverter("usuarioCompleteConverter")
public class UsuarioCompleteConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            UsuarioServicios usuariosServicio = (UsuarioServicios) context.getExternalContext().getApplicationMap().get("usuarioServicios");
            return usuariosServicio.consultarClientes().get(Integer.parseInt(value));
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return String.valueOf(((Usuario) value).getUsuario());
        } else {
            return null;
        }
    }
}
