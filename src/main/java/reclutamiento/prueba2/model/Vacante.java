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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author PGutierrez
 */
@Entity
@Table(name = "Vacante")
@NamedQueries({
    @NamedQuery(name = "Vacante.findAll", query = "SELECT v FROM Vacante v"),
    @NamedQuery(name = "Vacante.findByIdVacante", query = "SELECT v FROM Vacante v WHERE v.idVacante = :idVacante"),
    @NamedQuery(name = "Vacante.findByTituloVacante", query = "SELECT v FROM Vacante v WHERE v.tituloVacante = :tituloVacante"),
    @NamedQuery(name = "Vacante.findByDescripcion", query = "SELECT v FROM Vacante v WHERE v.descripcion = :descripcion"),
    @NamedQuery(name = "Vacante.findBySueldoOfrecido", query = "SELECT v FROM Vacante v WHERE v.sueldoOfrecido = :sueldoOfrecido"),
    @NamedQuery(name = "Vacante.findByEstadoVacante", query = "SELECT v FROM Vacante v WHERE v.estadoVacante = :estadoVacante"),
    @NamedQuery(name = "Vacante.findByFechaPublicacion", query = "SELECT v FROM Vacante v WHERE v.fechaPublicacion = :fechaPublicacion")})
public class Vacante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdVacante")
    private Integer idVacante;
    @Basic(optional = false)
    @Column(name = "TituloVacante")
    private String tituloVacante;
    @Column(name = "Descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SueldoOfrecido")
    private BigDecimal sueldoOfrecido;
    @Column(name = "EstadoVacante")
    private String estadoVacante;
    @Column(name = "FechaPublicacion")
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vacante", fetch = FetchType.LAZY)
    private List<VacanteReclutador> vacanteReclutadorList;
    @JoinColumn(name = "IdEmpresa", referencedColumnName = "IdEmpresa")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Empresa idEmpresa;
    @JoinColumn(name = "IdUsuarioCreador", referencedColumnName = "IdUsuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario idUsuarioCreador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVacante", fetch = FetchType.LAZY)
    private List<Postulacion> postulacionList;

    public Vacante() {
    }

    public Vacante(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public Vacante(Integer idVacante, String tituloVacante) {
        this.idVacante = idVacante;
        this.tituloVacante = tituloVacante;
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Integer idVacante) {
        this.idVacante = idVacante;
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

    public String getEstadoVacante() {
        return estadoVacante;
    }

    public void setEstadoVacante(String estadoVacante) {
        this.estadoVacante = estadoVacante;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public List<VacanteReclutador> getVacanteReclutadorList() {
        return vacanteReclutadorList;
    }

    public void setVacanteReclutadorList(List<VacanteReclutador> vacanteReclutadorList) {
        this.vacanteReclutadorList = vacanteReclutadorList;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Usuario getIdUsuarioCreador() {
        return idUsuarioCreador;
    }

    public void setIdUsuarioCreador(Usuario idUsuarioCreador) {
        this.idUsuarioCreador = idUsuarioCreador;
    }

    public List<Postulacion> getPostulacionList() {
        return postulacionList;
    }

    public void setPostulacionList(List<Postulacion> postulacionList) {
        this.postulacionList = postulacionList;
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
        if (!(object instanceof Vacante)) {
            return false;
        }
        Vacante other = (Vacante) object;
        if ((this.idVacante == null && other.idVacante != null) || (this.idVacante != null && !this.idVacante.equals(other.idVacante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tituloVacante != null ? tituloVacante : "";
    }
}
