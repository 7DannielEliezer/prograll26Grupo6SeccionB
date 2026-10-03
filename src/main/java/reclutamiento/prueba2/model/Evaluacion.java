/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reclutamiento.prueba2.model;

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
 * @author PGutierrez
 */
@Entity
@Table(name = "Evaluacion")
@NamedQueries({
    @NamedQuery(name = "Evaluacion.findAll", query = "SELECT e FROM Evaluacion e"),
    @NamedQuery(name = "Evaluacion.findByIdEvaluacion", query = "SELECT e FROM Evaluacion e WHERE e.idEvaluacion = :idEvaluacion"),
    @NamedQuery(name = "Evaluacion.findByTipoEvaluacion", query = "SELECT e FROM Evaluacion e WHERE e.tipoEvaluacion = :tipoEvaluacion"),
    @NamedQuery(name = "Evaluacion.findByFechaEvaluacion", query = "SELECT e FROM Evaluacion e WHERE e.fechaEvaluacion = :fechaEvaluacion"),
    @NamedQuery(name = "Evaluacion.findByResultado", query = "SELECT e FROM Evaluacion e WHERE e.resultado = :resultado"),
    @NamedQuery(name = "Evaluacion.findByComentarios", query = "SELECT e FROM Evaluacion e WHERE e.comentarios = :comentarios")})
public class Evaluacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdEvaluacion")
    private Integer idEvaluacion;
    @Basic(optional = false)
    @Column(name = "TipoEvaluacion")
    private String tipoEvaluacion;
    @Basic(optional = false)
    @Column(name = "Estado")
    private Boolean estado;
    @Column(name = "FechaEvaluacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEvaluacion;
    @Column(name = "Resultado")
    private String resultado;
    @Column(name = "Comentarios")
    private String comentarios;
    @JoinColumn(name = "IdPostulacion", referencedColumnName = "IdPostulacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Postulacion idPostulacion;
    @JoinColumn(name = "IdEvaluador", referencedColumnName = "IdUsuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario idEvaluador;

    public Evaluacion() {
    }

    public Evaluacion(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Evaluacion(Integer idEvaluacion, String tipoEvaluacion, Date fechaEvaluacion) {
        this.idEvaluacion = idEvaluacion;
        this.tipoEvaluacion = tipoEvaluacion;
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public Integer getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public String getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    public void setTipoEvaluacion(String tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }

    public Date getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(Date fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Postulacion getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(Postulacion idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public Usuario getIdEvaluador() {
        return idEvaluador;
    }

    public void setIdEvaluador(Usuario idEvaluador) {
        this.idEvaluador = idEvaluador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvaluacion != null ? idEvaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluacion)) {
            return false;
        }
        Evaluacion other = (Evaluacion) object;
        if ((this.idEvaluacion == null && other.idEvaluacion != null) || (this.idEvaluacion != null && !this.idEvaluacion.equals(other.idEvaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tipoEvaluacion != null ? tipoEvaluacion : "";
    }
}
