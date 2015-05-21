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
@Table(name = "ingrediente", catalog = "control", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Ingrediente.findAll", query = "SELECT i FROM Ingrediente i"),
    @NamedQuery(name = "Ingrediente.findByIdIngrediente", query = "SELECT i FROM Ingrediente i WHERE i.idIngrediente = :idIngrediente"),
    @NamedQuery(name = "Ingrediente.findByNombre", query = "SELECT i FROM Ingrediente i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Ingrediente.findByCosto", query = "SELECT i FROM Ingrediente i WHERE i.costo = :costo")})
public class Ingrediente implements Serializable {
    @OneToMany(mappedBy = "ingredienteInventario", fetch = FetchType.LAZY)
    private List<Inventario> inventarioList;
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="INGREDIENTE_ID_GENERATOR", sequenceName="ingrediente_id_ingrediente_seq",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INGREDIENTE_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "id_ingrediente", nullable = false)
    private Integer idIngrediente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre", nullable = false, length = 2147483647)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo", nullable = false)
    private double costo;
    @JoinColumn(name = "id_unidad", referencedColumnName = "id_unidad", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Unidad idUnidad;
    @JoinColumn(name = "provedor", referencedColumnName = "id_provedor", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Provedor provedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ingrediente", fetch = FetchType.LAZY)
    private List<RecetaDet> recetaDetList;

    public Ingrediente() {
    }

    public Ingrediente(Integer idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public Ingrediente(Integer idIngrediente, String nombre, double costo) {
        this.idIngrediente = idIngrediente;
        this.nombre = nombre;
        this.costo = costo;
    }

    public Integer getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Integer idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Unidad getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Unidad idUnidad) {
        this.idUnidad = idUnidad;
    }

    public Provedor getProvedor() {
        return provedor;
    }

    public void setProvedor(Provedor provedor) {
        this.provedor = provedor;
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
        hash += (idIngrediente != null ? idIngrediente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingrediente)) {
            return false;
        }
        Ingrediente other = (Ingrediente) object;
        if ((this.idIngrediente == null && other.idIngrediente != null) || (this.idIngrediente != null && !this.idIngrediente.equals(other.idIngrediente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entidad.Ingrediente[ idIngrediente=" + idIngrediente + " ]";
    }

    public List<Inventario> getInventarioList() {
        return inventarioList;
    }

    public void setInventarioList(List<Inventario> inventarioList) {
        this.inventarioList = inventarioList;
    }
    
}
