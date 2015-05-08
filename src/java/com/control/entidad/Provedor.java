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
@Table(name = "provedor", catalog = "control", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Provedor.findAll", query = "SELECT p FROM Provedor p"),
    @NamedQuery(name = "Provedor.findByIdProvedor", query = "SELECT p FROM Provedor p WHERE p.idProvedor = :idProvedor"),
    @NamedQuery(name = "Provedor.findByNombre", query = "SELECT p FROM Provedor p WHERE p.nombre = :nombre")})
public class Provedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="PROVEDOR_ID_GENERATOR", sequenceName="provedor_id_provedor_seq",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROVEDOR_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "id_provedor", nullable = false)
    private Integer idProvedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre", nullable = false, length = 2147483647)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provedor", fetch = FetchType.LAZY)
    private List<Ingrediente> ingredienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProvedor", fetch = FetchType.LAZY)
    private List<Producto> productoList;

    public Provedor() {
    }

    public Provedor(Integer idProvedor) {
        this.idProvedor = idProvedor;
    }

    public Provedor(Integer idProvedor, String nombre) {
        this.idProvedor = idProvedor;
        this.nombre = nombre;
    }

    public Integer getIdProvedor() {
        return idProvedor;
    }

    public void setIdProvedor(Integer idProvedor) {
        this.idProvedor = idProvedor;
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

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProvedor != null ? idProvedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provedor)) {
            return false;
        }
        Provedor other = (Provedor) object;
        if ((this.idProvedor == null && other.idProvedor != null) || (this.idProvedor != null && !this.idProvedor.equals(other.idProvedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entidad.Provedor[ idProvedor=" + idProvedor + " ]";
    }
    
}
