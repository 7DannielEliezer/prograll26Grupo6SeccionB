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
@Table(name = "Evaluaciones", catalog = "ReclutadoraDB", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Evaluaciones.findAll", query = "SELECT e FROM Evaluaciones e")})
public class Evaluaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EvaluacionID", nullable = false)
    private Integer evaluacionID;
    @Basic(optional = false)
    @Column(name = "TipoEvaluacion", nullable = false, length = 50)
    private String tipoEvaluacion;
    @Basic(optional = false)
    @Column(name = "FechaEvaluacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEvaluacion;
    @Column(name = "Resultado", length = 50)
    private String resultado;
    @Column(name = "Comentarios", length = 2147483647)
    private String comentarios;
    @JoinColumn(name = "PostulacionID", referencedColumnName = "PostulacionID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Postulaciones postulacionID;

    public Evaluaciones() {
    }

    public Evaluaciones(Integer evaluacionID) {
        this.evaluacionID = evaluacionID;
    }

    public Evaluaciones(Integer evaluacionID, String tipoEvaluacion, Date fechaEvaluacion) {
        this.evaluacionID = evaluacionID;
        this.tipoEvaluacion = tipoEvaluacion;
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public Integer getEvaluacionID() {
        return evaluacionID;
    }

    public void setEvaluacionID(Integer evaluacionID) {
        this.evaluacionID = evaluacionID;
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

    public Postulaciones getPostulacionID() {
        return postulacionID;
    }

    public void setPostulacionID(Postulaciones postulacionID) {
        this.postulacionID = postulacionID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evaluacionID != null ? evaluacionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluaciones)) {
            return false;
        }
        Evaluaciones other = (Evaluaciones) object;
        if ((this.evaluacionID == null && other.evaluacionID != null) || (this.evaluacionID != null && !this.evaluacionID.equals(other.evaluacionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "paquete.bd.reclutadora.Evaluaciones[ evaluacionID=" + evaluacionID + " ]";
    }
    
}
