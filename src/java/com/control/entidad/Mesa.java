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
@Table(name = "mesa", catalog = "control", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Mesa.findAll", query = "SELECT m FROM Mesa m"),
    @NamedQuery(name = "Mesa.findByIdMesa", query = "SELECT m FROM Mesa m WHERE m.idMesa = :idMesa"),
    @NamedQuery(name = "Mesa.findByNombre", query = "SELECT m FROM Mesa m WHERE m.nombre = :nombre")})
public class Mesa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="MESA_ID_GENERATOR", sequenceName="mesa_id_mesa_seq",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MESA_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "id_mesa", nullable = false)
    private Integer idMesa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre", nullable = false, length = 2147483647)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mesa", fetch = FetchType.LAZY)
    private List<Ventas> ventasList;

    public Mesa() {
    }

    public Mesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public Mesa(Integer idMesa, String nombre) {
        this.idMesa = idMesa;
        this.nombre = nombre;
    }

    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Ventas> getVentasList() {
        return ventasList;
    }

    public void setVentasList(List<Ventas> ventasList) {
        this.ventasList = ventasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMesa != null ? idMesa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mesa)) {
            return false;
        }
        Mesa other = (Mesa) object;
        if ((this.idMesa == null && other.idMesa != null) || (this.idMesa != null && !this.idMesa.equals(other.idMesa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entidad.Mesa[ idMesa=" + idMesa + " ]";
    }
    
}
