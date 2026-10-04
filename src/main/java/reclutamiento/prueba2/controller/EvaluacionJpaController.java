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
import reclutamiento.prueba2.model.Evaluacion;
import reclutamiento.prueba2.model.Postulacion;
import reclutamiento.prueba2.model.Usuario;

/**
 *
 * @author PGutierrez
 */
public class EvaluacionJpaController implements Serializable {

    public EvaluacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Evaluacion evaluacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Postulacion idPostulacion = evaluacion.getIdPostulacion();
            if (idPostulacion != null) {
                idPostulacion = em.getReference(idPostulacion.getClass(), idPostulacion.getIdPostulacion());
                evaluacion.setIdPostulacion(idPostulacion);
            }
            Usuario idEvaluador = evaluacion.getIdEvaluador();
            if (idEvaluador != null) {
                idEvaluador = em.getReference(idEvaluador.getClass(), idEvaluador.getIdUsuario());
                evaluacion.setIdEvaluador(idEvaluador);
            }
            em.persist(evaluacion);
            if (idPostulacion != null) {
                idPostulacion.getEvaluacionList().add(evaluacion);
                idPostulacion = em.merge(idPostulacion);
            }
            if (idEvaluador != null) {
                idEvaluador.getEvaluacionList().add(evaluacion);
                idEvaluador = em.merge(idEvaluador);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Evaluacion evaluacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Evaluacion persistentEvaluacion = em.find(Evaluacion.class, evaluacion.getIdEvaluacion());
            Postulacion idPostulacionOld = persistentEvaluacion.getIdPostulacion();
            Postulacion idPostulacionNew = evaluacion.getIdPostulacion();
            Usuario idEvaluadorOld = persistentEvaluacion.getIdEvaluador();
            Usuario idEvaluadorNew = evaluacion.getIdEvaluador();
            if (idPostulacionNew != null) {
                idPostulacionNew = em.getReference(idPostulacionNew.getClass(), idPostulacionNew.getIdPostulacion());
                evaluacion.setIdPostulacion(idPostulacionNew);
            }
            if (idEvaluadorNew != null) {
                idEvaluadorNew = em.getReference(idEvaluadorNew.getClass(), idEvaluadorNew.getIdUsuario());
                evaluacion.setIdEvaluador(idEvaluadorNew);
            }
            evaluacion = em.merge(evaluacion);
            if (idPostulacionOld != null && !idPostulacionOld.equals(idPostulacionNew)) {
                idPostulacionOld.getEvaluacionList().remove(evaluacion);
                idPostulacionOld = em.merge(idPostulacionOld);
            }
            if (idPostulacionNew != null && !idPostulacionNew.equals(idPostulacionOld)) {
                idPostulacionNew.getEvaluacionList().add(evaluacion);
                idPostulacionNew = em.merge(idPostulacionNew);
            }
            if (idEvaluadorOld != null && !idEvaluadorOld.equals(idEvaluadorNew)) {
                idEvaluadorOld.getEvaluacionList().remove(evaluacion);
                idEvaluadorOld = em.merge(idEvaluadorOld);
            }
            if (idEvaluadorNew != null && !idEvaluadorNew.equals(idEvaluadorOld)) {
                idEvaluadorNew.getEvaluacionList().add(evaluacion);
                idEvaluadorNew = em.merge(idEvaluadorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = evaluacion.getIdEvaluacion();
                if (findEvaluacion(id) == null) {
                    throw new NonexistentEntityException("The evaluacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Evaluacion evaluacion;
            try {
                evaluacion = em.getReference(Evaluacion.class, id);
                evaluacion.getIdEvaluacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The evaluacion with id " + id + " no longer exists.", enfe);
            }
            Postulacion idPostulacion = evaluacion.getIdPostulacion();
            if (idPostulacion != null) {
                idPostulacion.getEvaluacionList().remove(evaluacion);
                idPostulacion = em.merge(idPostulacion);
            }
            Usuario idEvaluador = evaluacion.getIdEvaluador();
            if (idEvaluador != null) {
                idEvaluador.getEvaluacionList().remove(evaluacion);
                idEvaluador = em.merge(idEvaluador);
            }
            em.remove(evaluacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Evaluacion> findEvaluacionEntities() {
        return findEvaluacionEntities(true, -1, -1);
    }

    public List<Evaluacion> findEvaluacionEntities(int maxResults, int firstResult) {
        return findEvaluacionEntities(false, maxResults, firstResult);
    }

    private List<Evaluacion> findEvaluacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Evaluacion.class));
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

    public Evaluacion findEvaluacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Evaluacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getEvaluacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Evaluacion> rt = cq.from(Evaluacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
