/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsf.contolador;

import com.jsf.dao.ProductoFacade;
import com.jsf.entidad.Producto;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author mateo
 */
@ManagedBean
@ViewScoped
public class ProductoController {

    @EJB
    private ProductoFacade productoEjb;
    private Producto producto;
    private List<Producto> productos;
    private UploadedFile archivo;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    /**
     * Creates a new instance of ProductoController
     */
    public ProductoController() {
    }

    @PostConstruct
    public void iniciar() {
        this.productos = productoEjb.findAll();
        this.producto = new Producto();
    }

    public void guardar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            productoEjb.create(producto);
            guardarArchivo(producto.getNombreProducto());
            iniciar();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado", ""));
        } catch (Exception e) {
        }
    }

    public void editar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            productoEjb.edit(producto);
            iniciar();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado", ""));
        } catch (Exception e) {
        }
    }

    public void eliminar(Producto p) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            productoEjb.remove(p);
            iniciar();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", ""));
        } catch (Exception e) {
        }
    }

    public void asignarEditar(Producto p) {
        this.producto = p;
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Exito", event.getFile().getFileName() + " Cargado.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        archivo=event.getFile();
    }
    
    public boolean guardarArchivo(String nombre) {
        try {
            if(this.archivo==null){
                return true;
            }
            ServletContext servContx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String ruta = (String) servContx.getRealPath("/");
            //File documento=new File(ruta+"resources/pdf/"+nombre);
            OutputStream documento = new FileOutputStream(new File(ruta + "resources/imagenes/" + nombre+".pdf"));
            InputStream archivoIn = this.archivo.getInputstream();
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = archivoIn.read(bytes)) != -1) {
                documento.write(bytes, 0, read);
            }
            archivoIn.close();
            documento.flush();
            documento.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
