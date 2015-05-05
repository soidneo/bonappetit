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

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "receta_det", catalog = "control", schema = "public")
@NamedQueries({
    @NamedQuery(name = "RecetaDet.findAll", query = "SELECT r FROM RecetaDet r"),
    @NamedQuery(name = "RecetaDet.findByIdIngrediente", query = "SELECT r FROM RecetaDet r WHERE r.idIngrediente = :idIngrediente"),
    @NamedQuery(name = "RecetaDet.findByCantidad", query = "SELECT r FROM RecetaDet r WHERE r.cantidad = :cantidad"),
    @NamedQuery(name = "RecetaDet.findByIdRecetaDet", query = "SELECT r FROM RecetaDet r WHERE r.idRecetaDet = :idRecetaDet")})
public class RecetaDet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "id_ingrediente")
    private Integer idIngrediente;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_receta_det", nullable = false)
    private Integer idRecetaDet;
    @JoinColumn(name = "id_receta", referencedColumnName = "id_receta")
    @ManyToOne(fetch = FetchType.LAZY)
    private Receta idReceta;

    public RecetaDet() {
    }

    public RecetaDet(Integer idRecetaDet) {
        this.idRecetaDet = idRecetaDet;
    }

    public Integer getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Integer idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getIdRecetaDet() {
        return idRecetaDet;
    }

    public void setIdRecetaDet(Integer idRecetaDet) {
        this.idRecetaDet = idRecetaDet;
    }

    public Receta getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Receta idReceta) {
        this.idReceta = idReceta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRecetaDet != null ? idRecetaDet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecetaDet)) {
            return false;
        }
        RecetaDet other = (RecetaDet) object;
        if ((this.idRecetaDet == null && other.idRecetaDet != null) || (this.idRecetaDet != null && !this.idRecetaDet.equals(other.idRecetaDet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entidad.RecetaDet[ idRecetaDet=" + idRecetaDet + " ]";
    }
    
}
