/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reclutamiento.prueba2.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "Contratacion")
@NamedQueries({
    @NamedQuery(name = "Contratacion.findAll", query = "SELECT c FROM Contratacion c"),
    @NamedQuery(name = "Contratacion.findByIdContratacion", query = "SELECT c FROM Contratacion c WHERE c.idContratacion = :idContratacion"),
    @NamedQuery(name = "Contratacion.findByFechaContratacion", query = "SELECT c FROM Contratacion c WHERE c.fechaContratacion = :fechaContratacion"),
    @NamedQuery(name = "Contratacion.findBySueldoAcordado", query = "SELECT c FROM Contratacion c WHERE c.sueldoAcordado = :sueldoAcordado"),
    @NamedQuery(name = "Contratacion.findByCostoServicio", query = "SELECT c FROM Contratacion c WHERE c.costoServicio = :costoServicio")})
public class Contratacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdContratacion")
    private Integer idContratacion;
    @Column(name = "FechaContratacion")
    @Temporal(TemporalType.DATE)
    private Date fechaContratacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "SueldoAcordado")
    private BigDecimal sueldoAcordado;
    @Basic(optional = false)
    @Column(name = "CostoServicio")
    private BigDecimal costoServicio;
    @JoinColumn(name = "IdPostulacion", referencedColumnName = "IdPostulacion")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Postulacion idPostulacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContratacion", fetch = FetchType.LAZY)
    private List<Factura> facturaList;

    public Contratacion() {
    }

    public Contratacion(Integer idContratacion) {
        this.idContratacion = idContratacion;
    }

    public Contratacion(Integer idContratacion, BigDecimal sueldoAcordado, BigDecimal costoServicio) {
        this.idContratacion = idContratacion;
        this.sueldoAcordado = sueldoAcordado;
        this.costoServicio = costoServicio;
    }

    public Integer getIdContratacion() {
        return idContratacion;
    }

    public void setIdContratacion(Integer idContratacion) {
        this.idContratacion = idContratacion;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public BigDecimal getSueldoAcordado() {
        return sueldoAcordado;
    }

    public void setSueldoAcordado(BigDecimal sueldoAcordado) {
        this.sueldoAcordado = sueldoAcordado;
    }

    public BigDecimal getCostoServicio() {
        return costoServicio;
    }

    public void setCostoServicio(BigDecimal costoServicio) {
        this.costoServicio = costoServicio;
    }

    public Postulacion getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(Postulacion idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    @Column(name = "Estado")
    private Boolean estado;

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContratacion != null ? idContratacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contratacion)) {
            return false;
        }
        Contratacion other = (Contratacion) object;
        if ((this.idContratacion == null && other.idContratacion != null) || (this.idContratacion != null && !this.idContratacion.equals(other.idContratacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idContratacion != null ? "Contratación #" + idContratacion : "";
    }
}
