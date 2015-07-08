/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.controlador.util;

import com.control.dao.VentasFacade;
import com.control.dto.PedidoDetalleDto;
import com.control.dto.PedidoMaestro;
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
    @EJB
    private VentasFacade ventaEjb;

    public List<PedidoMaestro> getListaPedidos() {
        if(listaPedidos==null){
            listaPedidos=new ArrayList<PedidoMaestro>();
        }
        return listaPedidos;
    }
    
    @PostConstruct
    public void iniciarServicio(){
        listaPedidos=new ArrayList<PedidoMaestro>();
    }

    public void setListaPedidos(List<PedidoMaestro> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }
    
    public void agregarPedido(PedidoMaestro pedido){
        this.listaPedidos.add(pedido);       
    }
    
    public void confirmarPedido(PedidoMaestro pedido){
        try{
            Ventas venta=new Ventas();
            venta.setCliente(pedido.getCliente());
            venta.setFecha(new Date());   
            venta.setMesero(pedido.getMesero());
            venta.setTotal(pedido.getTotal());
            venta.setVentaDetalleList(new ArrayList<VentaDetalle>());
            for(PedidoDetalleDto detalles:pedido.getDetallesPedido()){
                VentaDetalle  detalle=new VentaDetalle();
                detalle.setVenta(venta);
                detalle.setCantidad(detalles.getCantidad());
                detalle.setProducto(detalles.getProducto().getIdProducto());
                venta.getVentaDetalleList().add(detalle);
            }
            ventaEjb.create(venta);
        }catch(Exception e){
            
        }
    }

    /**
     * Creates a new instance of CajaPedidosControlador
     */
    public CajaPedidosControlador() {
    }
}
