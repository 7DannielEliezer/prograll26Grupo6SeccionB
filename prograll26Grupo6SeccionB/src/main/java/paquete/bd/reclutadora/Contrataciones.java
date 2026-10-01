/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete.bd.reclutadora;

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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "Contrataciones", catalog = "ReclutadoraDB", schema = "dbo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"PostulacionID"}),
    @UniqueConstraint(columnNames = {"EmpresaID", "ContratacionID"})})
@NamedQueries({
    @NamedQuery(name = "Contrataciones.findAll", query = "SELECT c FROM Contrataciones c")})
public class Contrataciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ContratacionID", nullable = false)
    private Integer contratacionID;
    @Basic(optional = false)
    @Column(name = "FechaContratacion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaContratacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "SueldoAcordado", nullable = false, precision = 12, scale = 2)
    private BigDecimal sueldoAcordado;
    @Basic(optional = false)
    @Column(name = "CostoServicio", nullable = false, precision = 12, scale = 2)
    private BigDecimal costoServicio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contrataciones", fetch = FetchType.LAZY)
    private List<Facturas> facturasList;
    @JoinColumns({
        @JoinColumn(name = "EmpresaID", referencedColumnName = "EmpresaID", nullable = false),
        @JoinColumn(name = "PostulacionID", referencedColumnName = "PostulacionID", nullable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Postulaciones postulaciones;

    public Contrataciones() {
    }

    public Contrataciones(Integer contratacionID) {
        this.contratacionID = contratacionID;
    }

    public Contrataciones(Integer contratacionID, Date fechaContratacion, BigDecimal sueldoAcordado, BigDecimal costoServicio) {
        this.contratacionID = contratacionID;
        this.fechaContratacion = fechaContratacion;
        this.sueldoAcordado = sueldoAcordado;
        this.costoServicio = costoServicio;
    }

    public Integer getContratacionID() {
        return contratacionID;
    }

    public void setContratacionID(Integer contratacionID) {
        this.contratacionID = contratacionID;
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

    public List<Facturas> getFacturasList() {
        return facturasList;
    }

    public void setFacturasList(List<Facturas> facturasList) {
        this.facturasList = facturasList;
    }

    public Postulaciones getPostulaciones() {
        return postulaciones;
    }

    public void setPostulaciones(Postulaciones postulaciones) {
        this.postulaciones = postulaciones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contratacionID != null ? contratacionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrataciones)) {
            return false;
        }
        Contrataciones other = (Contrataciones) object;
        if ((this.contratacionID == null && other.contratacionID != null) || (this.contratacionID != null && !this.contratacionID.equals(other.contratacionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "paquete.bd.reclutadora.Contrataciones[ contratacionID=" + contratacionID + " ]";
    }
    
}
