/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.controlador.util;

import com.control.dao.VentasFacade;
import com.control.entidad.Ventas;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author mateo
 */
@ManagedBean
@ViewScoped
public class ConsultaVentasController {
    @EJB VentasFacade ventasEjbFacade;
    private List<Ventas> listaVentas;
    private Date fechaIng;
    private Date fechaSal;
    private Ventas venta;
    private boolean detalle;
    
   @PostConstruct
   public void iniciar(){
       this.listaVentas=new ArrayList<Ventas>();
       this.detalle=false;
       this.venta=new Ventas();
   }

    public List<Ventas> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(List<Ventas> listaVentas) {
        this.listaVentas = listaVentas;
    }

    public Date getFechaIng() {
        return fechaIng;
    }

    public void setFechaIng(Date fechaIng) {
        this.fechaIng = fechaIng;
    }

    public Date getFechaSal() {
        return fechaSal;
    }

    public void setFechaSal(Date fechaSal) {
        this.fechaSal = fechaSal;
    }
    
   public void consultar(){
       this.detalle=false;
       if(fechaIng.after(fechaSal)){
           this.listaVentas=new ArrayList<Ventas>();
           return;
       }
       this.listaVentas=this.ventasEjbFacade.findByRango(fechaIng, fechaSal);
   }

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
    }

    public boolean isDetalle() {
        return detalle;
    }

    public void setDetalle(boolean detalle) {
        this.detalle = detalle;
    }
    
    public void asignarDetalle(Ventas v){
        this.venta=v;
        this.detalle=true;
    }
   
   
    /**
     * Creates a new instance of ConsultaVentasController
     */
    public ConsultaVentasController() {
    }
}
