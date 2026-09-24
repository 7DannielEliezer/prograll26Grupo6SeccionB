/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.db;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "Candidatos", catalog = "Reclutadora", schema = "dbo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"dpi"})})
@NamedQueries({
    @NamedQuery(name = "Candidatos.findAll", query = "SELECT c FROM Candidatos c")})
public class Candidatos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_candidato", nullable = false)
    private Integer idCandidato;
    @Basic(optional = false)
    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;
    @Basic(optional = false)
    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "dpi", nullable = false, length = 20)
    private String dpi;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "telefono", length = 30)
    private String telefono;
    @Column(name = "email", length = 150)
    private String email;
    @Column(name = "direccion", length = 250)
    private String direccion;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    public Candidatos() {
    }

    public Candidatos(Integer idCandidato) {
        this.idCandidato = idCandidato;
    }

    public Candidatos(Integer idCandidato, String nombres, String apellidos, String dpi, String estado) {
        this.idCandidato = idCandidato;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dpi = dpi;
        this.estado = estado;
    }

    public Integer getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(Integer idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCandidato != null ? idCandidato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Candidatos)) {
            return false;
        }
        Candidatos other = (Candidatos) object;
        if ((this.idCandidato == null && other.idCandidato != null) || (this.idCandidato != null && !this.idCandidato.equals(other.idCandidato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.db.Candidatos[ idCandidato=" + idCandidato + " ]";
    }
    
}
