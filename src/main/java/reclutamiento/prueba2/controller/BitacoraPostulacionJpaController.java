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
import reclutamiento.prueba2.model.BitacoraPostulacion;
import reclutamiento.prueba2.model.Postulacion;
import reclutamiento.prueba2.model.Usuario;

/**
 *
 * @author PGutierrez
 */
public class BitacoraPostulacionJpaController implements Serializable {

    public BitacoraPostulacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BitacoraPostulacion bitacoraPostulacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Postulacion idPostulacion = bitacoraPostulacion.getIdPostulacion();
            if (idPostulacion != null) {
                idPostulacion = em.getReference(idPostulacion.getClass(), idPostulacion.getIdPostulacion());
                bitacoraPostulacion.setIdPostulacion(idPostulacion);
            }
            Usuario idUsuario = bitacoraPostulacion.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                bitacoraPostulacion.setIdUsuario(idUsuario);
            }
            em.persist(bitacoraPostulacion);
            if (idPostulacion != null) {
                idPostulacion.getBitacoraPostulacionList().add(bitacoraPostulacion);
                idPostulacion = em.merge(idPostulacion);
            }
            if (idUsuario != null) {
                idUsuario.getBitacoraPostulacionList().add(bitacoraPostulacion);
                idUsuario = em.merge(idUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BitacoraPostulacion bitacoraPostulacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BitacoraPostulacion persistentBitacoraPostulacion = em.find(BitacoraPostulacion.class, bitacoraPostulacion.getIdBitacora());
            Postulacion idPostulacionOld = persistentBitacoraPostulacion.getIdPostulacion();
            Postulacion idPostulacionNew = bitacoraPostulacion.getIdPostulacion();
            Usuario idUsuarioOld = persistentBitacoraPostulacion.getIdUsuario();
            Usuario idUsuarioNew = bitacoraPostulacion.getIdUsuario();
            if (idPostulacionNew != null) {
                idPostulacionNew = em.getReference(idPostulacionNew.getClass(), idPostulacionNew.getIdPostulacion());
                bitacoraPostulacion.setIdPostulacion(idPostulacionNew);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                bitacoraPostulacion.setIdUsuario(idUsuarioNew);
            }
            bitacoraPostulacion = em.merge(bitacoraPostulacion);
            if (idPostulacionOld != null && !idPostulacionOld.equals(idPostulacionNew)) {
                idPostulacionOld.getBitacoraPostulacionList().remove(bitacoraPostulacion);
                idPostulacionOld = em.merge(idPostulacionOld);
            }
            if (idPostulacionNew != null && !idPostulacionNew.equals(idPostulacionOld)) {
                idPostulacionNew.getBitacoraPostulacionList().add(bitacoraPostulacion);
                idPostulacionNew = em.merge(idPostulacionNew);
            }
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getBitacoraPostulacionList().remove(bitacoraPostulacion);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getBitacoraPostulacionList().add(bitacoraPostulacion);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bitacoraPostulacion.getIdBitacora();
                if (findBitacoraPostulacion(id) == null) {
                    throw new NonexistentEntityException("The bitacoraPostulacion with id " + id + " no longer exists.");
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
            BitacoraPostulacion bitacoraPostulacion;
            try {
                bitacoraPostulacion = em.getReference(BitacoraPostulacion.class, id);
                bitacoraPostulacion.getIdBitacora();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bitacoraPostulacion with id " + id + " no longer exists.", enfe);
            }
            Postulacion idPostulacion = bitacoraPostulacion.getIdPostulacion();
            if (idPostulacion != null) {
                idPostulacion.getBitacoraPostulacionList().remove(bitacoraPostulacion);
                idPostulacion = em.merge(idPostulacion);
            }
            Usuario idUsuario = bitacoraPostulacion.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getBitacoraPostulacionList().remove(bitacoraPostulacion);
                idUsuario = em.merge(idUsuario);
            }
            em.remove(bitacoraPostulacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BitacoraPostulacion> findBitacoraPostulacionEntities() {
        return findBitacoraPostulacionEntities(true, -1, -1);
    }

    public List<BitacoraPostulacion> findBitacoraPostulacionEntities(int maxResults, int firstResult) {
        return findBitacoraPostulacionEntities(false, maxResults, firstResult);
    }

    private List<BitacoraPostulacion> findBitacoraPostulacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BitacoraPostulacion.class));
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

    public BitacoraPostulacion findBitacoraPostulacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BitacoraPostulacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getBitacoraPostulacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BitacoraPostulacion> rt = cq.from(BitacoraPostulacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
