/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.controlador.util;

import com.control.dao.ProductoFacade;
import com.control.dao.RecetaFacade;
import com.control.entidad.Producto;
import com.control.entidad.Receta;
import com.control.entidad.RecetaDet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author mateo
 */
@ManagedBean
@ViewScoped
public class RecetaController {
    private Receta selected;
    private RecetaDet detalle;
    private List<Producto>listaIngredientes;
    @EJB
    private ProductoFacade productoEjbFacade;
    @EJB
    private RecetaFacade recetaEjbFacade;
    

    public Receta getSelected() {
        return selected;
    }

    public void setSelected(Receta selected) {
        this.selected = selected;
    }

    public RecetaDet getDetalle() {
        return detalle;
    }

    public void setDetalle(RecetaDet detalle) {
        this.detalle = detalle;
    }
    
    public void agragarIngrediente(){
        detalle.setProductoReceta(productoEjbFacade.find(this.detalle.getProductoReceta().getId()));
        selected.getRecetaDetList().add(detalle);
        detalle.setReceta(selected);
    }

    public List<Producto> getListaIngredientes() {
        return listaIngredientes;
    }

    public void setListaIngredientes(List<Producto> listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }
    
    public void prepararReceta(){
        detalle=new RecetaDet();
        detalle=new RecetaDet();
        detalle.setProductoReceta(new Producto());
    }
    
    @PostConstruct
    public void iniciar(){
        selected=new Receta();
        selected.setRecetaDetList(new ArrayList<RecetaDet>());
        selected.setProductoList(new ArrayList<Producto>());
       
        listaIngredientes=this.productoEjbFacade.findAll();
        detalle=new RecetaDet();
        detalle.setProductoReceta(new Producto());
    }
    
    public void guardarReceta(){
        try{
            recetaEjbFacade.create(selected);
        }catch(Exception e){
            
        }
    }
    /**
     * Creates a new instance of RecetaController
     */
    public RecetaController() {
    }
}
