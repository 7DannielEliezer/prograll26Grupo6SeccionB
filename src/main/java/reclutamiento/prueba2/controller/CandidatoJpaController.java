/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reclutamiento.prueba2.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import reclutamiento.prueba2.model.Postulacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import reclutamiento.prueba2.controller.exceptions.IllegalOrphanException;
import reclutamiento.prueba2.controller.exceptions.NonexistentEntityException;
import reclutamiento.prueba2.model.Candidato;

/**
 *
 * @author PGutierrez
 */
public class CandidatoJpaController implements Serializable {

    public CandidatoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Candidato candidato) {
        if (candidato.getPostulacionList() == null) {
            candidato.setPostulacionList(new ArrayList<Postulacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Postulacion> attachedPostulacionList = new ArrayList<Postulacion>();
            for (Postulacion postulacionListPostulacionToAttach : candidato.getPostulacionList()) {
                postulacionListPostulacionToAttach = em.getReference(postulacionListPostulacionToAttach.getClass(), postulacionListPostulacionToAttach.getIdPostulacion());
                attachedPostulacionList.add(postulacionListPostulacionToAttach);
            }
            candidato.setPostulacionList(attachedPostulacionList);
            em.persist(candidato);
            for (Postulacion postulacionListPostulacion : candidato.getPostulacionList()) {
                Candidato oldIdCandidatoOfPostulacionListPostulacion = postulacionListPostulacion.getIdCandidato();
                postulacionListPostulacion.setIdCandidato(candidato);
                postulacionListPostulacion = em.merge(postulacionListPostulacion);
                if (oldIdCandidatoOfPostulacionListPostulacion != null) {
                    oldIdCandidatoOfPostulacionListPostulacion.getPostulacionList().remove(postulacionListPostulacion);
                    oldIdCandidatoOfPostulacionListPostulacion = em.merge(oldIdCandidatoOfPostulacionListPostulacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Candidato candidato) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Candidato persistentCandidato = em.find(Candidato.class, candidato.getIdCandidato());
            List<Postulacion> postulacionListOld = persistentCandidato.getPostulacionList();
            List<Postulacion> postulacionListNew = candidato.getPostulacionList();
            List<String> illegalOrphanMessages = null;
            for (Postulacion postulacionListOldPostulacion : postulacionListOld) {
                if (!postulacionListNew.contains(postulacionListOldPostulacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Postulacion " + postulacionListOldPostulacion + " since its idCandidato field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Postulacion> attachedPostulacionListNew = new ArrayList<Postulacion>();
            for (Postulacion postulacionListNewPostulacionToAttach : postulacionListNew) {
                postulacionListNewPostulacionToAttach = em.getReference(postulacionListNewPostulacionToAttach.getClass(), postulacionListNewPostulacionToAttach.getIdPostulacion());
                attachedPostulacionListNew.add(postulacionListNewPostulacionToAttach);
            }
            postulacionListNew = attachedPostulacionListNew;
            candidato.setPostulacionList(postulacionListNew);
            candidato = em.merge(candidato);
            for (Postulacion postulacionListNewPostulacion : postulacionListNew) {
                if (!postulacionListOld.contains(postulacionListNewPostulacion)) {
                    Candidato oldIdCandidatoOfPostulacionListNewPostulacion = postulacionListNewPostulacion.getIdCandidato();
                    postulacionListNewPostulacion.setIdCandidato(candidato);
                    postulacionListNewPostulacion = em.merge(postulacionListNewPostulacion);
                    if (oldIdCandidatoOfPostulacionListNewPostulacion != null && !oldIdCandidatoOfPostulacionListNewPostulacion.equals(candidato)) {
                        oldIdCandidatoOfPostulacionListNewPostulacion.getPostulacionList().remove(postulacionListNewPostulacion);
                        oldIdCandidatoOfPostulacionListNewPostulacion = em.merge(oldIdCandidatoOfPostulacionListNewPostulacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = candidato.getIdCandidato();
                if (findCandidato(id) == null) {
                    throw new NonexistentEntityException("The candidato with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Candidato candidato;
            try {
                candidato = em.getReference(Candidato.class, id);
                candidato.getIdCandidato();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The candidato with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Postulacion> postulacionListOrphanCheck = candidato.getPostulacionList();
            for (Postulacion postulacionListOrphanCheckPostulacion : postulacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Candidato (" + candidato + ") cannot be destroyed since the Postulacion " + postulacionListOrphanCheckPostulacion + " in its postulacionList field has a non-nullable idCandidato field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(candidato);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Candidato> findCandidatoEntities() {
        return findCandidatoEntities(true, -1, -1);
    }

    public List<Candidato> findCandidatoEntities(int maxResults, int firstResult) {
        return findCandidatoEntities(false, maxResults, firstResult);
    }

    private List<Candidato> findCandidatoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Candidato.class));
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

    public Candidato findCandidato(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Candidato.class, id);
        } finally {
            em.close();
        }
    }

    public int getCandidatoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Candidato> rt = cq.from(Candidato.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
