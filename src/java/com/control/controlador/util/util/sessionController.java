/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.controlador.util.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author Andres
 */
public class sessionController implements PhaseListener {

    /**
     *
     * @return
     */
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        // Do your job here which should run right before the RENDER_RESPONSE.
        System.out.println("pasa por el controller de session");
    }

    

    @Override
    public void afterPhase(PhaseEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
}
