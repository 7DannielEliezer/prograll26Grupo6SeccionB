/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.db;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "Empresas", catalog = "Reclutadora", schema = "dbo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nit"})})
@NamedQueries({
    @NamedQuery(name = "Empresas.findAll", query = "SELECT e FROM Empresas e")})
public class Empresas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empresa", nullable = false)
    private Integer idEmpresa;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "nit", nullable = false, length = 20)
    private String nit;
    @Column(name = "direccion", length = 250)
    private String direccion;
    @Column(name = "telefono", length = 30)
    private String telefono;
    @Column(name = "email", length = 150)
    private String email;
    @Column(name = "contacto", length = 150)
    private String contacto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa", fetch = FetchType.LAZY)
    private Collection<Vacantes> vacantesCollection;

    public Empresas() {
    }

    public Empresas(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Empresas(Integer idEmpresa, String nombre, String nit) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.nit = nit;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Collection<Vacantes> getVacantesCollection() {
        return vacantesCollection;
    }

    public void setVacantesCollection(Collection<Vacantes> vacantesCollection) {
        this.vacantesCollection = vacantesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresa != null ? idEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresas)) {
            return false;
        }
        Empresas other = (Empresas) object;
        if ((this.idEmpresa == null && other.idEmpresa != null) || (this.idEmpresa != null && !this.idEmpresa.equals(other.idEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.db.Empresas[ idEmpresa=" + idEmpresa + " ]";
    }
    
}
