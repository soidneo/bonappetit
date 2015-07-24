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
@Table(name = "modo_pago", catalog = "control", schema = "public")
@NamedQueries({
    @NamedQuery(name = "ModoPago.findAll", query = "SELECT m FROM ModoPago m"),
    @NamedQuery(name = "ModoPago.findByIdModoPago", query = "SELECT m FROM ModoPago m WHERE m.idModoPago = :idModoPago"),
    @NamedQuery(name = "ModoPago.findByNombre", query = "SELECT m FROM ModoPago m WHERE m.nombre = :nombre")})
public class ModoPago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="MODO_ID_GENERATOR", sequenceName="modo_pago_id_modo_pago_seq",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MODO_ID_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_modo_pago", nullable = false)
    private Integer idModoPago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre", nullable = false, length = 2147483647)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modoPago", fetch = FetchType.LAZY)
    private List<Ventas> ventasList;

    public ModoPago() {
    }

    public ModoPago(Integer idModoPago) {
        this.idModoPago = idModoPago;
    }

    public ModoPago(Integer idModoPago, String nombre) {
        this.idModoPago = idModoPago;
        this.nombre = nombre;
    }

    public Integer getIdModoPago() {
        return idModoPago;
    }

    public void setIdModoPago(Integer idModoPago) {
        this.idModoPago = idModoPago;
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
        hash += (idModoPago != null ? idModoPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModoPago)) {
            return false;
        }
        ModoPago other = (ModoPago) object;
        if ((this.idModoPago == null && other.idModoPago != null) || (this.idModoPago != null && !this.idModoPago.equals(other.idModoPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entidad.ModoPago[ idModoPago=" + idModoPago + " ]";
    }
    
}
