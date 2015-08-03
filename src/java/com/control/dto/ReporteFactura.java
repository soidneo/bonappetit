/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.dto;

/**
 *
 * @author Andres
 */
public class ReporteFactura {
    private String producto;
    private String cantidad;
    private String iva;
    private String dcto;
    private String total;

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getDcto() {
        return dcto;
    }

    public void setDcto(String dcto) {
        this.dcto = dcto;
    }

   

 

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
}
