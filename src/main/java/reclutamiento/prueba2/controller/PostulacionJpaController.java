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
import reclutamiento.prueba2.model.Contratacion;
import reclutamiento.prueba2.model.Candidato;
import reclutamiento.prueba2.model.Vacante;
import reclutamiento.prueba2.model.Evaluacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import reclutamiento.prueba2.controller.exceptions.IllegalOrphanException;
import reclutamiento.prueba2.controller.exceptions.NonexistentEntityException;
import reclutamiento.prueba2.model.BitacoraPostulacion;
import reclutamiento.prueba2.model.Postulacion;

/**
 *
 * @author PGutierrez
 */
public class PostulacionJpaController implements Serializable {

    public PostulacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Postulacion postulacion) {
        if (postulacion.getEvaluacionList() == null) {
            postulacion.setEvaluacionList(new ArrayList<Evaluacion>());
        }
        if (postulacion.getBitacoraPostulacionList() == null) {
            postulacion.setBitacoraPostulacionList(new ArrayList<BitacoraPostulacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contratacion contratacion = postulacion.getContratacion();
            if (contratacion != null) {
                contratacion = em.getReference(contratacion.getClass(), contratacion.getIdContratacion());
                postulacion.setContratacion(contratacion);
            }
            Candidato idCandidato = postulacion.getIdCandidato();
            if (idCandidato != null) {
                idCandidato = em.getReference(idCandidato.getClass(), idCandidato.getIdCandidato());
                postulacion.setIdCandidato(idCandidato);
            }
            Vacante idVacante = postulacion.getIdVacante();
            if (idVacante != null) {
                idVacante = em.getReference(idVacante.getClass(), idVacante.getIdVacante());
                postulacion.setIdVacante(idVacante);
            }
            List<Evaluacion> attachedEvaluacionList = new ArrayList<Evaluacion>();
            for (Evaluacion evaluacionListEvaluacionToAttach : postulacion.getEvaluacionList()) {
                evaluacionListEvaluacionToAttach = em.getReference(evaluacionListEvaluacionToAttach.getClass(), evaluacionListEvaluacionToAttach.getIdEvaluacion());
                attachedEvaluacionList.add(evaluacionListEvaluacionToAttach);
            }
            postulacion.setEvaluacionList(attachedEvaluacionList);
            List<BitacoraPostulacion> attachedBitacoraPostulacionList = new ArrayList<BitacoraPostulacion>();
            for (BitacoraPostulacion bitacoraPostulacionListBitacoraPostulacionToAttach : postulacion.getBitacoraPostulacionList()) {
                bitacoraPostulacionListBitacoraPostulacionToAttach = em.getReference(bitacoraPostulacionListBitacoraPostulacionToAttach.getClass(), bitacoraPostulacionListBitacoraPostulacionToAttach.getIdBitacora());
                attachedBitacoraPostulacionList.add(bitacoraPostulacionListBitacoraPostulacionToAttach);
            }
            postulacion.setBitacoraPostulacionList(attachedBitacoraPostulacionList);
            em.persist(postulacion);
            if (contratacion != null) {
                Postulacion oldIdPostulacionOfContratacion = contratacion.getIdPostulacion();
                if (oldIdPostulacionOfContratacion != null) {
                    oldIdPostulacionOfContratacion.setContratacion(null);
                    oldIdPostulacionOfContratacion = em.merge(oldIdPostulacionOfContratacion);
                }
                contratacion.setIdPostulacion(postulacion);
                contratacion = em.merge(contratacion);
            }
            if (idCandidato != null) {
                idCandidato.getPostulacionList().add(postulacion);
                idCandidato = em.merge(idCandidato);
            }
            if (idVacante != null) {
                idVacante.getPostulacionList().add(postulacion);
                idVacante = em.merge(idVacante);
            }
            for (Evaluacion evaluacionListEvaluacion : postulacion.getEvaluacionList()) {
                Postulacion oldIdPostulacionOfEvaluacionListEvaluacion = evaluacionListEvaluacion.getIdPostulacion();
                evaluacionListEvaluacion.setIdPostulacion(postulacion);
                evaluacionListEvaluacion = em.merge(evaluacionListEvaluacion);
                if (oldIdPostulacionOfEvaluacionListEvaluacion != null) {
                    oldIdPostulacionOfEvaluacionListEvaluacion.getEvaluacionList().remove(evaluacionListEvaluacion);
                    oldIdPostulacionOfEvaluacionListEvaluacion = em.merge(oldIdPostulacionOfEvaluacionListEvaluacion);
                }
            }
            for (BitacoraPostulacion bitacoraPostulacionListBitacoraPostulacion : postulacion.getBitacoraPostulacionList()) {
                Postulacion oldIdPostulacionOfBitacoraPostulacionListBitacoraPostulacion = bitacoraPostulacionListBitacoraPostulacion.getIdPostulacion();
                bitacoraPostulacionListBitacoraPostulacion.setIdPostulacion(postulacion);
                bitacoraPostulacionListBitacoraPostulacion = em.merge(bitacoraPostulacionListBitacoraPostulacion);
                if (oldIdPostulacionOfBitacoraPostulacionListBitacoraPostulacion != null) {
                    oldIdPostulacionOfBitacoraPostulacionListBitacoraPostulacion.getBitacoraPostulacionList().remove(bitacoraPostulacionListBitacoraPostulacion);
                    oldIdPostulacionOfBitacoraPostulacionListBitacoraPostulacion = em.merge(oldIdPostulacionOfBitacoraPostulacionListBitacoraPostulacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Postulacion postulacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Postulacion persistentPostulacion = em.find(Postulacion.class, postulacion.getIdPostulacion());
            Contratacion contratacionOld = persistentPostulacion.getContratacion();
            Contratacion contratacionNew = postulacion.getContratacion();
            Candidato idCandidatoOld = persistentPostulacion.getIdCandidato();
            Candidato idCandidatoNew = postulacion.getIdCandidato();
            Vacante idVacanteOld = persistentPostulacion.getIdVacante();
            Vacante idVacanteNew = postulacion.getIdVacante();
            List<Evaluacion> evaluacionListOld = persistentPostulacion.getEvaluacionList();
            List<Evaluacion> evaluacionListNew = postulacion.getEvaluacionList();
            List<BitacoraPostulacion> bitacoraPostulacionListOld = persistentPostulacion.getBitacoraPostulacionList();
            List<BitacoraPostulacion> bitacoraPostulacionListNew = postulacion.getBitacoraPostulacionList();
            List<String> illegalOrphanMessages = null;
            if (contratacionOld != null && !contratacionOld.equals(contratacionNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Contratacion " + contratacionOld + " since its idPostulacion field is not nullable.");
            }
            for (Evaluacion evaluacionListOldEvaluacion : evaluacionListOld) {
                if (!evaluacionListNew.contains(evaluacionListOldEvaluacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Evaluacion " + evaluacionListOldEvaluacion + " since its idPostulacion field is not nullable.");
                }
            }
            for (BitacoraPostulacion bitacoraPostulacionListOldBitacoraPostulacion : bitacoraPostulacionListOld) {
                if (!bitacoraPostulacionListNew.contains(bitacoraPostulacionListOldBitacoraPostulacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain BitacoraPostulacion " + bitacoraPostulacionListOldBitacoraPostulacion + " since its idPostulacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (contratacionNew != null) {
                contratacionNew = em.getReference(contratacionNew.getClass(), contratacionNew.getIdContratacion());
                postulacion.setContratacion(contratacionNew);
            }
            if (idCandidatoNew != null) {
                idCandidatoNew = em.getReference(idCandidatoNew.getClass(), idCandidatoNew.getIdCandidato());
                postulacion.setIdCandidato(idCandidatoNew);
            }
            if (idVacanteNew != null) {
                idVacanteNew = em.getReference(idVacanteNew.getClass(), idVacanteNew.getIdVacante());
                postulacion.setIdVacante(idVacanteNew);
            }
            List<Evaluacion> attachedEvaluacionListNew = new ArrayList<Evaluacion>();
            for (Evaluacion evaluacionListNewEvaluacionToAttach : evaluacionListNew) {
                evaluacionListNewEvaluacionToAttach = em.getReference(evaluacionListNewEvaluacionToAttach.getClass(), evaluacionListNewEvaluacionToAttach.getIdEvaluacion());
                attachedEvaluacionListNew.add(evaluacionListNewEvaluacionToAttach);
            }
            evaluacionListNew = attachedEvaluacionListNew;
            postulacion.setEvaluacionList(evaluacionListNew);
            List<BitacoraPostulacion> attachedBitacoraPostulacionListNew = new ArrayList<BitacoraPostulacion>();
            for (BitacoraPostulacion bitacoraPostulacionListNewBitacoraPostulacionToAttach : bitacoraPostulacionListNew) {
                bitacoraPostulacionListNewBitacoraPostulacionToAttach = em.getReference(bitacoraPostulacionListNewBitacoraPostulacionToAttach.getClass(), bitacoraPostulacionListNewBitacoraPostulacionToAttach.getIdBitacora());
                attachedBitacoraPostulacionListNew.add(bitacoraPostulacionListNewBitacoraPostulacionToAttach);
            }
            bitacoraPostulacionListNew = attachedBitacoraPostulacionListNew;
            postulacion.setBitacoraPostulacionList(bitacoraPostulacionListNew);
            postulacion = em.merge(postulacion);
            if (contratacionNew != null && !contratacionNew.equals(contratacionOld)) {
                Postulacion oldIdPostulacionOfContratacion = contratacionNew.getIdPostulacion();
                if (oldIdPostulacionOfContratacion != null) {
                    oldIdPostulacionOfContratacion.setContratacion(null);
                    oldIdPostulacionOfContratacion = em.merge(oldIdPostulacionOfContratacion);
                }
                contratacionNew.setIdPostulacion(postulacion);
                contratacionNew = em.merge(contratacionNew);
            }
            if (idCandidatoOld != null && !idCandidatoOld.equals(idCandidatoNew)) {
                idCandidatoOld.getPostulacionList().remove(postulacion);
                idCandidatoOld = em.merge(idCandidatoOld);
            }
            if (idCandidatoNew != null && !idCandidatoNew.equals(idCandidatoOld)) {
                idCandidatoNew.getPostulacionList().add(postulacion);
                idCandidatoNew = em.merge(idCandidatoNew);
            }
            if (idVacanteOld != null && !idVacanteOld.equals(idVacanteNew)) {
                idVacanteOld.getPostulacionList().remove(postulacion);
                idVacanteOld = em.merge(idVacanteOld);
            }
            if (idVacanteNew != null && !idVacanteNew.equals(idVacanteOld)) {
                idVacanteNew.getPostulacionList().add(postulacion);
                idVacanteNew = em.merge(idVacanteNew);
            }
            for (Evaluacion evaluacionListNewEvaluacion : evaluacionListNew) {
                if (!evaluacionListOld.contains(evaluacionListNewEvaluacion)) {
                    Postulacion oldIdPostulacionOfEvaluacionListNewEvaluacion = evaluacionListNewEvaluacion.getIdPostulacion();
                    evaluacionListNewEvaluacion.setIdPostulacion(postulacion);
                    evaluacionListNewEvaluacion = em.merge(evaluacionListNewEvaluacion);
                    if (oldIdPostulacionOfEvaluacionListNewEvaluacion != null && !oldIdPostulacionOfEvaluacionListNewEvaluacion.equals(postulacion)) {
                        oldIdPostulacionOfEvaluacionListNewEvaluacion.getEvaluacionList().remove(evaluacionListNewEvaluacion);
                        oldIdPostulacionOfEvaluacionListNewEvaluacion = em.merge(oldIdPostulacionOfEvaluacionListNewEvaluacion);
                    }
                }
            }
            for (BitacoraPostulacion bitacoraPostulacionListNewBitacoraPostulacion : bitacoraPostulacionListNew) {
                if (!bitacoraPostulacionListOld.contains(bitacoraPostulacionListNewBitacoraPostulacion)) {
                    Postulacion oldIdPostulacionOfBitacoraPostulacionListNewBitacoraPostulacion = bitacoraPostulacionListNewBitacoraPostulacion.getIdPostulacion();
                    bitacoraPostulacionListNewBitacoraPostulacion.setIdPostulacion(postulacion);
                    bitacoraPostulacionListNewBitacoraPostulacion = em.merge(bitacoraPostulacionListNewBitacoraPostulacion);
                    if (oldIdPostulacionOfBitacoraPostulacionListNewBitacoraPostulacion != null && !oldIdPostulacionOfBitacoraPostulacionListNewBitacoraPostulacion.equals(postulacion)) {
                        oldIdPostulacionOfBitacoraPostulacionListNewBitacoraPostulacion.getBitacoraPostulacionList().remove(bitacoraPostulacionListNewBitacoraPostulacion);
                        oldIdPostulacionOfBitacoraPostulacionListNewBitacoraPostulacion = em.merge(oldIdPostulacionOfBitacoraPostulacionListNewBitacoraPostulacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = postulacion.getIdPostulacion();
                if (findPostulacion(id) == null) {
                    throw new NonexistentEntityException("The postulacion with id " + id + " no longer exists.");
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
            Postulacion postulacion;
            try {
                postulacion = em.getReference(Postulacion.class, id);
                postulacion.getIdPostulacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The postulacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Contratacion contratacionOrphanCheck = postulacion.getContratacion();
            if (contratacionOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Postulacion (" + postulacion + ") cannot be destroyed since the Contratacion " + contratacionOrphanCheck + " in its contratacion field has a non-nullable idPostulacion field.");
            }
            List<Evaluacion> evaluacionListOrphanCheck = postulacion.getEvaluacionList();
            for (Evaluacion evaluacionListOrphanCheckEvaluacion : evaluacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Postulacion (" + postulacion + ") cannot be destroyed since the Evaluacion " + evaluacionListOrphanCheckEvaluacion + " in its evaluacionList field has a non-nullable idPostulacion field.");
            }
            List<BitacoraPostulacion> bitacoraPostulacionListOrphanCheck = postulacion.getBitacoraPostulacionList();
            for (BitacoraPostulacion bitacoraPostulacionListOrphanCheckBitacoraPostulacion : bitacoraPostulacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Postulacion (" + postulacion + ") cannot be destroyed since the BitacoraPostulacion " + bitacoraPostulacionListOrphanCheckBitacoraPostulacion + " in its bitacoraPostulacionList field has a non-nullable idPostulacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Candidato idCandidato = postulacion.getIdCandidato();
            if (idCandidato != null) {
                idCandidato.getPostulacionList().remove(postulacion);
                idCandidato = em.merge(idCandidato);
            }
            Vacante idVacante = postulacion.getIdVacante();
            if (idVacante != null) {
                idVacante.getPostulacionList().remove(postulacion);
                idVacante = em.merge(idVacante);
            }
            em.remove(postulacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Postulacion> findPostulacionEntities() {
        return findPostulacionEntities(true, -1, -1);
    }

    public List<Postulacion> findPostulacionEntities(int maxResults, int firstResult) {
        return findPostulacionEntities(false, maxResults, firstResult);
    }

    private List<Postulacion> findPostulacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Postulacion.class));
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

    public Postulacion findPostulacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Postulacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getPostulacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Postulacion> rt = cq.from(Postulacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
