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
import reclutamiento.prueba2.model.Factura;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import reclutamiento.prueba2.controller.exceptions.IllegalOrphanException;
import reclutamiento.prueba2.controller.exceptions.NonexistentEntityException;
import reclutamiento.prueba2.model.Contratacion;

/**
 *
 * @author PGutierrez
 */
public class ContratacionJpaController implements Serializable {

    public ContratacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Contratacion contratacion) throws IllegalOrphanException {
        if (contratacion.getFacturaList() == null) {
            contratacion.setFacturaList(new ArrayList<Factura>());
        }
        List<String> illegalOrphanMessages = null;
        Postulacion idPostulacionOrphanCheck = contratacion.getIdPostulacion();
        if (idPostulacionOrphanCheck != null) {
            Contratacion oldContratacionOfIdPostulacion = idPostulacionOrphanCheck.getContratacion();
            if (oldContratacionOfIdPostulacion != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Postulacion " + idPostulacionOrphanCheck + " already has an item of type Contratacion whose idPostulacion column cannot be null. Please make another selection for the idPostulacion field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Postulacion idPostulacion = contratacion.getIdPostulacion();
            if (idPostulacion != null) {
                idPostulacion = em.getReference(idPostulacion.getClass(), idPostulacion.getIdPostulacion());
                contratacion.setIdPostulacion(idPostulacion);
            }
            List<Factura> attachedFacturaList = new ArrayList<Factura>();
            for (Factura facturaListFacturaToAttach : contratacion.getFacturaList()) {
                facturaListFacturaToAttach = em.getReference(facturaListFacturaToAttach.getClass(), facturaListFacturaToAttach.getIdFactura());
                attachedFacturaList.add(facturaListFacturaToAttach);
            }
            contratacion.setFacturaList(attachedFacturaList);
            em.persist(contratacion);
            if (idPostulacion != null) {
                idPostulacion.setContratacion(contratacion);
                idPostulacion = em.merge(idPostulacion);
            }
            for (Factura facturaListFactura : contratacion.getFacturaList()) {
                Contratacion oldIdContratacionOfFacturaListFactura = facturaListFactura.getIdContratacion();
                facturaListFactura.setIdContratacion(contratacion);
                facturaListFactura = em.merge(facturaListFactura);
                if (oldIdContratacionOfFacturaListFactura != null) {
                    oldIdContratacionOfFacturaListFactura.getFacturaList().remove(facturaListFactura);
                    oldIdContratacionOfFacturaListFactura = em.merge(oldIdContratacionOfFacturaListFactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Contratacion contratacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contratacion persistentContratacion = em.find(Contratacion.class, contratacion.getIdContratacion());
            Postulacion idPostulacionOld = persistentContratacion.getIdPostulacion();
            Postulacion idPostulacionNew = contratacion.getIdPostulacion();
            List<Factura> facturaListOld = persistentContratacion.getFacturaList();
            List<Factura> facturaListNew = contratacion.getFacturaList();
            List<String> illegalOrphanMessages = null;
            if (idPostulacionNew != null && !idPostulacionNew.equals(idPostulacionOld)) {
                Contratacion oldContratacionOfIdPostulacion = idPostulacionNew.getContratacion();
                if (oldContratacionOfIdPostulacion != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Postulacion " + idPostulacionNew + " already has an item of type Contratacion whose idPostulacion column cannot be null. Please make another selection for the idPostulacion field.");
                }
            }

            if (facturaListNew == null) {
                facturaListNew = new ArrayList<>();
            }

            for (Factura facturaListOldFactura : facturaListOld) {
                if (!facturaListNew.contains(facturaListOldFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Factura " + facturaListOldFactura + " since its idContratacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idPostulacionNew != null) {
                idPostulacionNew = em.getReference(idPostulacionNew.getClass(), idPostulacionNew.getIdPostulacion());
                contratacion.setIdPostulacion(idPostulacionNew);
            }
            List<Factura> attachedFacturaListNew = new ArrayList<Factura>();
            for (Factura facturaListNewFacturaToAttach : facturaListNew) {
                facturaListNewFacturaToAttach = em.getReference(facturaListNewFacturaToAttach.getClass(), facturaListNewFacturaToAttach.getIdFactura());
                attachedFacturaListNew.add(facturaListNewFacturaToAttach);
            }
            facturaListNew = attachedFacturaListNew;
            contratacion.setFacturaList(facturaListNew);
            contratacion = em.merge(contratacion);
            if (idPostulacionOld != null && !idPostulacionOld.equals(idPostulacionNew)) {
                idPostulacionOld.setContratacion(null);
                idPostulacionOld = em.merge(idPostulacionOld);
            }
            if (idPostulacionNew != null && !idPostulacionNew.equals(idPostulacionOld)) {
                idPostulacionNew.setContratacion(contratacion);
                idPostulacionNew = em.merge(idPostulacionNew);
            }
            for (Factura facturaListNewFactura : facturaListNew) {
                if (!facturaListOld.contains(facturaListNewFactura)) {
                    Contratacion oldIdContratacionOfFacturaListNewFactura = facturaListNewFactura.getIdContratacion();
                    facturaListNewFactura.setIdContratacion(contratacion);
                    facturaListNewFactura = em.merge(facturaListNewFactura);
                    if (oldIdContratacionOfFacturaListNewFactura != null && !oldIdContratacionOfFacturaListNewFactura.equals(contratacion)) {
                        oldIdContratacionOfFacturaListNewFactura.getFacturaList().remove(facturaListNewFactura);
                        oldIdContratacionOfFacturaListNewFactura = em.merge(oldIdContratacionOfFacturaListNewFactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = contratacion.getIdContratacion();
                if (findContratacion(id) == null) {
                    throw new NonexistentEntityException("The contratacion with id " + id + " no longer exists.");
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
            Contratacion contratacion;
            try {
                contratacion = em.getReference(Contratacion.class, id);
                contratacion.getIdContratacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contratacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Factura> facturaListOrphanCheck = contratacion.getFacturaList();
            for (Factura facturaListOrphanCheckFactura : facturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Contratacion (" + contratacion + ") cannot be destroyed since the Factura " + facturaListOrphanCheckFactura + " in its facturaList field has a non-nullable idContratacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Postulacion idPostulacion = contratacion.getIdPostulacion();
            if (idPostulacion != null) {
                idPostulacion.setContratacion(null);
                idPostulacion = em.merge(idPostulacion);
            }
            em.remove(contratacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Contratacion> findContratacionEntities() {
        return findContratacionEntities(true, -1, -1);
    }

    public List<Contratacion> findContratacionEntities(int maxResults, int firstResult) {
        return findContratacionEntities(false, maxResults, firstResult);
    }

    private List<Contratacion> findContratacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contratacion.class));
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

    public Contratacion findContratacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contratacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getContratacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contratacion> rt = cq.from(Contratacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
