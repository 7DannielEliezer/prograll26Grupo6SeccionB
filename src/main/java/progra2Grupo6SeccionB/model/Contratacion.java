/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progra2Grupo6SeccionB.model;

/**
 *
 * @author PGutierrez
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Contratacion")
public class Contratacion extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdContratacion")
    private Integer idContratacion;

    @Basic(optional = false)
    @Column(name = "FechaContratacion")
    @Temporal(TemporalType.DATE)
    private Date fechaContratacion;

    @Basic(optional = false)
    @Column(name = "SalarioAcordado")
    private BigDecimal salarioAcordado;

    @Basic(optional = false)
    @Column(name = "MontoComisionServicio")
    private BigDecimal montoComisionServicio;

    @Basic(optional = false)
    @Column(name = "Estado")
    private String estado;

    @JoinColumn(name = "IdPostulacion", referencedColumnName = "IdPostulacion")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Postulacion idPostulacion;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContratacion", fetch = FetchType.LAZY)
    private List<Facturacion> facturacionList;

    public Contratacion() {
    }

    public Contratacion(Integer idContratacion) {
        this.idContratacion = idContratacion;
    }

    public Contratacion(Integer idContratacion, Date fechaContratacion, BigDecimal salarioAcordado, BigDecimal montoComisionServicio, String estado) {
        this.idContratacion = idContratacion;
        this.fechaContratacion = fechaContratacion;
        this.salarioAcordado = salarioAcordado;
        this.montoComisionServicio = montoComisionServicio;
        this.estado = estado;
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

    public BigDecimal getSalarioAcordado() {
        return salarioAcordado;
    }

    public void setSalarioAcordado(BigDecimal salarioAcordado) {
        this.salarioAcordado = salarioAcordado;
    }

    public BigDecimal getMontoComisionServicio() {
        return montoComisionServicio;
    }

    public void setMontoComisionServicio(BigDecimal montoComisionServicio) {
        this.montoComisionServicio = montoComisionServicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Postulacion getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(Postulacion idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public List<Facturacion> getFacturacionList() {
        return facturacionList;
    }

    public void setFacturacionList(List<Facturacion> facturacionList) {
        this.facturacionList = facturacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContratacion != null ? idContratacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
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
        return "Contratacion #" + idContratacion;
    }
}
