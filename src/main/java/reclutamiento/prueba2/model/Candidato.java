/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reclutamiento.prueba2.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author PGutierrez
 */
@Entity
@Table(name = "Candidato")
@NamedQueries({
    @NamedQuery(name = "Candidato.findAll", query = "SELECT c FROM Candidato c"),
    @NamedQuery(name = "Candidato.findByIdCandidato", query = "SELECT c FROM Candidato c WHERE c.idCandidato = :idCandidato"),
    @NamedQuery(name = "Candidato.findByNombres", query = "SELECT c FROM Candidato c WHERE c.nombres = :nombres"),
    @NamedQuery(name = "Candidato.findByApellidos", query = "SELECT c FROM Candidato c WHERE c.apellidos = :apellidos"),
    @NamedQuery(name = "Candidato.findByDpi", query = "SELECT c FROM Candidato c WHERE c.dpi = :dpi"),
    @NamedQuery(name = "Candidato.findByCorreo", query = "SELECT c FROM Candidato c WHERE c.correo = :correo"),
    @NamedQuery(name = "Candidato.findByTelefono", query = "SELECT c FROM Candidato c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Candidato.findByEstado", query = "SELECT c FROM Candidato c WHERE c.estado = :estado"),
    @NamedQuery(name = "Candidato.findByFechaRegistro", query = "SELECT c FROM Candidato c WHERE c.fechaRegistro = :fechaRegistro")})
public class Candidato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdCandidato")
    private Integer idCandidato;
    @Basic(optional = false)
    @Column(name = "Nombres")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "Apellidos")
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "DPI")
    private String dpi;
    @Basic(optional = false)
    @Column(name = "Correo")
    private String correo;
    @Column(name = "Telefono")
    private String telefono;
    @Column(name = "Estado")
    private Boolean estado;
    @Column(name = "FechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCandidato", fetch = FetchType.LAZY)
    private List<Postulacion> postulacionList;

    public Candidato() {
    }

    public Candidato(Integer idCandidato) {
        this.idCandidato = idCandidato;
    }

    public Candidato(Integer idCandidato, String nombres, String apellidos, String dpi, String correo) {
        this.idCandidato = idCandidato;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dpi = dpi;
        this.correo = correo;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Postulacion> getPostulacionList() {
        return postulacionList;
    }

    public void setPostulacionList(List<Postulacion> postulacionList) {
        this.postulacionList = postulacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCandidato != null ? idCandidato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Candidato)) {
            return false;
        }
        Candidato other = (Candidato) object;
        if ((this.idCandidato == null && other.idCandidato != null) || (this.idCandidato != null && !this.idCandidato.equals(other.idCandidato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return (nombres != null ? nombres : "") + " " + (apellidos != null ? apellidos : "");
    }
}
