/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mateo
 */
@Entity
@Table(name = "inventario", catalog = "control", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Inventario.findAll", query = "SELECT i FROM Inventario i"),
    @NamedQuery(name = "Inventario.findByIdInventario", query = "SELECT i FROM Inventario i WHERE i.idInventario = :idInventario"),
    @NamedQuery(name = "Inventario.findByStock", query = "SELECT i FROM Inventario i WHERE i.stock = :stock"),
    @NamedQuery(name = "Inventario.findByEsProducto", query = "SELECT i FROM Inventario i WHERE i.esProducto = :esProducto"),
    @NamedQuery(name = "Inventario.findByPrecioSalida", query = "SELECT i FROM Inventario i WHERE i.precioSalida = :precioSalida"),
    @NamedQuery(name = "Inventario.findByPrecioEntrada", query = "SELECT i FROM Inventario i WHERE i.precioEntrada = :precioEntrada"),
    @NamedQuery(name = "Inventario.findByCantidadEntrada", query = "SELECT i FROM Inventario i WHERE i.cantidadEntrada = :cantidadEntrada"),
    @NamedQuery(name = "Inventario.findByCantidadSalida", query = "SELECT i FROM Inventario i WHERE i.cantidadSalida = :cantidadSalida"),
    @NamedQuery(name = "Inventario.findByFechaVencimiento", query = "SELECT i FROM Inventario i WHERE i.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "Inventario.findByCantidadDisponible", query = "SELECT i FROM Inventario i WHERE i.cantidadDisponible = :cantidadDisponible"),
    @NamedQuery(name = "Inventario.findByIva", query = "SELECT i FROM Inventario i WHERE i.iva = :iva")})
public class Inventario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_inventario", nullable = false)
    private Integer idInventario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stock", nullable = false)
    private double stock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "es_producto", nullable = false)
    private boolean esProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_salida", nullable = false)
    private double precioSalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_entrada", nullable = false)
    private double precioEntrada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_entrada", nullable = false)
    private double cantidadEntrada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_salida", nullable = false)
    private double cantidadSalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_vencimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_disponible", nullable = false)
    private double cantidadDisponible;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iva", nullable = false)
    private double iva;
    @JoinColumn(name = "provedor", referencedColumnName = "id_provedor", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Provedor provedor;
    @JoinColumn(name = "producto_inventario", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto productoInventario;
    @JoinColumn(name = "ingrediente_inventario", referencedColumnName = "id_ingrediente")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ingrediente ingredienteInventario;

    public Inventario() {
    }

    public Inventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public Inventario(Integer idInventario, double stock, boolean esProducto, double precioSalida, double precioEntrada, double cantidadEntrada, double cantidadSalida, Date fechaVencimiento, double cantidadDisponible, double iva) {
        this.idInventario = idInventario;
        this.stock = stock;
        this.esProducto = esProducto;
        this.precioSalida = precioSalida;
        this.precioEntrada = precioEntrada;
        this.cantidadEntrada = cantidadEntrada;
        this.cantidadSalida = cantidadSalida;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidadDisponible = cantidadDisponible;
        this.iva = iva;
    }

    public Integer getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public boolean getEsProducto() {
        return esProducto;
    }

    public void setEsProducto(boolean esProducto) {
        this.esProducto = esProducto;
    }

    public double getPrecioSalida() {
        return precioSalida;
    }

    public void setPrecioSalida(double precioSalida) {
        this.precioSalida = precioSalida;
    }

    public double getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(double precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public double getCantidadEntrada() {
        return cantidadEntrada;
    }

    public void setCantidadEntrada(double cantidadEntrada) {
        this.cantidadEntrada = cantidadEntrada;
    }

    public double getCantidadSalida() {
        return cantidadSalida;
    }

    public void setCantidadSalida(double cantidadSalida) {
        this.cantidadSalida = cantidadSalida;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(double cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public Provedor getProvedor() {
        return provedor;
    }

    public void setProvedor(Provedor provedor) {
        this.provedor = provedor;
    }

    public Producto getProductoInventario() {
        return productoInventario;
    }

    public void setProductoInventario(Producto productoInventario) {
        this.productoInventario = productoInventario;
    }

    public Ingrediente getIngredienteInventario() {
        return ingredienteInventario;
    }

    public void setIngredienteInventario(Ingrediente ingredienteInventario) {
        this.ingredienteInventario = ingredienteInventario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInventario != null ? idInventario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventario)) {
            return false;
        }
        Inventario other = (Inventario) object;
        if ((this.idInventario == null && other.idInventario != null) || (this.idInventario != null && !this.idInventario.equals(other.idInventario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entidad.Inventario[ idInventario=" + idInventario + " ]";
    }
    
}
