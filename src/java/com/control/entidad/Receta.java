/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "receta", catalog = "control", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Receta.findAll", query = "SELECT r FROM Receta r"),
    @NamedQuery(name = "Receta.findByIdReceta", query = "SELECT r FROM Receta r WHERE r.idReceta = :idReceta"),
    @NamedQuery(name = "Receta.findByNombre", query = "SELECT r FROM Receta r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Receta.findByDescripcion", query = "SELECT r FROM Receta r WHERE r.descripcion = :descripcion")})
public class Receta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_receta", nullable = false)
    private Integer idReceta;
    @Size(max = 2147483647)
    @Column(name = "nombre", length = 2147483647)
    private String nombre;
    @Size(max = 2147483647)
    @Column(name = "descripcion", length = 2147483647)
    private String descripcion;
    @OneToMany(mappedBy = "idReceta", fetch = FetchType.LAZY)
    private List<RecetaDet> recetaDetList;

    public Receta() {
    }

    public Receta(Integer idReceta) {
        this.idReceta = idReceta;
    }

    public Integer getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Integer idReceta) {
        this.idReceta = idReceta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<RecetaDet> getRecetaDetList() {
        return recetaDetList;
    }

    public void setRecetaDetList(List<RecetaDet> recetaDetList) {
        this.recetaDetList = recetaDetList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReceta != null ? idReceta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receta)) {
            return false;
        }
        Receta other = (Receta) object;
        if ((this.idReceta == null && other.idReceta != null) || (this.idReceta != null && !this.idReceta.equals(other.idReceta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entidad.Receta[ idReceta=" + idReceta + " ]";
    }
    
}
