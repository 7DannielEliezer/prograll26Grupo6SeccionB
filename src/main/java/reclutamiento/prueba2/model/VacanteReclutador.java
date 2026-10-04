/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reclutamiento.prueba2.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "VacanteReclutador")
@NamedQueries({
    @NamedQuery(name = "VacanteReclutador.findAll", query = "SELECT v FROM VacanteReclutador v"),
    @NamedQuery(name = "VacanteReclutador.findByIdVacante", query = "SELECT v FROM VacanteReclutador v WHERE v.vacanteReclutadorPK.idVacante = :idVacante"),
    @NamedQuery(name = "VacanteReclutador.findByIdUsuario", query = "SELECT v FROM VacanteReclutador v WHERE v.vacanteReclutadorPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "VacanteReclutador.findByFechaAsignacion", query = "SELECT v FROM VacanteReclutador v WHERE v.fechaAsignacion = :fechaAsignacion")})
public class VacanteReclutador implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VacanteReclutadorPK vacanteReclutadorPK;
    @Column(name = "FechaAsignacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAsignacion;
    @JoinColumn(name = "IdUsuario", referencedColumnName = "IdUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;
    @JoinColumn(name = "IdVacante", referencedColumnName = "IdVacante", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Vacante vacante;

    public VacanteReclutador() {
    }

    public VacanteReclutador(VacanteReclutadorPK vacanteReclutadorPK) {
        this.vacanteReclutadorPK = vacanteReclutadorPK;
    }

    public VacanteReclutador(int idVacante, int idUsuario) {
        this.vacanteReclutadorPK = new VacanteReclutadorPK(idVacante, idUsuario);
    }

    public VacanteReclutadorPK getVacanteReclutadorPK() {
        return vacanteReclutadorPK;
    }

    public void setVacanteReclutadorPK(VacanteReclutadorPK vacanteReclutadorPK) {
        this.vacanteReclutadorPK = vacanteReclutadorPK;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vacanteReclutadorPK != null ? vacanteReclutadorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VacanteReclutador)) {
            return false;
        }
        VacanteReclutador other = (VacanteReclutador) object;
        if ((this.vacanteReclutadorPK == null && other.vacanteReclutadorPK != null) || (this.vacanteReclutadorPK != null && !this.vacanteReclutadorPK.equals(other.vacanteReclutadorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return vacanteReclutadorPK != null
                ? "Asignación [Vacante #" + vacanteReclutadorPK.getIdVacante() + " - Reclutador #" + vacanteReclutadorPK.getIdUsuario() + "]"
                : "";
    }
}
