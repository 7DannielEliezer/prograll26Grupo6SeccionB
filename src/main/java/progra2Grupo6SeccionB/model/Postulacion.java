/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progra2Grupo6SeccionB.model;

/**
 *
 * @author PGutierrez
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Postulacion")
public class Postulacion extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdPostulacion")
    private Integer idPostulacion;

    @Basic(optional = false)
    @Column(name = "EstadoCandidato")
    private String estadoCandidato;

    @Basic(optional = false)
    @Column(name = "FechaPostulacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPostulacion;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idPostulacion", fetch = FetchType.LAZY)
    private Contratacion contratacion;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPostulacion", fetch = FetchType.LAZY)
    private List<Evaluacion> evaluacionList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPostulacion", fetch = FetchType.LAZY)
    private List<BitacoraPostulacion> bitacoraPostulacionList;

    @JoinColumn(name = "IdCandidato", referencedColumnName = "IdCandidato")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Candidato idCandidato;

    @JoinColumn(name = "IdVacante", referencedColumnName = "IdVacante")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Vacante idVacante;

    public Postulacion() {
    }

    public Postulacion(Integer idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public Postulacion(Integer idPostulacion, String estadoCandidato, Date fechaPostulacion) {
        this.idPostulacion = idPostulacion;
        this.estadoCandidato = estadoCandidato;
        this.fechaPostulacion = fechaPostulacion;
    }

    public Integer getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(Integer idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public String getEstadoCandidato() {
        return estadoCandidato;
    }

    public void setEstadoCandidato(String estadoCandidato) {
        this.estadoCandidato = estadoCandidato;
    }

    public Date getFechaPostulacion() {
        return fechaPostulacion;
    }

    public void setFechaPostulacion(Date fechaPostulacion) {
        this.fechaPostulacion = fechaPostulacion;
    }

    public Contratacion getContratacion() {
        return contratacion;
    }

    public void setContratacion(Contratacion contratacion) {
        this.contratacion = contratacion;
    }

    public List<Evaluacion> getEvaluacionList() {
        return evaluacionList;
    }

    public void setEvaluacionList(List<Evaluacion> evaluacionList) {
        this.evaluacionList = evaluacionList;
    }

    public List<BitacoraPostulacion> getBitacoraPostulacionList() {
        return bitacoraPostulacionList;
    }

    public void setBitacoraPostulacionList(List<BitacoraPostulacion> bitacoraPostulacionList) {
        this.bitacoraPostulacionList = bitacoraPostulacionList;
    }

    public Candidato getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(Candidato idCandidato) {
        this.idCandidato = idCandidato;
    }

    public Vacante getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Vacante idVacante) {
        this.idVacante = idVacante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPostulacion != null ? idPostulacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Postulacion)) {
            return false;
        }
        Postulacion other = (Postulacion) object;
        if ((this.idPostulacion == null && other.idPostulacion != null) || (this.idPostulacion != null && !this.idPostulacion.equals(other.idPostulacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Postulacion #" + idPostulacion;
    }
}