/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.controlador.util.util;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author mateo
 */
@FacesValidator("cantidadValidator")
public class CantidadValidaitor implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String label;
        HtmlInputText htmlInputText =
                (HtmlInputText) component;
        if (htmlInputText.getLabel() == null
                || htmlInputText.getLabel().trim().equals("")) {
            label = htmlInputText.getId();
        } else {
            label = htmlInputText.getLabel();
        }
        try {
            if (Integer.parseInt(value.toString()) <= 0) {
                FacesMessage facesMessage =
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        label
                        + "", ResourceBundle.getBundle("/Bundle").getString("validacionNumeroEnteroPositivo"));
                throw new ValidatorException(facesMessage);
            }
        } catch (Exception e) {
             FacesMessage facesMessage =
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        label
                        + "", ResourceBundle.getBundle("/Bundle").getString("validacionNumeroEnteroPositivo"));
                throw new ValidatorException(facesMessage);
        }
    }
}
