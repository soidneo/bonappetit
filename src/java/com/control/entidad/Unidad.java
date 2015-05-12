/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Cacheable(false)
@Table(name = "unidad", catalog = "control", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Unidad.findAll", query = "SELECT u FROM Unidad u"),
    @NamedQuery(name = "Unidad.findByIdUnidad", query = "SELECT u FROM Unidad u WHERE u.idUnidad = :idUnidad"),
    @NamedQuery(name = "Unidad.findByNombre", query = "SELECT u FROM Unidad u WHERE u.nombre = :nombre")})
public class Unidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="UNIDAD_ID_GENERATOR", sequenceName="unidad_id_unidad_seq",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UNIDAD_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "id_unidad", nullable = false)
    private Integer idUnidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre", nullable = false, length = 2147483647)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidad", fetch = FetchType.LAZY)
    private List<Ingrediente> ingredienteList;

    public Unidad() {
    }

    public Unidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public Unidad(Integer idUnidad, String nombre) {
        this.idUnidad = idUnidad;
        this.nombre = nombre;
    }

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Ingrediente> getIngredienteList() {
        return ingredienteList;
    }

    public void setIngredienteList(List<Ingrediente> ingredienteList) {
        this.ingredienteList = ingredienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidad != null ? idUnidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unidad)) {
            return false;
        }
        Unidad other = (Unidad) object;
        if ((this.idUnidad == null && other.idUnidad != null) || (this.idUnidad != null && !this.idUnidad.equals(other.idUnidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
    
}
