/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "producto", catalog = "control", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findById", query = "SELECT p FROM Producto p WHERE p.id = :id"),
    @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Producto.findByCostoProceso", query = "SELECT p FROM Producto p WHERE p.costoProceso = :costoProceso"),
    @NamedQuery(name = "Producto.findBySabor", query = "SELECT p FROM Producto p WHERE p.sabor = :sabor"),
    @NamedQuery(name = "Producto.findByTamanio", query = "SELECT p FROM Producto p WHERE p.tamanio = :tamanio"),
    @NamedQuery(name = "Producto.findByAdicional", query = "SELECT p FROM Producto p WHERE p.adicional = :adicional"),
    @NamedQuery(name = "Producto.findByCalorias", query = "SELECT p FROM Producto p WHERE p.calorias = :calorias"),
    @NamedQuery(name = "Producto.findByCostoTotal", query = "SELECT p FROM Producto p WHERE p.costoTotal = :costoTotal"),
    @NamedQuery(name = "Producto.findByCostoVenta", query = "SELECT p FROM Producto p WHERE p.costoVenta = :costoVenta")})
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="PROD_ID_GENERATOR", sequenceName="producto_id_seq",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROD_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre", nullable = false, length = 2147483647)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo_proceso", nullable = false)
    private double costoProceso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "sabor", nullable = false, length = 2147483647)
    private String sabor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tamanio", nullable = false)
    private double tamanio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "adicional", nullable = false)
    private boolean adicional;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "calorias", nullable = false, length = 2147483647)
    private String calorias;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo_total", nullable = false)
    private double costoTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo_venta", nullable = false)
    private double costoVenta;
    @OneToMany(mappedBy = "productoKardex", fetch = FetchType.LAZY)
    private List<Kardex> kardexList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoReceta", fetch = FetchType.LAZY)
    private List<RecetaDet> recetaDetList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto", fetch = FetchType.LAZY)
    private List<VentaDetalle> ventaDetalleList;
    @JoinColumn(name = "unidad", referencedColumnName = "id_unidad", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Unidad unidad;
    @JoinColumn(name = "receta_fk", referencedColumnName = "id_receta")
    @ManyToOne(fetch = FetchType.LAZY)
    private Receta recetaFk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<TProductoCategoria> tProductoCategoriaList;

    public Producto() {
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Producto(Integer id, String nombre, double costoProceso, String sabor, double tamanio, boolean adicional, String calorias, double costoTotal, double costoVenta) {
        this.id = id;
        this.nombre = nombre;
        this.costoProceso = costoProceso;
        this.sabor = sabor;
        this.tamanio = tamanio;
        this.adicional = adicional;
        this.calorias = calorias;
        this.costoTotal = costoTotal;
        this.costoVenta = costoVenta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCostoProceso() {
        return costoProceso;
    }

    public void setCostoProceso(double costoProceso) {
        this.costoProceso = costoProceso;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public double getTamanio() {
        return tamanio;
    }

    public void setTamanio(double tamanio) {
        this.tamanio = tamanio;
    }

    public boolean getAdicional() {
        return adicional;
    }

    public void setAdicional(boolean adicional) {
        this.adicional = adicional;
    }

    public String getCalorias() {
        return calorias;
    }

    public void setCalorias(String calorias) {
        this.calorias = calorias;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public double getCostoVenta() {
        return costoVenta;
    }

    public void setCostoVenta(double costoVenta) {
        this.costoVenta = costoVenta;
    }

    public List<Kardex> getKardexList() {
        return kardexList;
    }

    public void setKardexList(List<Kardex> kardexList) {
        this.kardexList = kardexList;
    }

    public List<RecetaDet> getRecetaDetList() {
        return recetaDetList;
    }

    public void setRecetaDetList(List<RecetaDet> recetaDetList) {
        this.recetaDetList = recetaDetList;
    }

    public List<VentaDetalle> getVentaDetalleList() {
        return ventaDetalleList;
    }

    public void setVentaDetalleList(List<VentaDetalle> ventaDetalleList) {
        this.ventaDetalleList = ventaDetalleList;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public Receta getRecetaFk() {
        return recetaFk;
    }

    public void setRecetaFk(Receta recetaFk) {
        this.recetaFk = recetaFk;
    }

    public List<TProductoCategoria> getTProductoCategoriaList() {
        return tProductoCategoriaList;
    }

    public void setTProductoCategoriaList(List<TProductoCategoria> tProductoCategoriaList) {
        this.tProductoCategoriaList = tProductoCategoriaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entidad.Producto[ id=" + id + " ]";
    }
    
}
