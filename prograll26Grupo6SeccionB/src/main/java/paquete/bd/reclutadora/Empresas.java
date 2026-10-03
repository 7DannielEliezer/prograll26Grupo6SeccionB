/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete.bd.reclutadora;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "Empresas", catalog = "ReclutadoraDB", schema = "dbo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NIT"})})
@NamedQueries({
    @NamedQuery(name = "Empresas.findAll", query = "SELECT e FROM Empresas e")})
public class Empresas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EmpresaID", nullable = false)
    private Integer empresaID;
    @Basic(optional = false)
    @Column(name = "NombreEmpresa", nullable = false, length = 100)
    private String nombreEmpresa;
    @Basic(optional = false)
    @Column(name = "NIT", nullable = false, length = 20)
    private String nit;
    @Column(name = "Telefono", length = 20)
    private String telefono;
    @Column(name = "Correo", length = 100)
    private String correo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresaID", fetch = FetchType.LAZY)
    private List<Vacantes> vacantesList;

    public Empresas() {
    }

    public Empresas(Integer empresaID) {
        this.empresaID = empresaID;
    }

    public Empresas(Integer empresaID, String nombreEmpresa, String nit) {
        this.empresaID = empresaID;
        this.nombreEmpresa = nombreEmpresa;
        this.nit = nit;
    }

    public Integer getEmpresaID() {
        return empresaID;
    }

    public void setEmpresaID(Integer empresaID) {
        this.empresaID = empresaID;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Vacantes> getVacantesList() {
        return vacantesList;
    }

    public void setVacantesList(List<Vacantes> vacantesList) {
        this.vacantesList = vacantesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empresaID != null ? empresaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresas)) {
            return false;
        }
        Empresas other = (Empresas) object;
        if ((this.empresaID == null && other.empresaID != null) || (this.empresaID != null && !this.empresaID.equals(other.empresaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "paquete.bd.reclutadora.Empresas[ empresaID=" + empresaID + " ]";
    }
    
}
