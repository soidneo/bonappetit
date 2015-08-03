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
@Table(name = "rol_soft_menu", catalog = "control", schema = "public")
@NamedQueries({
    @NamedQuery(name = "RolSoftMenu.findAll", query = "SELECT r FROM RolSoftMenu r"),
    @NamedQuery(name = "RolSoftMenu.findByIdRolSoftMenu", query = "SELECT r FROM RolSoftMenu r WHERE r.idRolSoftMenu = :idRolSoftMenu")})
public class RolSoftMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rol_soft_menu", nullable = false)
    private Integer idRolSoftMenu;
    @JoinColumn(name = "rol", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RolSoftware rol;
    @JoinColumn(name = "menu", referencedColumnName = "id_menu", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Menu menu;

    public RolSoftMenu() {
    }

    public RolSoftMenu(Integer idRolSoftMenu) {
        this.idRolSoftMenu = idRolSoftMenu;
    }

    public Integer getIdRolSoftMenu() {
        return idRolSoftMenu;
    }

    public void setIdRolSoftMenu(Integer idRolSoftMenu) {
        this.idRolSoftMenu = idRolSoftMenu;
    }

    public RolSoftware getRol() {
        return rol;
    }

    public void setRol(RolSoftware rol) {
        this.rol = rol;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRolSoftMenu != null ? idRolSoftMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolSoftMenu)) {
            return false;
        }
        RolSoftMenu other = (RolSoftMenu) object;
        if ((this.idRolSoftMenu == null && other.idRolSoftMenu != null) || (this.idRolSoftMenu != null && !this.idRolSoftMenu.equals(other.idRolSoftMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entidad.RolSoftMenu[ idRolSoftMenu=" + idRolSoftMenu + " ]";
    }
    
}
