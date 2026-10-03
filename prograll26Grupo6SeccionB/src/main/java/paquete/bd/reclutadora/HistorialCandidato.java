/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete.bd.reclutadora;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "HistorialCandidato", catalog = "ReclutadoraDB", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "HistorialCandidato.findAll", query = "SELECT h FROM HistorialCandidato h")})
public class HistorialCandidato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "HistorialID", nullable = false)
    private Integer historialID;
    @Basic(optional = false)
    @Column(name = "TipoHistorial", nullable = false, length = 20)
    private String tipoHistorial;
    @Basic(optional = false)
    @Column(name = "TituloInstitucion", nullable = false, length = 150)
    private String tituloInstitucion;
    @Column(name = "Descripcion", length = 2147483647)
    private String descripcion;
    @Column(name = "FechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "FechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @JoinColumn(name = "CandidatoID", referencedColumnName = "CandidatoID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Candidatos candidatoID;

    public HistorialCandidato() {
    }

    public HistorialCandidato(Integer historialID) {
        this.historialID = historialID;
    }

    public HistorialCandidato(Integer historialID, String tipoHistorial, String tituloInstitucion) {
        this.historialID = historialID;
        this.tipoHistorial = tipoHistorial;
        this.tituloInstitucion = tituloInstitucion;
    }

    public Integer getHistorialID() {
        return historialID;
    }

    public void setHistorialID(Integer historialID) {
        this.historialID = historialID;
    }

    public String getTipoHistorial() {
        return tipoHistorial;
    }

    public void setTipoHistorial(String tipoHistorial) {
        this.tipoHistorial = tipoHistorial;
    }

    public String getTituloInstitucion() {
        return tituloInstitucion;
    }

    public void setTituloInstitucion(String tituloInstitucion) {
        this.tituloInstitucion = tituloInstitucion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Candidatos getCandidatoID() {
        return candidatoID;
    }

    public void setCandidatoID(Candidatos candidatoID) {
        this.candidatoID = candidatoID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (historialID != null ? historialID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialCandidato)) {
            return false;
        }
        HistorialCandidato other = (HistorialCandidato) object;
        if ((this.historialID == null && other.historialID != null) || (this.historialID != null && !this.historialID.equals(other.historialID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "paquete.bd.reclutadora.HistorialCandidato[ historialID=" + historialID + " ]";
    }
    
}
