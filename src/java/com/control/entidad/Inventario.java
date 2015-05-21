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
@Table(name = "inventario", catalog = "control", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Inventario.findAll", query = "SELECT i FROM Inventario i"),
    @NamedQuery(name = "Inventario.findByIdInventarioP", query = "SELECT i FROM Inventario i WHERE i.idInventarioP = :idInventarioP"),
    @NamedQuery(name = "Inventario.findByCantidad", query = "SELECT i FROM Inventario i WHERE i.cantidad = :cantidad")})
public class Inventario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_inventario_p", nullable = false)
    private Integer idInventarioP;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad", nullable = false)
    private double cantidad;
    @JoinColumn(name = "producto_inventario", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Producto productoInventario;

    public Inventario() {
    }

    public Inventario(Integer idInventarioP) {
        this.idInventarioP = idInventarioP;
    }

    public Inventario(Integer idInventarioP, double cantidad) {
        this.idInventarioP = idInventarioP;
        this.cantidad = cantidad;
    }

    public Integer getIdInventarioP() {
        return idInventarioP;
    }

    public void setIdInventarioP(Integer idInventarioP) {
        this.idInventarioP = idInventarioP;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProductoInventario() {
        return productoInventario;
    }

    public void setProductoInventario(Producto productoInventario) {
        this.productoInventario = productoInventario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInventarioP != null ? idInventarioP.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventario)) {
            return false;
        }
        Inventario other = (Inventario) object;
        if ((this.idInventarioP == null && other.idInventarioP != null) || (this.idInventarioP != null && !this.idInventarioP.equals(other.idInventarioP))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entidad.Inventario[ idInventarioP=" + idInventarioP + " ]";
    }
    
}
