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
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Facturacion")
public class Facturacion extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdFactura")
    private Integer idFactura;

    @Basic(optional = false)
    @Column(name = "NumeroFactura")
    private String numeroFactura;

    @Basic(optional = false)
    @Column(name = "MontoTotal")
    private BigDecimal montoTotal;

    @Basic(optional = false)
    @Column(name = "EstadoFactura")
    private String estadoFactura;

    @Basic(optional = false)
    @Column(name = "FechaEmision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEmision;

    @JoinColumn(name = "IdContratacion", referencedColumnName = "IdContratacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Contratacion idContratacion;

    @JoinColumn(name = "IdEmpresa", referencedColumnName = "IdEmpresa")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Empresa idEmpresa;

    public Facturacion() {
    }

    public Facturacion(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Facturacion(Integer idFactura, String numeroFactura, BigDecimal montoTotal, String estadoFactura, Date fechaEmision) {
        this.idFactura = idFactura;
        this.numeroFactura = numeroFactura;
        this.montoTotal = montoTotal;
        this.estadoFactura = estadoFactura;
        this.fechaEmision = fechaEmision;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(String estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Contratacion getIdContratacion() {
        return idContratacion;
    }

    public void setIdContratacion(Contratacion idContratacion) {
        this.idContratacion = idContratacion;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFactura != null ? idFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Facturacion)) {
            return false;
        }
        Facturacion other = (Facturacion) object;
        if ((this.idFactura == null && other.idFactura != null) || (this.idFactura != null && !this.idFactura.equals(other.idFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return numeroFactura != null ? numeroFactura : "";
    }
}