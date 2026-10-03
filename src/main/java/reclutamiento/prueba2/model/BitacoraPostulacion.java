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
@Table(name = "BitacoraPostulacion")
@NamedQueries({
    @NamedQuery(name = "BitacoraPostulacion.findAll", query = "SELECT b FROM BitacoraPostulacion b"),
    @NamedQuery(name = "BitacoraPostulacion.findByIdBitacora", query = "SELECT b FROM BitacoraPostulacion b WHERE b.idBitacora = :idBitacora"),
    @NamedQuery(name = "BitacoraPostulacion.findByEstadoAnterior", query = "SELECT b FROM BitacoraPostulacion b WHERE b.estadoAnterior = :estadoAnterior"),
    @NamedQuery(name = "BitacoraPostulacion.findByEstadoNuevo", query = "SELECT b FROM BitacoraPostulacion b WHERE b.estadoNuevo = :estadoNuevo"),
    @NamedQuery(name = "BitacoraPostulacion.findByObservacion", query = "SELECT b FROM BitacoraPostulacion b WHERE b.observacion = :observacion"),
    @NamedQuery(name = "BitacoraPostulacion.findByFechaCambio", query = "SELECT b FROM BitacoraPostulacion b WHERE b.fechaCambio = :fechaCambio")})
public class BitacoraPostulacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdBitacora")
    private Integer idBitacora;
    @Column(name = "EstadoAnterior")
    private String estadoAnterior;
    @Basic(optional = false)
    @Column(name = "EstadoNuevo")
    private String estadoNuevo;
    @Column(name = "Observacion")
    private String observacion;
    @Column(name = "FechaCambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCambio;
    @JoinColumn(name = "IdPostulacion", referencedColumnName = "IdPostulacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Postulacion idPostulacion;
    @JoinColumn(name = "IdUsuario", referencedColumnName = "IdUsuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario idUsuario;

    public BitacoraPostulacion() {
    }

    public BitacoraPostulacion(Integer idBitacora) {
        this.idBitacora = idBitacora;
    }

    public BitacoraPostulacion(Integer idBitacora, String estadoNuevo) {
        this.idBitacora = idBitacora;
        this.estadoNuevo = estadoNuevo;
    }

    public Integer getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(Integer idBitacora) {
        this.idBitacora = idBitacora;
    }

    public String getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(String estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public String getEstadoNuevo() {
        return estadoNuevo;
    }

    public void setEstadoNuevo(String estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public Postulacion getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(Postulacion idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBitacora != null ? idBitacora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BitacoraPostulacion)) {
            return false;
        }
        BitacoraPostulacion other = (BitacoraPostulacion) object;
        if ((this.idBitacora == null && other.idBitacora != null) || (this.idBitacora != null && !this.idBitacora.equals(other.idBitacora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return observacion != null ? observacion : "";
    }
}
