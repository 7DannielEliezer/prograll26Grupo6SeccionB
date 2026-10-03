/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete.bd.reclutadora;

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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "Postulaciones", catalog = "ReclutadoraDB", schema = "dbo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CandidatoID", "VacanteID"}),
    @UniqueConstraint(columnNames = {"EmpresaID", "PostulacionID"})})
@NamedQueries({
    @NamedQuery(name = "Postulaciones.findAll", query = "SELECT p FROM Postulaciones p")})
public class Postulaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PostulacionID", nullable = false)
    private Integer postulacionID;
    @Basic(optional = false)
    @Column(name = "FechaPostulacion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaPostulacion;
    @Basic(optional = false)
    @Column(name = "EstadoCandidato", nullable = false, length = 30)
    private String estadoCandidato;
    @JoinColumn(name = "CandidatoID", referencedColumnName = "CandidatoID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Candidatos candidatoID;
    @JoinColumns({
        @JoinColumn(name = "EmpresaID", referencedColumnName = "EmpresaID", nullable = false),
        @JoinColumn(name = "VacanteID", referencedColumnName = "VacanteID", nullable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Vacantes vacantes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postulaciones", fetch = FetchType.LAZY)
    private List<Contrataciones> contratacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postulacionID", fetch = FetchType.LAZY)
    private List<Evaluaciones> evaluacionesList;

    public Postulaciones() {
    }

    public Postulaciones(Integer postulacionID) {
        this.postulacionID = postulacionID;
    }

    public Postulaciones(Integer postulacionID, Date fechaPostulacion, String estadoCandidato) {
        this.postulacionID = postulacionID;
        this.fechaPostulacion = fechaPostulacion;
        this.estadoCandidato = estadoCandidato;
    }

    public Integer getPostulacionID() {
        return postulacionID;
    }

    public void setPostulacionID(Integer postulacionID) {
        this.postulacionID = postulacionID;
    }

    public Date getFechaPostulacion() {
        return fechaPostulacion;
    }

    public void setFechaPostulacion(Date fechaPostulacion) {
        this.fechaPostulacion = fechaPostulacion;
    }

    public String getEstadoCandidato() {
        return estadoCandidato;
    }

    public void setEstadoCandidato(String estadoCandidato) {
        this.estadoCandidato = estadoCandidato;
    }

    public Candidatos getCandidatoID() {
        return candidatoID;
    }

    public void setCandidatoID(Candidatos candidatoID) {
        this.candidatoID = candidatoID;
    }

    public Vacantes getVacantes() {
        return vacantes;
    }

    public void setVacantes(Vacantes vacantes) {
        this.vacantes = vacantes;
    }

    public List<Contrataciones> getContratacionesList() {
        return contratacionesList;
    }

    public void setContratacionesList(List<Contrataciones> contratacionesList) {
        this.contratacionesList = contratacionesList;
    }

    public List<Evaluaciones> getEvaluacionesList() {
        return evaluacionesList;
    }

    public void setEvaluacionesList(List<Evaluaciones> evaluacionesList) {
        this.evaluacionesList = evaluacionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postulacionID != null ? postulacionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Postulaciones)) {
            return false;
        }
        Postulaciones other = (Postulaciones) object;
        if ((this.postulacionID == null && other.postulacionID != null) || (this.postulacionID != null && !this.postulacionID.equals(other.postulacionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "paquete.bd.reclutadora.Postulaciones[ postulacionID=" + postulacionID + " ]";
    }
    
}
