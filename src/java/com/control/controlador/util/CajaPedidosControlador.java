/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.controlador.util;

import com.control.dto.PedidoMaestro;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author mateo
 */
@ManagedBean(name="cajaPedidosControlador")
@ApplicationScoped
public class CajaPedidosControlador {
    
    private List<PedidoMaestro> listaPedidos;

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
    


    /**
     * Creates a new instance of CajaPedidosControlador
     */
    public CajaPedidosControlador() {
    }
}
