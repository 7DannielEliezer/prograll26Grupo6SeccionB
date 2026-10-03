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
import reclutamiento.prueba2.model.Empresa;
import reclutamiento.prueba2.model.Usuario;
import reclutamiento.prueba2.model.VacanteReclutador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import reclutamiento.prueba2.controller.exceptions.IllegalOrphanException;
import reclutamiento.prueba2.controller.exceptions.NonexistentEntityException;
import reclutamiento.prueba2.model.Postulacion;
import reclutamiento.prueba2.model.Vacante;

/**
 *
 * @author PGutierrez
 */
public class VacanteJpaController implements Serializable {

    public VacanteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vacante vacante) {
        if (vacante.getVacanteReclutadorList() == null) {
            vacante.setVacanteReclutadorList(new ArrayList<VacanteReclutador>());
        }
        if (vacante.getPostulacionList() == null) {
            vacante.setPostulacionList(new ArrayList<Postulacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresa idEmpresa = vacante.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getIdEmpresa());
                vacante.setIdEmpresa(idEmpresa);
            }
            Usuario idUsuarioCreador = vacante.getIdUsuarioCreador();
            if (idUsuarioCreador != null) {
                idUsuarioCreador = em.getReference(idUsuarioCreador.getClass(), idUsuarioCreador.getIdUsuario());
                vacante.setIdUsuarioCreador(idUsuarioCreador);
            }
            List<VacanteReclutador> attachedVacanteReclutadorList = new ArrayList<VacanteReclutador>();
            for (VacanteReclutador vacanteReclutadorListVacanteReclutadorToAttach : vacante.getVacanteReclutadorList()) {
                vacanteReclutadorListVacanteReclutadorToAttach = em.getReference(vacanteReclutadorListVacanteReclutadorToAttach.getClass(), vacanteReclutadorListVacanteReclutadorToAttach.getVacanteReclutadorPK());
                attachedVacanteReclutadorList.add(vacanteReclutadorListVacanteReclutadorToAttach);
            }
            vacante.setVacanteReclutadorList(attachedVacanteReclutadorList);
            List<Postulacion> attachedPostulacionList = new ArrayList<Postulacion>();
            for (Postulacion postulacionListPostulacionToAttach : vacante.getPostulacionList()) {
                postulacionListPostulacionToAttach = em.getReference(postulacionListPostulacionToAttach.getClass(), postulacionListPostulacionToAttach.getIdPostulacion());
                attachedPostulacionList.add(postulacionListPostulacionToAttach);
            }
            vacante.setPostulacionList(attachedPostulacionList);
            em.persist(vacante);
            if (idEmpresa != null) {
                idEmpresa.getVacanteList().add(vacante);
                idEmpresa = em.merge(idEmpresa);
            }
            if (idUsuarioCreador != null) {
                idUsuarioCreador.getVacanteList().add(vacante);
                idUsuarioCreador = em.merge(idUsuarioCreador);
            }
            for (VacanteReclutador vacanteReclutadorListVacanteReclutador : vacante.getVacanteReclutadorList()) {
                Vacante oldVacanteOfVacanteReclutadorListVacanteReclutador = vacanteReclutadorListVacanteReclutador.getVacante();
                vacanteReclutadorListVacanteReclutador.setVacante(vacante);
                vacanteReclutadorListVacanteReclutador = em.merge(vacanteReclutadorListVacanteReclutador);
                if (oldVacanteOfVacanteReclutadorListVacanteReclutador != null) {
                    oldVacanteOfVacanteReclutadorListVacanteReclutador.getVacanteReclutadorList().remove(vacanteReclutadorListVacanteReclutador);
                    oldVacanteOfVacanteReclutadorListVacanteReclutador = em.merge(oldVacanteOfVacanteReclutadorListVacanteReclutador);
                }
            }
            for (Postulacion postulacionListPostulacion : vacante.getPostulacionList()) {
                Vacante oldIdVacanteOfPostulacionListPostulacion = postulacionListPostulacion.getIdVacante();
                postulacionListPostulacion.setIdVacante(vacante);
                postulacionListPostulacion = em.merge(postulacionListPostulacion);
                if (oldIdVacanteOfPostulacionListPostulacion != null) {
                    oldIdVacanteOfPostulacionListPostulacion.getPostulacionList().remove(postulacionListPostulacion);
                    oldIdVacanteOfPostulacionListPostulacion = em.merge(oldIdVacanteOfPostulacionListPostulacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vacante vacante) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vacante persistentVacante = em.find(Vacante.class, vacante.getIdVacante());
            Empresa idEmpresaOld = persistentVacante.getIdEmpresa();
            Empresa idEmpresaNew = vacante.getIdEmpresa();
            Usuario idUsuarioCreadorOld = persistentVacante.getIdUsuarioCreador();
            Usuario idUsuarioCreadorNew = vacante.getIdUsuarioCreador();
            List<VacanteReclutador> vacanteReclutadorListOld = persistentVacante.getVacanteReclutadorList();
            List<VacanteReclutador> vacanteReclutadorListNew = vacante.getVacanteReclutadorList();
            List<Postulacion> postulacionListOld = persistentVacante.getPostulacionList();
            List<Postulacion> postulacionListNew = vacante.getPostulacionList();
            List<String> illegalOrphanMessages = null;
            for (VacanteReclutador vacanteReclutadorListOldVacanteReclutador : vacanteReclutadorListOld) {
                if (!vacanteReclutadorListNew.contains(vacanteReclutadorListOldVacanteReclutador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain VacanteReclutador " + vacanteReclutadorListOldVacanteReclutador + " since its vacante field is not nullable.");
                }
            }
            for (Postulacion postulacionListOldPostulacion : postulacionListOld) {
                if (!postulacionListNew.contains(postulacionListOldPostulacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Postulacion " + postulacionListOldPostulacion + " since its idVacante field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getIdEmpresa());
                vacante.setIdEmpresa(idEmpresaNew);
            }
            if (idUsuarioCreadorNew != null) {
                idUsuarioCreadorNew = em.getReference(idUsuarioCreadorNew.getClass(), idUsuarioCreadorNew.getIdUsuario());
                vacante.setIdUsuarioCreador(idUsuarioCreadorNew);
            }
            List<VacanteReclutador> attachedVacanteReclutadorListNew = new ArrayList<VacanteReclutador>();
            for (VacanteReclutador vacanteReclutadorListNewVacanteReclutadorToAttach : vacanteReclutadorListNew) {
                vacanteReclutadorListNewVacanteReclutadorToAttach = em.getReference(vacanteReclutadorListNewVacanteReclutadorToAttach.getClass(), vacanteReclutadorListNewVacanteReclutadorToAttach.getVacanteReclutadorPK());
                attachedVacanteReclutadorListNew.add(vacanteReclutadorListNewVacanteReclutadorToAttach);
            }
            vacanteReclutadorListNew = attachedVacanteReclutadorListNew;
            vacante.setVacanteReclutadorList(vacanteReclutadorListNew);
            List<Postulacion> attachedPostulacionListNew = new ArrayList<Postulacion>();
            for (Postulacion postulacionListNewPostulacionToAttach : postulacionListNew) {
                postulacionListNewPostulacionToAttach = em.getReference(postulacionListNewPostulacionToAttach.getClass(), postulacionListNewPostulacionToAttach.getIdPostulacion());
                attachedPostulacionListNew.add(postulacionListNewPostulacionToAttach);
            }
            postulacionListNew = attachedPostulacionListNew;
            vacante.setPostulacionList(postulacionListNew);
            vacante = em.merge(vacante);
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getVacanteList().remove(vacante);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getVacanteList().add(vacante);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            if (idUsuarioCreadorOld != null && !idUsuarioCreadorOld.equals(idUsuarioCreadorNew)) {
                idUsuarioCreadorOld.getVacanteList().remove(vacante);
                idUsuarioCreadorOld = em.merge(idUsuarioCreadorOld);
            }
            if (idUsuarioCreadorNew != null && !idUsuarioCreadorNew.equals(idUsuarioCreadorOld)) {
                idUsuarioCreadorNew.getVacanteList().add(vacante);
                idUsuarioCreadorNew = em.merge(idUsuarioCreadorNew);
            }
            for (VacanteReclutador vacanteReclutadorListNewVacanteReclutador : vacanteReclutadorListNew) {
                if (!vacanteReclutadorListOld.contains(vacanteReclutadorListNewVacanteReclutador)) {
                    Vacante oldVacanteOfVacanteReclutadorListNewVacanteReclutador = vacanteReclutadorListNewVacanteReclutador.getVacante();
                    vacanteReclutadorListNewVacanteReclutador.setVacante(vacante);
                    vacanteReclutadorListNewVacanteReclutador = em.merge(vacanteReclutadorListNewVacanteReclutador);
                    if (oldVacanteOfVacanteReclutadorListNewVacanteReclutador != null && !oldVacanteOfVacanteReclutadorListNewVacanteReclutador.equals(vacante)) {
                        oldVacanteOfVacanteReclutadorListNewVacanteReclutador.getVacanteReclutadorList().remove(vacanteReclutadorListNewVacanteReclutador);
                        oldVacanteOfVacanteReclutadorListNewVacanteReclutador = em.merge(oldVacanteOfVacanteReclutadorListNewVacanteReclutador);
                    }
                }
            }
            for (Postulacion postulacionListNewPostulacion : postulacionListNew) {
                if (!postulacionListOld.contains(postulacionListNewPostulacion)) {
                    Vacante oldIdVacanteOfPostulacionListNewPostulacion = postulacionListNewPostulacion.getIdVacante();
                    postulacionListNewPostulacion.setIdVacante(vacante);
                    postulacionListNewPostulacion = em.merge(postulacionListNewPostulacion);
                    if (oldIdVacanteOfPostulacionListNewPostulacion != null && !oldIdVacanteOfPostulacionListNewPostulacion.equals(vacante)) {
                        oldIdVacanteOfPostulacionListNewPostulacion.getPostulacionList().remove(postulacionListNewPostulacion);
                        oldIdVacanteOfPostulacionListNewPostulacion = em.merge(oldIdVacanteOfPostulacionListNewPostulacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vacante.getIdVacante();
                if (findVacante(id) == null) {
                    throw new NonexistentEntityException("The vacante with id " + id + " no longer exists.");
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
            Vacante vacante;
            try {
                vacante = em.getReference(Vacante.class, id);
                vacante.getIdVacante();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vacante with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<VacanteReclutador> vacanteReclutadorListOrphanCheck = vacante.getVacanteReclutadorList();
            for (VacanteReclutador vacanteReclutadorListOrphanCheckVacanteReclutador : vacanteReclutadorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vacante (" + vacante + ") cannot be destroyed since the VacanteReclutador " + vacanteReclutadorListOrphanCheckVacanteReclutador + " in its vacanteReclutadorList field has a non-nullable vacante field.");
            }
            List<Postulacion> postulacionListOrphanCheck = vacante.getPostulacionList();
            for (Postulacion postulacionListOrphanCheckPostulacion : postulacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vacante (" + vacante + ") cannot be destroyed since the Postulacion " + postulacionListOrphanCheckPostulacion + " in its postulacionList field has a non-nullable idVacante field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empresa idEmpresa = vacante.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa.getVacanteList().remove(vacante);
                idEmpresa = em.merge(idEmpresa);
            }
            Usuario idUsuarioCreador = vacante.getIdUsuarioCreador();
            if (idUsuarioCreador != null) {
                idUsuarioCreador.getVacanteList().remove(vacante);
                idUsuarioCreador = em.merge(idUsuarioCreador);
            }
            em.remove(vacante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vacante> findVacanteEntities() {
        return findVacanteEntities(true, -1, -1);
    }

    public List<Vacante> findVacanteEntities(int maxResults, int firstResult) {
        return findVacanteEntities(false, maxResults, firstResult);
    }

    private List<Vacante> findVacanteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vacante.class));
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

    public Vacante findVacante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vacante.class, id);
        } finally {
            em.close();
        }
    }

    public int getVacanteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vacante> rt = cq.from(Vacante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
