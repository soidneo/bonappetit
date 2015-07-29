/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.controlador.util;

import com.control.dao.CategoriaFacade;
import com.control.dao.ProductoFacade;
import com.control.dao.ProvedorFacade;
import com.control.dao.RecetaFacade;
import com.control.dao.UnidadFacade;
import com.control.entidad.Categoria;
import com.control.entidad.Kardex;
import com.control.entidad.Producto;
import com.control.entidad.Provedor;
import com.control.entidad.Receta;
import com.control.entidad.TProductoCategoria;
import com.control.entidad.Unidad;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Asus
 */
@ManagedBean
@ViewScoped
public class InventarioProductoControlador {

    private Producto producto;
    private Kardex compra;
    private Provedor provedor;
    private List<Provedor> listaProvedor;
    private List<Producto> listaProducto;
    @EJB
    private ProductoFacade productoEjb;
    @EJB
    private ProvedorFacade provedorEjb;
    @EJB
    private CategoriaFacade categoriaEjb;
    @EJB
    private UnidadFacade unidadEjb;
    @EJB
    private RecetaFacade recetaFacade;
    private List<Receta> listaReceta;
    private List<Unidad> listaUnidades;
    private Unidad unidad;
    private List<Categoria> listaCategorias;
    private Categoria categoria;
    private boolean nuevo;

    public List<Receta> getListaReceta() {
        return listaReceta;
    }

    public void setListaReceta(List<Receta> listaReceta) {
        this.listaReceta = listaReceta;
    }

    /**
     * Creates a new instance of InventarioProductoControlador
     */
    public InventarioProductoControlador() {
    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Kardex getCompra() {
        return compra;
    }

    public void setCompra(Kardex compra) {
        this.compra = compra;
    }

    public Provedor getProvedor() {
        return provedor;
    }

    public void setProvedor(Provedor provedor) {
        this.provedor = provedor;
    }

    public List<Provedor> getListaProvedor() {
        return listaProvedor;
    }

    public void setListaProvedor(List<Provedor> listaProvedor) {
        this.listaProvedor = listaProvedor;
    }

    public List<Producto> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Unidad> getListaUnidades() {
        return listaUnidades;
    }

    public void setListaUnidades(List<Unidad> listaUnidades) {
        this.listaUnidades = listaUnidades;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    @PostConstruct
    public void iniciar() {
        this.producto = new Producto();
        this.producto.setRecetaFk(new Receta());
        this.compra = new Kardex();
        this.compra.setPrecioEntrada(Double.parseDouble("0"));
        this.unidad = new Unidad();
        this.categoria = new Categoria();
        this.provedor = new Provedor();

        listaProvedor = provedorEjb.findAll();
        listaProducto = productoEjb.consultarProductosIngrediente(false);
        listaCategorias = categoriaEjb.findAll();
        listaUnidades = unidadEjb.findAll();
        listaReceta = recetaFacade.findAll();
    }

    public void nuevoP() {
        this.unidad = unidadEjb.find(this.unidad.getIdUnidad());
        this.producto.setUnidad(unidad);
        System.out.println("rece:"+this.producto.getRecetaFk().getIdReceta());
        if(this.producto.getRecetaFk().getIdReceta()!=null){
            this.producto.setRecetaFk(recetaFacade.find(this.producto.getRecetaFk().getIdReceta()));
        }else{
            this.producto.setRecetaFk(null);
        }
        productoEjb.create(producto);
        listaProducto=this.productoEjb.findAll();
    }

    public List<Producto> completeProductos(String query) {
        List<Producto> productos = productoEjb.findAll();
        List<Producto> ProductosQuery = new ArrayList<Producto>();
        for (Producto p : productos) {
            if (p.getNombre().contains(query.trim())) {
                ProductosQuery.add(p);
            }
        }
        return ProductosQuery;
    }
    
    public void actualizarkardex(){
        
    }
            

    public void guardarNuevo() {
        try {
            if (!nuevo) {
                producto = productoEjb.find(this.producto.getId());
            }
            TProductoCategoria pCat = new TProductoCategoria();
            categoria = categoriaEjb.find(this.categoria.getId());

            categoria.setTProductoCategoriaList(new ArrayList<TProductoCategoria>());
            pCat.setIdCategoria(categoria);
            pCat.setIdProducto(producto);

            this.provedor = provedorEjb.find(this.provedor.getIdProvedor());

            this.producto.setAdicional(false);
            this.producto.setKardexList(new ArrayList<Kardex>());
            this.producto.setTProductoCategoriaList(new ArrayList<TProductoCategoria>());
            this.producto.getTProductoCategoriaList().add(pCat);

            compra.setProvedor(provedor);
            compra.setProductoKardex(producto);
            compra.setCantidadDisponible(productoEjb.getCantidadDisponible(producto));
            compra.setCantidadDisponible(this.compra.getCantidadEntrada() + compra.getCantidadDisponible());
            this.producto.getKardexList().add(compra);
            productoEjb.edit(producto);
            com.control.controlador.util.util.JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("kardexRegistrado"));
        } catch (Exception e) {
            com.control.controlador.util.util.JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
}
