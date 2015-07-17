/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.controlador.util;

import com.control.dao.ModoPagoFacade;
import com.control.dao.VentasFacade;
import com.control.dto.PedidoDetalleDto;
import com.control.dto.PedidoMaestro;
import com.control.entidad.ModoPago;
import com.control.entidad.VentaDetalle;
import com.control.entidad.Ventas;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author mateo
 */
@ManagedBean(name="cajaPedidosControlador")
@ApplicationScoped
public class CajaPedidosControlador {
    
    private List<PedidoMaestro> listaPedidos;
    private PedidoMaestro pedidoMes;
    private List<ModoPago> modoPagos;
    private ModoPago modoPago;
    @EJB
    private VentasFacade ventaEjb;
    @EJB
    private ModoPagoFacade modoPagoEjb;

    public List<PedidoMaestro> getListaPedidos() {
        if(listaPedidos==null){
            listaPedidos=new ArrayList<PedidoMaestro>();
        }
        return listaPedidos;
    }
    
    @PostConstruct
    public void iniciarServicio(){
        listaPedidos=new ArrayList<PedidoMaestro>();
        modoPagos=modoPagoEjb.findAll();
        modoPago=new ModoPago();
    }

    public void setListaPedidos(List<PedidoMaestro> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }
    
    public void agregarPedido(PedidoMaestro pedido){
        if(!this.listaPedidos.contains(pedido)){
            this.listaPedidos.add(pedido); 
        }      
    }

    public PedidoMaestro getPedidoMes() {
        return pedidoMes;
    }

    public void setPedidoMes(PedidoMaestro pedidoMes) {
        this.pedidoMes = pedidoMes;
    }

    public void asignarPedido(PedidoMaestro pedido){
      this.pedidoMes=pedido;
      this.pedidoMes.setTotal(0);
      this.pedidoMes.setIvaTotal(0);
      this.pedidoMes.setTotalNeto(0);
      for(PedidoDetalleDto pedidoDet:pedidoMes.getDetallesPedido()){
          pedidoMes.setTotalNeto(pedidoDet.getTotal()+pedidoMes.getTotal());
          pedidoMes.setIvaTotal( pedidoMes.getIvaTotal()+pedidoDet.getProducto().getIdProducto().getIva());
          pedidoMes.setTotal(pedidoMes.getTotalNeto());
      }
      pedidoMes.setTotal(
              (this.pedidoMes.getTotal()*(this.pedidoMes.getIvaTotal()/100))+this.pedidoMes.getTotal());
    }

    public List<ModoPago> getModoPagos() {
        return modoPagos;
    }

    public void setModoPagos(List<ModoPago> modoPagos) {
        this.modoPagos = modoPagos;
    }

    public ModoPago getModoPago() {
        return modoPago;
    }

    public void setModoPago(ModoPago modoPago) {
        this.modoPago = modoPago;
    }
    
    
    
    public void confirmarPedido(){       
        try{
            Ventas venta=new Ventas();
            venta.setCliente(pedidoMes.getCliente());
            venta.setFecha(new Date());   
            venta.setMesero(pedidoMes.getMesero());
            venta.setTotal(pedidoMes.getTotal());
            venta.setFactura(pedidoMes.getFactura());
            venta.setModoPago(modoPagoEjb.find(modoPago.getIdModoPago()));
            venta.setMesa(pedidoMes.getMesa());
            venta.setVentaDetalleList(new ArrayList<VentaDetalle>());
            venta.setSubtotal(venta.getTotal());
            for(PedidoDetalleDto detalles:pedidoMes.getDetallesPedido()){
                VentaDetalle  detalle=new VentaDetalle();
                detalle.setVenta(venta);
                detalle.setCantidad(detalles.getCantidad());
                detalle.setProducto(detalles.getProducto().getIdProducto());
                detalle.setIva(10);
                detalle.setDescuento(10);
                venta.getVentaDetalleList().add(detalle);
            }
            ventaEjb.create(venta);
        }catch(Exception e){}
    }
    
    public void calcularDescuento(){
        double netoIva=this.pedidoMes.getTotalNeto()+(this.pedidoMes.getTotalNeto()*this.pedidoMes.getIvaTotal()/100);
        this.pedidoMes.setTotal(netoIva-
                ((netoIva)*this.pedidoMes.getDescuentoTotal()/100));
    }

    /**
     * Creates a new instance of CajaPedidosControlador
     */
    public CajaPedidosControlador() {
    }
}
