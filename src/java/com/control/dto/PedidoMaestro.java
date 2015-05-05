package com.control.dto;

import java.util.List;

/**
 *
 * @author Asus
 */
public class PedidoMaestro {
    
    private List<PedidoDetalleDto> detallesPedido;
    private int mesa;
   
    private double descuentoTotal;
    private double ivaTotal;
    private double total;

    public List<PedidoDetalleDto> getDetallesPedido() {
        return detallesPedido;
    }

    public void setDetallesPedido(List<PedidoDetalleDto> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public double getDescuentoTotal() {
        return descuentoTotal;
    }

    public void setDescuentoTotal(double descuentoTotal) {
        this.descuentoTotal = descuentoTotal;
    }

    public double getIvaTotal() {
        return ivaTotal;
    }

    public void setIvaTotal(double ivaTotal) {
        this.ivaTotal = ivaTotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
