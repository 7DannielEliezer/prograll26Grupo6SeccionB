/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reclutamiento.prueba2.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import reclutamiento.prueba2.controller.exceptions.NonexistentEntityException;
import reclutamiento.prueba2.controller.exceptions.PreexistingEntityException;
import reclutamiento.prueba2.model.Usuario;
import reclutamiento.prueba2.model.Vacante;
import reclutamiento.prueba2.model.VacanteReclutador;
import reclutamiento.prueba2.model.VacanteReclutadorPK;

/**
 *
 * @author PGutierrez
 */
public class VacanteReclutadorJpaController implements Serializable {

    public VacanteReclutadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VacanteReclutador vacanteReclutador) throws PreexistingEntityException, Exception {
        if (vacanteReclutador.getVacanteReclutadorPK() == null) {
            vacanteReclutador.setVacanteReclutadorPK(new VacanteReclutadorPK());
        }
        vacanteReclutador.getVacanteReclutadorPK().setIdUsuario(vacanteReclutador.getUsuario().getIdUsuario());
        vacanteReclutador.getVacanteReclutadorPK().setIdVacante(vacanteReclutador.getVacante().getIdVacante());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = vacanteReclutador.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getIdUsuario());
                vacanteReclutador.setUsuario(usuario);
            }
            Vacante vacante = vacanteReclutador.getVacante();
            if (vacante != null) {
                vacante = em.getReference(vacante.getClass(), vacante.getIdVacante());
                vacanteReclutador.setVacante(vacante);
            }
            em.persist(vacanteReclutador);
            if (usuario != null) {
                usuario.getVacanteReclutadorList().add(vacanteReclutador);
                usuario = em.merge(usuario);
            }
            if (vacante != null) {
                vacante.getVacanteReclutadorList().add(vacanteReclutador);
                vacante = em.merge(vacante);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVacanteReclutador(vacanteReclutador.getVacanteReclutadorPK()) != null) {
                throw new PreexistingEntityException("VacanteReclutador " + vacanteReclutador + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VacanteReclutador vacanteReclutador) throws NonexistentEntityException, Exception {
        vacanteReclutador.getVacanteReclutadorPK().setIdUsuario(vacanteReclutador.getUsuario().getIdUsuario());
        vacanteReclutador.getVacanteReclutadorPK().setIdVacante(vacanteReclutador.getVacante().getIdVacante());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VacanteReclutador persistentVacanteReclutador = em.find(VacanteReclutador.class, vacanteReclutador.getVacanteReclutadorPK());
            Usuario usuarioOld = persistentVacanteReclutador.getUsuario();
            Usuario usuarioNew = vacanteReclutador.getUsuario();
            Vacante vacanteOld = persistentVacanteReclutador.getVacante();
            Vacante vacanteNew = vacanteReclutador.getVacante();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getIdUsuario());
                vacanteReclutador.setUsuario(usuarioNew);
            }
            if (vacanteNew != null) {
                vacanteNew = em.getReference(vacanteNew.getClass(), vacanteNew.getIdVacante());
                vacanteReclutador.setVacante(vacanteNew);
            }
            vacanteReclutador = em.merge(vacanteReclutador);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getVacanteReclutadorList().remove(vacanteReclutador);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getVacanteReclutadorList().add(vacanteReclutador);
                usuarioNew = em.merge(usuarioNew);
            }
            if (vacanteOld != null && !vacanteOld.equals(vacanteNew)) {
                vacanteOld.getVacanteReclutadorList().remove(vacanteReclutador);
                vacanteOld = em.merge(vacanteOld);
            }
            if (vacanteNew != null && !vacanteNew.equals(vacanteOld)) {
                vacanteNew.getVacanteReclutadorList().add(vacanteReclutador);
                vacanteNew = em.merge(vacanteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                VacanteReclutadorPK id = vacanteReclutador.getVacanteReclutadorPK();
                if (findVacanteReclutador(id) == null) {
                    throw new NonexistentEntityException("The vacanteReclutador with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(VacanteReclutadorPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VacanteReclutador vacanteReclutador;
            try {
                vacanteReclutador = em.getReference(VacanteReclutador.class, id);
                vacanteReclutador.getVacanteReclutadorPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vacanteReclutador with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = vacanteReclutador.getUsuario();
            if (usuario != null) {
                usuario.getVacanteReclutadorList().remove(vacanteReclutador);
                usuario = em.merge(usuario);
            }
            Vacante vacante = vacanteReclutador.getVacante();
            if (vacante != null) {
                vacante.getVacanteReclutadorList().remove(vacanteReclutador);
                vacante = em.merge(vacante);
            }
            em.remove(vacanteReclutador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VacanteReclutador> findVacanteReclutadorEntities() {
        return findVacanteReclutadorEntities(true, -1, -1);
    }

    public List<VacanteReclutador> findVacanteReclutadorEntities(int maxResults, int firstResult) {
        return findVacanteReclutadorEntities(false, maxResults, firstResult);
    }

    private List<VacanteReclutador> findVacanteReclutadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VacanteReclutador.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public VacanteReclutador findVacanteReclutador(VacanteReclutadorPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VacanteReclutador.class, id);
        } finally {
            em.close();
        }
    }

    public int getVacanteReclutadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VacanteReclutador> rt = cq.from(VacanteReclutador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
