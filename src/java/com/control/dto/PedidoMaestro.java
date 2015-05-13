package com.control.dto;

import com.control.entidad.Mesa;
import com.control.entidad.Usuario;
import java.util.List;

/**
 *
 * @author Asus
 */
public class PedidoMaestro {
    
    private List<PedidoDetalleDto> detallesPedido;
    private Mesa mesa;
    private Usuario cliente;
    private Usuario mesero;
   
    private double descuentoTotal;
    private double ivaTotal;
    private double total;

    public List<PedidoDetalleDto> getDetallesPedido() {
        return detallesPedido;
    }

    public void setDetallesPedido(List<PedidoDetalleDto> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
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

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Usuario getMesero() {
        return mesero;
    }

    public void setMesero(Usuario mesero) {
        this.mesero = mesero;
    }
}
