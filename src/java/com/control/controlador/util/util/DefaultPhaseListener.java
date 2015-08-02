/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.controlador.util.util;

import com.control.controlador.util.UsuarioLoginControlador;
import java.io.IOException;
import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Andres
 */
public class DefaultPhaseListener implements PhaseListener {

    private UsuarioLoginControlador loginMb;

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        ExternalContext ext = facesContext.getExternalContext();
        String ctxPath = ((ServletContext) ext.getContext()).getContextPath();
        String currentPage = facesContext.getViewRoot().getViewId();
        boolean isLoginPage;
        isLoginPage = (currentPage.lastIndexOf("index.xhtml") > -1);
        if (isLoginPage) {
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
            if (session != null) {
                loginMb = (UsuarioLoginControlador) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{usuarioLogin}", UsuarioLoginControlador.class);
                if (loginMb.isLogin()) {
                 //   try {
                       // ext.redirect(ctxPath + "bienvenida esa va ir aqui");
                //    } catch (IOException ex) {
                 //       throw new FacesException("Sesión no login", ex);
                //    }
                }
            }
            return;
        }
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session == null) {
            try {
                ext.redirect(ctxPath + "/index.xhtml");
            } catch (IOException ex) {
                throw new FacesException("Sesión no login", ex);
            }
        } else {
            loginMb = (UsuarioLoginControlador) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{usuarioLogin}", UsuarioLoginControlador.class);
            if (!loginMb.isLogin()) {
                try {
                    ext.redirect(ctxPath + "/index.xhtml");
                } catch (IOException ex) {
                    throw new FacesException("Sesión no login", ex);
                }
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
       
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
