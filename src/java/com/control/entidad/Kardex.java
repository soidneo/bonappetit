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
@Table(name = "kardex", catalog = "control", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Kardex.findAll", query = "SELECT k FROM Kardex k"),
    @NamedQuery(name = "Kardex.findByIdKardex", query = "SELECT k FROM Kardex k WHERE k.idKardex = :idKardex"),
    @NamedQuery(name = "Kardex.findByStock", query = "SELECT k FROM Kardex k WHERE k.stock = :stock"),
    @NamedQuery(name = "Kardex.findByEsProducto", query = "SELECT k FROM Kardex k WHERE k.esProducto = :esProducto"),
    @NamedQuery(name = "Kardex.findByPrecioSalida", query = "SELECT k FROM Kardex k WHERE k.precioSalida = :precioSalida"),
    @NamedQuery(name = "Kardex.findByPrecioEntrada", query = "SELECT k FROM Kardex k WHERE k.precioEntrada = :precioEntrada"),
    @NamedQuery(name = "Kardex.findByCantidadEntrada", query = "SELECT k FROM Kardex k WHERE k.cantidadEntrada = :cantidadEntrada"),
    @NamedQuery(name = "Kardex.findByCantidadSalida", query = "SELECT k FROM Kardex k WHERE k.cantidadSalida = :cantidadSalida"),
    @NamedQuery(name = "Kardex.findByFechaVencimiento", query = "SELECT k FROM Kardex k WHERE k.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "Kardex.findByCantidadDisponible", query = "SELECT k FROM Kardex k WHERE k.cantidadDisponible = :cantidadDisponible"),
    @NamedQuery(name = "Kardex.findByIva", query = "SELECT k FROM Kardex k WHERE k.iva = :iva")})
public class Kardex implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_kardex", nullable = false)
    private Integer idKardex;
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
    @JoinColumn(name = "producto_kardex", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto productoKardex;

    public Kardex() {
    }

    public Kardex(Integer idKardex) {
        this.idKardex = idKardex;
    }

    public Kardex(Integer idKardex, double stock, boolean esProducto, double precioSalida, double precioEntrada, double cantidadEntrada, double cantidadSalida, Date fechaVencimiento, double cantidadDisponible, double iva) {
        this.idKardex = idKardex;
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

    public Integer getIdKardex() {
        return idKardex;
    }

    public void setIdKardex(Integer idKardex) {
        this.idKardex = idKardex;
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

    public Producto getProductoKardex() {
        return productoKardex;
    }

    public void setProductoKardex(Producto productoKardex) {
        this.productoKardex = productoKardex;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKardex != null ? idKardex.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kardex)) {
            return false;
        }
        Kardex other = (Kardex) object;
        if ((this.idKardex == null && other.idKardex != null) || (this.idKardex != null && !this.idKardex.equals(other.idKardex))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entidad.Kardex[ idKardex=" + idKardex + " ]";
    }
    
}
