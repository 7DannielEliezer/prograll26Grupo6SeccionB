/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.db;

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
@Table(name = "Vacantes", catalog = "Reclutadora", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Vacantes.findAll", query = "SELECT v FROM Vacantes v")})
public class Vacantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_vacante", nullable = false)
    private Integer idVacante;
    @Basic(optional = false)
    @Column(name = "puesto", nullable = false, length = 150)
    private String puesto;
    @Column(name = "descripcion", length = 2147483647)
    private String descripcion;
    @Column(name = "requisitos", length = 2147483647)
    private String requisitos;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salario_ofrecido", precision = 12, scale = 2)
    private BigDecimal salarioOfrecido;
    @Basic(optional = false)
    @Column(name = "fecha_solicitud", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaSolicitud;
    @Column(name = "fecha_limite")
    @Temporal(TemporalType.DATE)
    private Date fechaLimite;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Empresas idEmpresa;

    public Vacantes() {
    }

    public Vacantes(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public Vacantes(Integer idVacante, String puesto, Date fechaSolicitud, String estado) {
        this.idVacante = idVacante;
        this.puesto = puesto;
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public BigDecimal getSalarioOfrecido() {
        return salarioOfrecido;
    }

    public void setSalarioOfrecido(BigDecimal salarioOfrecido) {
        this.salarioOfrecido = salarioOfrecido;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Empresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVacante != null ? idVacante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vacantes)) {
            return false;
        }
        Vacantes other = (Vacantes) object;
        if ((this.idVacante == null && other.idVacante != null) || (this.idVacante != null && !this.idVacante.equals(other.idVacante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.db.Vacantes[ idVacante=" + idVacante + " ]";
    }
    
}
