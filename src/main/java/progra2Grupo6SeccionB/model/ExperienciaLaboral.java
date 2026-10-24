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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ExperienciaLaboral")
public class ExperienciaLaboral extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdExperiencia")
    private Integer idExperiencia;

    @Basic(optional = false)
    @Column(name = "EmpresaAnterior")
    private String empresaAnterior;

    @Basic(optional = false)
    @Column(name = "PuestoOcupado")
    private String puestoOcupado;

    @Lob
    @Column(name = "FuncionesRealizadas")
    private String funcionesRealizadas;

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

    public ExperienciaLaboral() {
    }

    public ExperienciaLaboral(Integer idExperiencia) {
        this.idExperiencia = idExperiencia;
    }

    public ExperienciaLaboral(Integer idExperiencia, String empresaAnterior, String puestoOcupado, Date fechaInicio) {
        this.idExperiencia = idExperiencia;
        this.empresaAnterior = empresaAnterior;
        this.puestoOcupado = puestoOcupado;
        this.fechaInicio = fechaInicio;
    }

    public Integer getIdExperiencia() {
        return idExperiencia;
    }

    public void setIdExperiencia(Integer idExperiencia) {
        this.idExperiencia = idExperiencia;
    }

    public String getEmpresaAnterior() {
        return empresaAnterior;
    }

    public void setEmpresaAnterior(String empresaAnterior) {
        this.empresaAnterior = empresaAnterior;
    }

    public String getPuestoOcupado() {
        return puestoOcupado;
    }

    public void setPuestoOcupado(String puestoOcupado) {
        this.puestoOcupado = puestoOcupado;
    }

    public String getFuncionesRealizadas() {
        return funcionesRealizadas;
    }

    public void setFuncionesRealizadas(String funcionesRealizadas) {
        this.funcionesRealizadas = funcionesRealizadas;
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
        hash += (idExperiencia != null ? idExperiencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ExperienciaLaboral)) {
            return false;
        }
        ExperienciaLaboral other = (ExperienciaLaboral) object;
        if ((this.idExperiencia == null && other.idExperiencia != null) || (this.idExperiencia != null && !this.idExperiencia.equals(other.idExperiencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Experiencia #" + idExperiencia;
    }
}
