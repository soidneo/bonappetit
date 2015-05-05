/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.dto;

/**
 *
 * @author mateo
 */
import com.control.entidad.TProductoCategoria;

/**
 *
 * @author Asus
 */
public class PedidoDetalleDto {
    //que tanto lleva el pedido que mas seria de agregarle? mesa, cliente,dcto, eso no se me ocurre algo mas
    //el iva en el detalle  o ese es el detalle?
    //hagamos dos clases java toca maestro detalle esa que sea el detalle osea por pedio adicionado
    //se supone que las mesas todos ban a hacer en la misma para ese pedido
    
    
    private TProductoCategoria producto;
    private int cantidad;
    private int iva;
    private double descuento;
    private double total;

    public TProductoCategoria getProducto() {
        return producto;
    }

    public void setProducto(TProductoCategoria producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    } //ve seguro es Tproductocategoria?
    //no es producto ? puede ser pero Tproducto tiene el producto da lo mismo pero como alla tenemos /
    //es mas facil tenerlo asi
}