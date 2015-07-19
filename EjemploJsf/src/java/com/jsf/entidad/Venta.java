/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsf.entidad;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.validation.constraints.NotNull;

/**
 *
 * @author mateo
 */
@Entity
@Table(name = "venta", catalog = "ferreteria", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v"),
    @NamedQuery(name = "Venta.findByIdVenta", query = "SELECT v FROM Venta v WHERE v.idVenta = :idVenta"),
    @NamedQuery(name = "Venta.findByCantidadVenta", query = "SELECT v FROM Venta v WHERE v.cantidadVenta = :cantidadVenta"),
    @NamedQuery(name = "Venta.findByTotal", query = "SELECT v FROM Venta v WHERE v.total = :total")})
public class Venta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_venta", nullable = false)
    private Integer idVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_venta", nullable = false)
    private BigInteger cantidadVenta;
    @Column(name = "total")
    private BigInteger total;
    @JoinColumn(name = "cliente", referencedColumnName = "usuario", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuarios cliente;
    @JoinColumn(name = "producto", referencedColumnName = "id_producto", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Producto producto;

    public Venta() {
    }

    public Venta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Venta(Integer idVenta, BigInteger cantidadVenta) {
        this.idVenta = idVenta;
        this.cantidadVenta = cantidadVenta;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public BigInteger getCantidadVenta() {
        return cantidadVenta;
    }

    public void setCantidadVenta(BigInteger cantidadVenta) {
        this.cantidadVenta = cantidadVenta;
    }

    public BigInteger getTotal() {
        return total;
    }

    public void setTotal(BigInteger total) {
        this.total = total;
    }

    public Usuarios getCliente() {
        return cliente;
    }

    public void setCliente(Usuarios cliente) {
        this.cliente = cliente;
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
        hash += (idVenta != null ? idVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.idVenta == null && other.idVenta != null) || (this.idVenta != null && !this.idVenta.equals(other.idVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsf.entidad.Venta[ idVenta=" + idVenta + " ]";
    }
    
}
