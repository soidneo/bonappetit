/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.controlador;

import com.control.dao.CategoriaFacade;
import com.control.dao.TProductoCategoriaFacade;
import com.control.dto.PedidoDetalleDto;
import com.control.dto.PedidoMaestro;
import com.control.entidad.Categoria;
import com.control.entidad.TProductoCategoria;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.lang3.StringEscapeUtils;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author mateo
 */
@ManagedBean(name = "categoriaController")
@ApplicationScoped
public class Controlador {

    @EJB
    private com.control.dao.CategoriaFacade ejbFacade;
    @EJB
    private TProductoCategoriaFacade ejbProductoFacadel;
    private List<Categoria> items = null;
    private Categoria selected;
    private List<TProductoCategoria> listaProductos;
    private PedidoMaestro pedidoMaestro;
    private TProductoCategoria pedido;
    private PedidoDetalleDto detalle;
    private List<PedidoMaestro> listaPedidos;

    public List<PedidoMaestro> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<PedidoMaestro> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public PedidoDetalleDto getDetalle() {
        return detalle;
    }

    public void setDetalle(PedidoDetalleDto detalle) {
        this.detalle = detalle;
    }

    public TProductoCategoria getPedido() {
        return pedido;
    }

    public void setPedido(TProductoCategoria pedido) {
        this.pedido = pedido;
    }

    public PedidoMaestro getPedidoMaestro() {
        return pedidoMaestro;
    }

    public void setPedidoMaestro(PedidoMaestro pedidoMaestro) {
        this.pedidoMaestro = pedidoMaestro;
    }

    public Categoria getSelected() {
        return selected;
    }

    public void setSelected(Categoria selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CategoriaFacade getFacade() {
        return ejbFacade;
    }

    public Categoria prepareCreate() {
        selected = new Categoria();
        initializeEmbeddableKey();
        return selected;
    }

    public List<TProductoCategoria> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<TProductoCategoria> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public void asignarCategoria() {
        selected = ejbFacade.find(this.selected.getId());
        this.listaProductos = selected.getTProductoCategoriaList();
        //  this.selected=cat;
    }

    public void agregarPedido() {
        pedido = ejbProductoFacadel.find(this.pedido.getSerial());
        detalle.setProducto(pedido);
        pedidoMaestro.getDetallesPedido().add(detalle);
        detalle = new PedidoDetalleDto();
    }

    @PostConstruct
    public void iniciar() {
        this.listaPedidos = new ArrayList<PedidoMaestro>();
        this.items = null;
        this.listaProductos = new ArrayList<TProductoCategoria>();
        this.selected = new Categoria();
        this.pedidoMaestro = new PedidoMaestro();
        this.pedidoMaestro.setDetallesPedido(new ArrayList<PedidoDetalleDto>());
        this.pedido = new TProductoCategoria();
        detalle = new PedidoDetalleDto();
    }

    public List<Categoria> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void agregarPedidoMaestro() {
        this.listaPedidos.add(pedidoMaestro);
        notificarPUSH();
    }

    public void notificarPUSH() {

        String summary = "Nuevo Elemento";
        String detail = "Se agrego a la lista";
        String CHANNEL = "/notify";

        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary), StringEscapeUtils.escapeHtml3(detail)));

    }

    public List<Categoria> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Categoria> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
}
