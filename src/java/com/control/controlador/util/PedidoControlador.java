/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.controlador.util;

import com.control.dao.CategoriaFacade;
import com.control.dao.KardexFacade;
import com.control.dao.MesaFacade;
import com.control.dao.TProductoCategoriaFacade;
import com.control.dao.UsuarioFacade;
import com.control.dto.PedidoDetalleDto;
import com.control.dto.PedidoMaestro;
import com.control.entidad.Categoria;
import com.control.entidad.Kardex;
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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.StringEscapeUtils;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author mateo
 */
@ManagedBean(name = "pedidoControlador")
@SessionScoped
public class PedidoControlador {

    @EJB
    private com.control.dao.CategoriaFacade ejbFacade;
    @EJB
    private TProductoCategoriaFacade ejbProductoFacadel;
    @EJB
    private MesaFacade ejbMesaFacade;
    @EJB
    private UsuarioFacade usuarioEjb;
    @EJB
    private KardexFacade kardexFacade;
    private List<Categoria> items = null;
    private Categoria selected;
    private List<TProductoCategoria> listaProductos;
    private PedidoMaestro pedidoMaestro;
    private TProductoCategoria pedido;
    private PedidoDetalleDto detalle;
    private List<Mesa> listaMesas;
    private List<PedidoMaestro> listaPedidoMaestro;
    private boolean bandera;
    private List<Usuario> usuarios;
    private boolean editar;

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

    public boolean isBandera() {
        return bandera;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void asignarCategoria() {
        selected = ejbFacade.find(this.selected.getId());
        this.listaProductos = selected.getTProductoCategoriaList();
    }

    public boolean calcularInventario(Producto p) {
        if (p.getRecetaFk() == null || p.getRecetaDetList() == null) {
            int i = p.getKardexList().size() - 1;
            System.out.println("can distp:" + p.getKardexList().get(i).getCantidadDisponible());
            System.out.println("can vent:" + this.detalle.getCantidad());
            if (p.getKardexList().get(i).getCantidadDisponible() < this.detalle.getCantidad()) {
                return true;
            }
        } else {
            for (RecetaDet receta : p.getRecetaFk().getRecetaDetList()) {
                int i = receta.getProductoReceta().getKardexList().size() - 1;

                if (receta.getProductoReceta().getKardexList().get(i).getCantidadDisponible()<
                        (receta.getCantidad()*this.detalle.getCantidad())) {
                    return true;
                }
            }
        }

        return false;
    }

    public void actualizarInventario(Producto p, PedidoDetalleDto detalleP) {
        if (p.getRecetaFk() == null) {
            int i = p.getKardexList().size() - 1;
            Kardex kardex = p.getKardexList().get(i);
            kardex.setCantidadDisponible(kardex.getCantidadDisponible() - detalleP.getCantidad());
            kardexFacade.edit(kardex);
        } else {
            System.out.println("paso por aqui");
            System.out.println("receta:"+p.getRecetaFk().getRecetaDetList().size());
            for (RecetaDet receta : p.getRecetaFk().getRecetaDetList()) {
                int i = receta.getProductoReceta().getKardexList().size() - 1;
                Kardex kardex = receta.getProductoReceta().getKardexList().get(i);
                System.out.println("disp antes:"+kardex.getCantidadDisponible());
                System.out.println("receta:"+receta.getCantidad());
                System.out.println("pidio:"+detalleP.getCantidad());
                kardex.setCantidadDisponible(kardex.getCantidadDisponible() - 
                        (detalleP.getCantidad()*receta.getCantidad()
                        ));
                kardexFacade.edit(kardex);
            }
        }
    }

    public void actualizarInventario(PedidoDetalleDto pedidoDto) {
        Producto p = pedidoDto.getProducto().getIdProducto();
        if (p.getRecetaFk() == null) {
            int i = p.getKardexList().size() - 1;
            Kardex kardex = p.getKardexList().get(i);
            kardex.setCantidadDisponible(kardex.getCantidadDisponible() + pedidoDto.getCantidad());
            kardexFacade.edit(kardex);
        } else {
            for (RecetaDet receta : p.getRecetaDetList()) {
                int i = p.getKardexList().size() - 1;
                Kardex kardex = p.getKardexList().get(i);
                kardex.setCantidadDisponible(kardex.getCantidadDisponible() + pedidoDto.getCantidad());
                kardexFacade.edit(kardex);
            }
        }
    }

    public void modal() {
        this.bandera = true;
    }

    public void agregarPedido() {
        boolean existe = false;
        this.pedido = ejbProductoFacadel.find(this.pedido.getId());
        if (calcularInventario(this.pedido.getIdProducto())) {
            com.control.controlador.util.util.JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("noinventarioVal"));
            return;
        }
        this.detalle.setProducto(pedido);
        this.detalle.setTotal(this.pedido.getIdProducto().getCostoVenta() * this.detalle.getCantidad());
        for (int i = 0; i < pedidoMaestro.getDetallesPedido().size(); i++) {
            if (pedidoMaestro.getDetallesPedido().get(i).getProducto().getId() == detalle.getProducto().getId()) {
                pedidoMaestro.getDetallesPedido().get(i).setCantidad(pedidoMaestro.getDetallesPedido().get(i).getCantidad() + detalle.getCantidad());
                pedidoMaestro.getDetallesPedido().get(i).setTotal(pedidoMaestro.getDetallesPedido().get(i).getCantidad()
                        * pedidoMaestro.getDetallesPedido().get(i).getProducto().getIdProducto().getCostoVenta());
                existe = true;
                break;
            }
        }
        if (!existe) {
            this.pedidoMaestro.getDetallesPedido().add(detalle);
        }

        this.detalle = new PedidoDetalleDto();
        this.selected = new Categoria();
        this.pedido = new TProductoCategoria();
    }

    public void cancelarPedido(PedidoDetalleDto detalle) {
        this.pedidoMaestro.getDetallesPedido().remove(detalle);
    }

    @PostConstruct
    public void iniciar() {

            this.items = getFacade().findAll();
            this.listaMesas = ejbMesaFacade.findAll();
            this.listaProductos = new ArrayList<TProductoCategoria>();
            this.selected = new Categoria();
            this.pedidoMaestro = new PedidoMaestro();
            this.pedidoMaestro.setDetallesPedido(new ArrayList<PedidoDetalleDto>());
            this.pedidoMaestro.setMesa(new Mesa());
            this.pedidoMaestro.setCliente(new Usuario());
            this.pedido = new TProductoCategoria();
            this.detalle = new PedidoDetalleDto();
            usuarios = usuarioEjb.findAll();
            this.bandera = true;
            this.editar = false;
        
    }

    public List<Categoria> getItems() {
        return items;
    }

    public void agregarPedidoMaestro() {
        UsuarioLoginControlador usuarioLogin = (UsuarioLoginControlador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
        pedidoMaestro.setMesa(this.ejbMesaFacade.find(this.pedidoMaestro.getMesa().getIdMesa()));
        pedidoMaestro.setMesero(usuarioLogin.getUsuarioSession());
        CajaPedidosControlador caja = (CajaPedidosControlador) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("cajaPedidosControlador");
        caja.agregarPedido(pedidoMaestro);
        for (PedidoDetalleDto detalleP : this.pedidoMaestro.getDetallesPedido()) {

            this.actualizarInventario(detalleP.getProducto().getIdProducto(), detalleP);
        }
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

    public List<Usuario> completeClientes(String query) {

        return usuarios;
    }

    public String editarPedido(PedidoMaestro p) {
        this.pedidoMaestro = p;
        this.bandera = true;
        this.editar = true;
        for (PedidoDetalleDto detalleP : this.pedidoMaestro.getDetallesPedido()) {
            actualizarInventario(detalleP);
        }
        CajaPedidosControlador caja = (CajaPedidosControlador) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("cajaPedidosControlador");
        caja.getListaPedidos().remove(p);
        
        return "addPedido.xhtml";
    }

    /**
     * Creates a new instance of PedidoControlador
     */
    public PedidoControlador() {
    }
}
