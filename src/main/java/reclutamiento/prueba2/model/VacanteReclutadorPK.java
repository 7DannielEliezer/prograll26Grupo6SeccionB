/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reclutamiento.prueba2.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author PGutierrez
 */
@Embeddable
public class VacanteReclutadorPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "IdVacante")
    private int idVacante;
    @Basic(optional = false)
    @Column(name = "IdUsuario")
    private int idUsuario;

    public VacanteReclutadorPK() {
    }

    public VacanteReclutadorPK(int idVacante, int idUsuario) {
        this.idVacante = idVacante;
        this.idUsuario = idUsuario;
    }

    public int getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(int idVacante) {
        this.idVacante = idVacante;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idVacante;
        hash += (int) idUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VacanteReclutadorPK)) {
            return false;
        }
        VacanteReclutadorPK other = (VacanteReclutadorPK) object;
        if (this.idVacante != other.idVacante) {
            return false;
        }
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vacante #" + idVacante + " - Usuario #" + idUsuario;
    }
}
