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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "usuario_rol_software", catalog = "control", schema = "public")
@NamedQueries({
    @NamedQuery(name = "UsuarioRolSoftware.findAll", query = "SELECT u FROM UsuarioRolSoftware u"),
    @NamedQuery(name = "UsuarioRolSoftware.findByIdUsuarioRolSoftware", query = "SELECT u FROM UsuarioRolSoftware u WHERE u.idUsuarioRolSoftware = :idUsuarioRolSoftware")})
public class UsuarioRolSoftware implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="USUARIO_ROL_SOFTWARE_ID_GENERATOR", sequenceName="usuario_rol_software_id_usuario_rol_software_seq",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIO_ROL_SOFTWARE_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "id_usuario_rol_software", nullable = false)
    private Integer idUsuarioRolSoftware;
    @JoinColumn(name = "usuario", referencedColumnName = "usuario", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;
    @JoinColumn(name = "id_rol", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RolSoftware idRol;

    public UsuarioRolSoftware() {
    }

    public UsuarioRolSoftware(Integer idUsuarioRolSoftware) {
        this.idUsuarioRolSoftware = idUsuarioRolSoftware;
    }

    public Integer getIdUsuarioRolSoftware() {
        return idUsuarioRolSoftware;
    }

    public void setIdUsuarioRolSoftware(Integer idUsuarioRolSoftware) {
        this.idUsuarioRolSoftware = idUsuarioRolSoftware;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public RolSoftware getIdRol() {
        return idRol;
    }

    public void setIdRol(RolSoftware idRol) {
        this.idRol = idRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioRolSoftware != null ? idUsuarioRolSoftware.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRolSoftware)) {
            return false;
        }
        UsuarioRolSoftware other = (UsuarioRolSoftware) object;
        if ((this.idUsuarioRolSoftware == null && other.idUsuarioRolSoftware != null) || (this.idUsuarioRolSoftware != null && !this.idUsuarioRolSoftware.equals(other.idUsuarioRolSoftware))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entidad.UsuarioRolSoftware[ idUsuarioRolSoftware=" + idUsuarioRolSoftware + " ]";
    }
    
}
