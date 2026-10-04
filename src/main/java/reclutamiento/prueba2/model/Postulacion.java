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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author PGutierrez
 */
@Entity
@Table(name = "Postulacion")
@NamedQueries({
    @NamedQuery(name = "Postulacion.findAll", query = "SELECT p FROM Postulacion p"),
    @NamedQuery(name = "Postulacion.findByIdPostulacion", query = "SELECT p FROM Postulacion p WHERE p.idPostulacion = :idPostulacion"),
    @NamedQuery(name = "Postulacion.findByFechaPostulacion", query = "SELECT p FROM Postulacion p WHERE p.fechaPostulacion = :fechaPostulacion"),
    @NamedQuery(name = "Postulacion.findByEstadoCandidato", query = "SELECT p FROM Postulacion p WHERE p.estadoCandidato = :estadoCandidato")})
public class Postulacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdPostulacion")
    private Integer idPostulacion;
    @Column(name = "FechaPostulacion")
    @Temporal(TemporalType.DATE)
    private Date fechaPostulacion;
    @Column(name = "EstadoCandidato")
    private String estadoCandidato;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idPostulacion", fetch = FetchType.LAZY)
    private Contratacion contratacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPostulacion", fetch = FetchType.LAZY)
    private List<Evaluacion> evaluacionList;
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

    public Integer getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(Integer idPostulacion) {
        this.idPostulacion = idPostulacion;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
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
        StringBuilder sb = new StringBuilder();

        // Agregamos el ID de la postulación
        if (this.idPostulacion != null) {
            sb.append("#").append(this.idPostulacion).append(" - ");
        }

        // Agregamos el nombre completo del candidato si existe
        if (this.idCandidato != null) {
            sb.append(this.idCandidato.getNombres()).append(" ").append(this.idCandidato.getApellidos());
        } else {
            sb.append("Candidato N/A");
        }

        // Agregamos el título de la vacante entre paréntesis si existe
        if (this.idVacante != null && this.idVacante.getTituloVacante() != null) {
            sb.append(" (").append(this.idVacante.getTituloVacante()).append(")");
        }

        return sb.toString();
    }
}
