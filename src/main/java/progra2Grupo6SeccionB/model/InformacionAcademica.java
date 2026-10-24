/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progra2Grupo6SeccionB.model;

/**
 *
 * @author PGutierrez
 */

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "InformacionAcademica")
public class InformacionAcademica extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdAcademico")
    private Integer idAcademico;

    @Basic(optional = false)
    @Column(name = "NivelEstudio")
    private String nivelEstudio;

    @Basic(optional = false)
    @Column(name = "Institucion")
    private String institucion;

    @Basic(optional = false)
    @Column(name = "TituloObtenido")
    private String tituloObtenido;

    @Basic(optional = false)
    @Column(name = "FechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "FechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @JoinColumn(name = "IdCandidato", referencedColumnName = "IdCandidato")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Candidato idCandidato;

    public InformacionAcademica() {
    }

    public InformacionAcademica(Integer idAcademico) {
        this.idAcademico = idAcademico;
    }

    public InformacionAcademica(Integer idAcademico, String nivelEstudio, String institucion, String tituloObtenido, Date fechaInicio) {
        this.idAcademico = idAcademico;
        this.nivelEstudio = nivelEstudio;
        this.institucion = institucion;
        this.tituloObtenido = tituloObtenido;
        this.fechaInicio = fechaInicio;
    }

    public Integer getIdAcademico() {
        return idAcademico;
    }

    public void setIdAcademico(Integer idAcademico) {
        this.idAcademico = idAcademico;
    }

    public String getNivelEstudio() {
        return nivelEstudio;
    }

    public void setNivelEstudio(String nivelEstudio) {
        this.nivelEstudio = nivelEstudio;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getTituloObtenido() {
        return tituloObtenido;
    }

    public void setTituloObtenido(String tituloObtenido) {
        this.tituloObtenido = tituloObtenido;
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

    public Candidato getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(Candidato idCandidato) {
        this.idCandidato = idCandidato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAcademico != null ? idAcademico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof InformacionAcademica)) {
            return false;
        }
        InformacionAcademica other = (InformacionAcademica) object;
        if ((this.idAcademico == null && other.idAcademico != null) || (this.idAcademico != null && !this.idAcademico.equals(other.idAcademico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Academico #" + idAcademico;
    }
}