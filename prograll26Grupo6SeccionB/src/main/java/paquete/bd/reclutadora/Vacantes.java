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
@Table(name = "Vacantes", catalog = "ReclutadoraDB", schema = "dbo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"EmpresaID", "VacanteID"})})
@NamedQueries({
    @NamedQuery(name = "Vacantes.findAll", query = "SELECT v FROM Vacantes v")})
public class Vacantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "VacanteID", nullable = false)
    private Integer vacanteID;
    @Basic(optional = false)
    @Column(name = "TituloVacante", nullable = false, length = 100)
    private String tituloVacante;
    @Column(name = "Descripcion", length = 2147483647)
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SueldoOfrecido", precision = 12, scale = 2)
    private BigDecimal sueldoOfrecido;
    @Basic(optional = false)
    @Column(name = "Estado", nullable = false, length = 20)
    private String estado;
    @Basic(optional = false)
    @Column(name = "FechaPublicacion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vacantes", fetch = FetchType.LAZY)
    private List<Postulaciones> postulacionesList;
    @JoinColumn(name = "EmpresaID", referencedColumnName = "EmpresaID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Empresas empresaID;

    public Vacantes() {
    }

    public Vacantes(Integer vacanteID) {
        this.vacanteID = vacanteID;
    }

    public Vacantes(Integer vacanteID, String tituloVacante, String estado, Date fechaPublicacion) {
        this.vacanteID = vacanteID;
        this.tituloVacante = tituloVacante;
        this.estado = estado;
        this.fechaPublicacion = fechaPublicacion;
    }

    public Integer getVacanteID() {
        return vacanteID;
    }

    public void setVacanteID(Integer vacanteID) {
        this.vacanteID = vacanteID;
    }

    public String getTituloVacante() {
        return tituloVacante;
    }

    public void setTituloVacante(String tituloVacante) {
        this.tituloVacante = tituloVacante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getSueldoOfrecido() {
        return sueldoOfrecido;
    }

    public void setSueldoOfrecido(BigDecimal sueldoOfrecido) {
        this.sueldoOfrecido = sueldoOfrecido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public List<Postulaciones> getPostulacionesList() {
        return postulacionesList;
    }

    public void setPostulacionesList(List<Postulaciones> postulacionesList) {
        this.postulacionesList = postulacionesList;
    }

    public Empresas getEmpresaID() {
        return empresaID;
    }

    public void setEmpresaID(Empresas empresaID) {
        this.empresaID = empresaID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vacanteID != null ? vacanteID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vacantes)) {
            return false;
        }
        Vacantes other = (Vacantes) object;
        if ((this.vacanteID == null && other.vacanteID != null) || (this.vacanteID != null && !this.vacanteID.equals(other.vacanteID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "paquete.bd.reclutadora.Vacantes[ vacanteID=" + vacanteID + " ]";
    }
    
}
