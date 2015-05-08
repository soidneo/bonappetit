/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "venta_detalle", catalog = "control", schema = "public")
@NamedQueries({
    @NamedQuery(name = "VentaDetalle.findAll", query = "SELECT v FROM VentaDetalle v"),
    @NamedQuery(name = "VentaDetalle.findByIdVentaDetalle", query = "SELECT v FROM VentaDetalle v WHERE v.idVentaDetalle = :idVentaDetalle"),
    @NamedQuery(name = "VentaDetalle.findByIva", query = "SELECT v FROM VentaDetalle v WHERE v.iva = :iva"),
    @NamedQuery(name = "VentaDetalle.findByCantidad", query = "SELECT v FROM VentaDetalle v WHERE v.cantidad = :cantidad"),
    @NamedQuery(name = "VentaDetalle.findByDescuento", query = "SELECT v FROM VentaDetalle v WHERE v.descuento = :descuento")})
public class VentaDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_venta_detalle", nullable = false)
    private Integer idVentaDetalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iva", nullable = false)
    private double iva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad", nullable = false)
    private double cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descuento", nullable = false)
    private double descuento;
    @JoinColumn(name = "venta", referencedColumnName = "id_venta", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ventas venta;
    @JoinColumn(name = "producto", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Producto producto;

    public VentaDetalle() {
    }

    public VentaDetalle(Integer idVentaDetalle) {
        this.idVentaDetalle = idVentaDetalle;
    }

    public VentaDetalle(Integer idVentaDetalle, double iva, double cantidad, double descuento) {
        this.idVentaDetalle = idVentaDetalle;
        this.iva = iva;
        this.cantidad = cantidad;
        this.descuento = descuento;
    }

    public Integer getIdVentaDetalle() {
        return idVentaDetalle;
    }

    public void setIdVentaDetalle(Integer idVentaDetalle) {
        this.idVentaDetalle = idVentaDetalle;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVentaDetalle != null ? idVentaDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaDetalle)) {
            return false;
        }
        VentaDetalle other = (VentaDetalle) object;
        if ((this.idVentaDetalle == null && other.idVentaDetalle != null) || (this.idVentaDetalle != null && !this.idVentaDetalle.equals(other.idVentaDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entidad.VentaDetalle[ idVentaDetalle=" + idVentaDetalle + " ]";
    }
    
}
