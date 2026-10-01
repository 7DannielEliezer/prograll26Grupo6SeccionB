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
@Table(name = "Candidatos", catalog = "ReclutadoraDB", schema = "dbo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"DPI"})})
@NamedQueries({
    @NamedQuery(name = "Candidatos.findAll", query = "SELECT c FROM Candidatos c")})
public class Candidatos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CandidatoID", nullable = false)
    private Integer candidatoID;
    @Basic(optional = false)
    @Column(name = "Nombres", nullable = false, length = 100)
    private String nombres;
    @Basic(optional = false)
    @Column(name = "Apellidos", nullable = false, length = 100)
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "DPI", nullable = false, length = 20)
    private String dpi;
    @Basic(optional = false)
    @Column(name = "Correo", nullable = false, length = 100)
    private String correo;
    @Column(name = "Telefono", length = 20)
    private String telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidatoID", fetch = FetchType.LAZY)
    private List<HistorialCandidato> historialCandidatoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidatoID", fetch = FetchType.LAZY)
    private List<Postulaciones> postulacionesList;

    public Candidatos() {
    }

    public Candidatos(Integer candidatoID) {
        this.candidatoID = candidatoID;
    }

    public Candidatos(Integer candidatoID, String nombres, String apellidos, String dpi, String correo) {
        this.candidatoID = candidatoID;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dpi = dpi;
        this.correo = correo;
    }

    public Integer getCandidatoID() {
        return candidatoID;
    }

    public void setCandidatoID(Integer candidatoID) {
        this.candidatoID = candidatoID;
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

    public List<HistorialCandidato> getHistorialCandidatoList() {
        return historialCandidatoList;
    }

    public void setHistorialCandidatoList(List<HistorialCandidato> historialCandidatoList) {
        this.historialCandidatoList = historialCandidatoList;
    }

    public List<Postulaciones> getPostulacionesList() {
        return postulacionesList;
    }

    public void setPostulacionesList(List<Postulaciones> postulacionesList) {
        this.postulacionesList = postulacionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (candidatoID != null ? candidatoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Candidatos)) {
            return false;
        }
        Candidatos other = (Candidatos) object;
        if ((this.candidatoID == null && other.candidatoID != null) || (this.candidatoID != null && !this.candidatoID.equals(other.candidatoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "paquete.bd.reclutadora.Candidatos[ candidatoID=" + candidatoID + " ]";
    }
    
}
