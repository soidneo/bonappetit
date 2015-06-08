/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.controlador.util;


import com.control.dao.CategoriaFacade;
import com.control.dao.MesaFacade;
import com.control.dao.TProductoCategoriaFacade;
import com.control.dao.UsuarioFacade;
import com.control.dto.PedidoDetalleDto;
import com.control.dto.PedidoMaestro;
import com.control.entidad.Categoria;
import com.control.entidad.Inventario;
import com.control.entidad.Mesa;
import com.control.entidad.Producto;
import com.control.entidad.RecetaDet;
import com.control.entidad.TProductoCategoria;
import com.control.entidad.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.StringEscapeUtils;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author mateo
 */
@ManagedBean(name = "pedidoControlador")
@ViewScoped
public class PedidoControlador {
    
    @EJB
    private com.control.dao.CategoriaFacade ejbFacade;
    @EJB
    private TProductoCategoriaFacade ejbProductoFacadel;
    @EJB
    private MesaFacade ejbMesaFacade;
    @EJB
    private UsuarioFacade usuarioEjb;
    
    private List<Categoria> items = null;
    private Categoria selected;
    private List<TProductoCategoria> listaProductos;
    private PedidoMaestro pedidoMaestro;
    private TProductoCategoria pedido;
    private PedidoDetalleDto detalle;
    private List<Mesa> listaMesas;
    private List<PedidoMaestro> listaPedidoMaestro;


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

  
    private CategoriaFacade getFacade() {
        return ejbFacade;
    }



    public List<TProductoCategoria> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<TProductoCategoria> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<Mesa> getListaMesas() {
        return listaMesas;
    }

    public void setListaMesas(List<Mesa> listaMesas) {
        this.listaMesas = listaMesas;
    }

    public List<PedidoMaestro> getListaPedidoMaestro() {
        return listaPedidoMaestro;
    }

    public void setListaPedidoMaestro(List<PedidoMaestro> listaPedidoMaestro) {
        this.listaPedidoMaestro = listaPedidoMaestro;
    }
    
    public void asignarCategoria() {
        selected = ejbFacade.find(this.selected.getId());
        this.listaProductos = selected.getTProductoCategoriaList();
    }
    
    public boolean calcularInventario(Producto p){
        if(p.getRecetaFk()==null){    
            if(this.detalle.getCantidad()>this.detalle.getProducto().getIdProducto().getInventarioList().get(0).getCantidad()){
                return false;
            }
        }
        for(RecetaDet receta:p.getRecetaFk().getRecetaDetList()){
            if(this.detalle.getCantidad()>receta.getCantidad()){
                return false;
            }
        }
        
        return true;
    }
    
    public void agregarPedido() {
        boolean existe=false;
        this.pedido = ejbProductoFacadel.find(this.pedido.getId());
        if(calcularInventario(this.pedido.getIdProducto())){
            com.control.controlador.util.util.JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("noinventarioVal"));
            return;
        }
  
        this.detalle.setTotal(this.pedido.getIdProducto().getCostoVenta()*this.detalle.getCantidad());
        for(int i=0;i<pedidoMaestro.getDetallesPedido().size();i++){
            if(pedidoMaestro.getDetallesPedido().get(i).getProducto().getId()==detalle.getProducto().getId()){
                pedidoMaestro.getDetallesPedido().get(i).setCantidad(pedidoMaestro.getDetallesPedido().get(i).getCantidad()+detalle.getCantidad());
                pedidoMaestro.getDetallesPedido().get(i).setTotal(pedidoMaestro.getDetallesPedido().get(i).getCantidad()*
                        pedidoMaestro.getDetallesPedido().get(i).getProducto().getIdProducto().getCostoVenta());
                        existe=true;
                        break;
            }
        }
        if(!existe){
            this.pedidoMaestro.getDetallesPedido().add(detalle);
        }
        this.detalle = new PedidoDetalleDto();
        this.selected = new Categoria();
        this.pedido = new TProductoCategoria();
    }
    
    public void cancelarPedido(PedidoDetalleDto detalle){
        this.pedidoMaestro.getDetallesPedido().remove(detalle);
    }

    @PostConstruct
    public void iniciar() {      
        this.items = getFacade().findAll();
        this.listaMesas=ejbMesaFacade.findAll();
        this.listaProductos = new ArrayList<TProductoCategoria>();
        this.selected = new Categoria();
        this.pedidoMaestro = new PedidoMaestro();
        this.pedidoMaestro.setDetallesPedido(new ArrayList<PedidoDetalleDto>());
        this.pedidoMaestro.setMesa(new Mesa());
        this.pedidoMaestro.setCliente(new Usuario());
        this.pedido = new TProductoCategoria();
        this.detalle = new PedidoDetalleDto();
    }

    public List<Categoria> getItems() {
        return items;
    }

    public void agregarPedidoMaestro() {
        pedidoMaestro.setMesa(this.ejbMesaFacade.find(this.pedidoMaestro.getMesa().getIdMesa()));
        CajaPedidosControlador caja=(CajaPedidosControlador) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("cajaPedidosControlador");
        caja.agregarPedido(pedidoMaestro);
        notificarPUSH();
        iniciar(); 
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
    
    public List<Usuario> completeClientes(String query){
        List<Usuario> usuarios=usuarioEjb.findAll();
        return usuarios;
    }
    
   
    

    /**
     * Creates a new instance of PedidoControlador
     */
    public PedidoControlador() {
    }
}
