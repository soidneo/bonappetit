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
@Table(name = "rol_software", catalog = "control", schema = "public")
@NamedQueries({
    @NamedQuery(name = "RolSoftware.findAll", query = "SELECT r FROM RolSoftware r"),
    @NamedQuery(name = "RolSoftware.findById", query = "SELECT r FROM RolSoftware r WHERE r.id = :id"),
    @NamedQuery(name = "RolSoftware.findByDescripcion", query = "SELECT r FROM RolSoftware r WHERE r.descripcion = :descripcion")})
public class RolSoftware implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="ROL_SOFT_ID_GENERATOR", sequenceName="rol_software_id_seq_1",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROL_SOFT_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion", nullable = false, length = 2147483647)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol", fetch = FetchType.LAZY)
    private List<RolSoftMenu> rolSoftMenuList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRol", fetch = FetchType.LAZY)
    private List<UsuarioRolSoftware> usuarioRolSoftwareList;

    public RolSoftware() {
    }

    public RolSoftware(Integer id) {
        this.id = id;
    }

    public RolSoftware(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<RolSoftMenu> getRolSoftMenuList() {
        return rolSoftMenuList;
    }

    public void setRolSoftMenuList(List<RolSoftMenu> rolSoftMenuList) {
        this.rolSoftMenuList = rolSoftMenuList;
    }

    public List<UsuarioRolSoftware> getUsuarioRolSoftwareList() {
        return usuarioRolSoftwareList;
    }

    public void setUsuarioRolSoftwareList(List<UsuarioRolSoftware> usuarioRolSoftwareList) {
        this.usuarioRolSoftwareList = usuarioRolSoftwareList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolSoftware)) {
            return false;
        }
        RolSoftware other = (RolSoftware) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entidad.RolSoftware[ id=" + id + " ]";
    }
    
}
