/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete.bd.reclutadora;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "Facturas", catalog = "ReclutadoraDB", schema = "dbo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ContratacionID"}),
    @UniqueConstraint(columnNames = {"NumeroFactura"})})
@NamedQueries({
    @NamedQuery(name = "Facturas.findAll", query = "SELECT f FROM Facturas f")})
public class Facturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FacturaID", nullable = false)
    private Integer facturaID;
    @Basic(optional = false)
    @Column(name = "NumeroFactura", nullable = false, length = 50)
    private String numeroFactura;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "MontoTotal", nullable = false, precision = 12, scale = 2)
    private BigDecimal montoTotal;
    @Basic(optional = false)
    @Column(name = "FechaEmision", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEmision;
    @Basic(optional = false)
    @Column(name = "EstadoFactura", nullable = false, length = 20)
    private String estadoFactura;
    @JoinColumns({
        @JoinColumn(name = "EmpresaID", referencedColumnName = "EmpresaID", nullable = false),
        @JoinColumn(name = "ContratacionID", referencedColumnName = "ContratacionID", nullable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Contrataciones contrataciones;

    public Facturas() {
    }

    public Facturas(Integer facturaID) {
        this.facturaID = facturaID;
    }

    public Facturas(Integer facturaID, String numeroFactura, BigDecimal montoTotal, Date fechaEmision, String estadoFactura) {
        this.facturaID = facturaID;
        this.numeroFactura = numeroFactura;
        this.montoTotal = montoTotal;
        this.fechaEmision = fechaEmision;
        this.estadoFactura = estadoFactura;
    }

    public Integer getFacturaID() {
        return facturaID;
    }

    public void setFacturaID(Integer facturaID) {
        this.facturaID = facturaID;
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

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(String estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public Contrataciones getContrataciones() {
        return contrataciones;
    }

    public void setContrataciones(Contrataciones contrataciones) {
        this.contrataciones = contrataciones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facturaID != null ? facturaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturas)) {
            return false;
        }
        Facturas other = (Facturas) object;
        if ((this.facturaID == null && other.facturaID != null) || (this.facturaID != null && !this.facturaID.equals(other.facturaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "paquete.bd.reclutadora.Facturas[ facturaID=" + facturaID + " ]";
    }
    
}
