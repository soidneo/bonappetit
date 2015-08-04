/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.controlador.util;

import com.control.dao.ProductoFacade;
import com.control.dao.RecetaFacade;
import com.control.entidad.Producto;
import com.control.entidad.Receta;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Andres
 */
@ManagedBean
@ViewScoped
public class ConsultaProductoController {

    @EJB
    private ProductoFacade productoEjbFacade;
    private List<Producto> listaProductos;
    private Producto producto;
    private boolean detalle;
    @EJB
    private RecetaFacade recetaFacade;
    private List<Receta> listaReceta;

    /**
     * Creates a new instance of ConsultaProductoController
     */
    public ConsultaProductoController() {
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public boolean isDetalle() {
        return detalle;
    }

    public void setDetalle(boolean detalle) {
        this.detalle = detalle;
    }

    public List<Receta> getListaReceta() {
        return listaReceta;
    }

    public void setListaReceta(List<Receta> listaReceta) {
        this.listaReceta = listaReceta;
    }

    public void eliminar(Producto p) {
        this.productoEjbFacade.remove(p);
        this.iniciar();
    }

    public void prepararEditar(Producto p) {
        this.producto = p;
        this.detalle = true;
    }
    
    public void cambiarRecetas(){
        this.producto.setRecetaFk(this.recetaFacade.find(this.producto.getRecetaFk().getIdReceta()));
        System.out.println(producto.getRecetaFk().getRecetaDetList().size());
    }

    public void actualizar() {
        try {
            this.productoEjbFacade.edit(producto);
            iniciar();
        } catch (Exception e) {
        }
    }

    @PostConstruct
    public void iniciar() {
        this.detalle = false;
        this.listaProductos = this.productoEjbFacade.findAll();
        this.listaReceta=this.recetaFacade.findAll();
        this.producto = new Producto();
        this.producto.setRecetaFk(new Receta());
    }
}
