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
import reclutamiento.prueba2.model.Rol;
import reclutamiento.prueba2.model.VacanteReclutador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import reclutamiento.prueba2.controller.exceptions.IllegalOrphanException;
import reclutamiento.prueba2.controller.exceptions.NonexistentEntityException;
import reclutamiento.prueba2.model.Vacante;
import reclutamiento.prueba2.model.Evaluacion;
import reclutamiento.prueba2.model.BitacoraPostulacion;
import reclutamiento.prueba2.model.Usuario;

/**
 *
 * @author PGutierrez
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getVacanteReclutadorList() == null) {
            usuario.setVacanteReclutadorList(new ArrayList<VacanteReclutador>());
        }
        if (usuario.getVacanteList() == null) {
            usuario.setVacanteList(new ArrayList<Vacante>());
        }
        if (usuario.getEvaluacionList() == null) {
            usuario.setEvaluacionList(new ArrayList<Evaluacion>());
        }
        if (usuario.getBitacoraPostulacionList() == null) {
            usuario.setBitacoraPostulacionList(new ArrayList<BitacoraPostulacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol idRol = usuario.getIdRol();
            if (idRol != null) {
                idRol = em.getReference(idRol.getClass(), idRol.getIdRol());
                usuario.setIdRol(idRol);
            }
            List<VacanteReclutador> attachedVacanteReclutadorList = new ArrayList<VacanteReclutador>();
            for (VacanteReclutador vacanteReclutadorListVacanteReclutadorToAttach : usuario.getVacanteReclutadorList()) {
                vacanteReclutadorListVacanteReclutadorToAttach = em.getReference(vacanteReclutadorListVacanteReclutadorToAttach.getClass(), vacanteReclutadorListVacanteReclutadorToAttach.getVacanteReclutadorPK());
                attachedVacanteReclutadorList.add(vacanteReclutadorListVacanteReclutadorToAttach);
            }
            usuario.setVacanteReclutadorList(attachedVacanteReclutadorList);
            List<Vacante> attachedVacanteList = new ArrayList<Vacante>();
            for (Vacante vacanteListVacanteToAttach : usuario.getVacanteList()) {
                vacanteListVacanteToAttach = em.getReference(vacanteListVacanteToAttach.getClass(), vacanteListVacanteToAttach.getIdVacante());
                attachedVacanteList.add(vacanteListVacanteToAttach);
            }
            usuario.setVacanteList(attachedVacanteList);
            List<Evaluacion> attachedEvaluacionList = new ArrayList<Evaluacion>();
            for (Evaluacion evaluacionListEvaluacionToAttach : usuario.getEvaluacionList()) {
                evaluacionListEvaluacionToAttach = em.getReference(evaluacionListEvaluacionToAttach.getClass(), evaluacionListEvaluacionToAttach.getIdEvaluacion());
                attachedEvaluacionList.add(evaluacionListEvaluacionToAttach);
            }
            usuario.setEvaluacionList(attachedEvaluacionList);
            List<BitacoraPostulacion> attachedBitacoraPostulacionList = new ArrayList<BitacoraPostulacion>();
            for (BitacoraPostulacion bitacoraPostulacionListBitacoraPostulacionToAttach : usuario.getBitacoraPostulacionList()) {
                bitacoraPostulacionListBitacoraPostulacionToAttach = em.getReference(bitacoraPostulacionListBitacoraPostulacionToAttach.getClass(), bitacoraPostulacionListBitacoraPostulacionToAttach.getIdBitacora());
                attachedBitacoraPostulacionList.add(bitacoraPostulacionListBitacoraPostulacionToAttach);
            }
            usuario.setBitacoraPostulacionList(attachedBitacoraPostulacionList);
            em.persist(usuario);
            if (idRol != null) {
                idRol.getUsuarioList().add(usuario);
                idRol = em.merge(idRol);
            }
            for (VacanteReclutador vacanteReclutadorListVacanteReclutador : usuario.getVacanteReclutadorList()) {
                Usuario oldUsuarioOfVacanteReclutadorListVacanteReclutador = vacanteReclutadorListVacanteReclutador.getUsuario();
                vacanteReclutadorListVacanteReclutador.setUsuario(usuario);
                vacanteReclutadorListVacanteReclutador = em.merge(vacanteReclutadorListVacanteReclutador);
                if (oldUsuarioOfVacanteReclutadorListVacanteReclutador != null) {
                    oldUsuarioOfVacanteReclutadorListVacanteReclutador.getVacanteReclutadorList().remove(vacanteReclutadorListVacanteReclutador);
                    oldUsuarioOfVacanteReclutadorListVacanteReclutador = em.merge(oldUsuarioOfVacanteReclutadorListVacanteReclutador);
                }
            }
            for (Vacante vacanteListVacante : usuario.getVacanteList()) {
                Usuario oldIdUsuarioCreadorOfVacanteListVacante = vacanteListVacante.getIdUsuarioCreador();
                vacanteListVacante.setIdUsuarioCreador(usuario);
                vacanteListVacante = em.merge(vacanteListVacante);
                if (oldIdUsuarioCreadorOfVacanteListVacante != null) {
                    oldIdUsuarioCreadorOfVacanteListVacante.getVacanteList().remove(vacanteListVacante);
                    oldIdUsuarioCreadorOfVacanteListVacante = em.merge(oldIdUsuarioCreadorOfVacanteListVacante);
                }
            }
            for (Evaluacion evaluacionListEvaluacion : usuario.getEvaluacionList()) {
                Usuario oldIdEvaluadorOfEvaluacionListEvaluacion = evaluacionListEvaluacion.getIdEvaluador();
                evaluacionListEvaluacion.setIdEvaluador(usuario);
                evaluacionListEvaluacion = em.merge(evaluacionListEvaluacion);
                if (oldIdEvaluadorOfEvaluacionListEvaluacion != null) {
                    oldIdEvaluadorOfEvaluacionListEvaluacion.getEvaluacionList().remove(evaluacionListEvaluacion);
                    oldIdEvaluadorOfEvaluacionListEvaluacion = em.merge(oldIdEvaluadorOfEvaluacionListEvaluacion);
                }
            }
            for (BitacoraPostulacion bitacoraPostulacionListBitacoraPostulacion : usuario.getBitacoraPostulacionList()) {
                Usuario oldIdUsuarioOfBitacoraPostulacionListBitacoraPostulacion = bitacoraPostulacionListBitacoraPostulacion.getIdUsuario();
                bitacoraPostulacionListBitacoraPostulacion.setIdUsuario(usuario);
                bitacoraPostulacionListBitacoraPostulacion = em.merge(bitacoraPostulacionListBitacoraPostulacion);
                if (oldIdUsuarioOfBitacoraPostulacionListBitacoraPostulacion != null) {
                    oldIdUsuarioOfBitacoraPostulacionListBitacoraPostulacion.getBitacoraPostulacionList().remove(bitacoraPostulacionListBitacoraPostulacion);
                    oldIdUsuarioOfBitacoraPostulacionListBitacoraPostulacion = em.merge(oldIdUsuarioOfBitacoraPostulacionListBitacoraPostulacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            Rol idRolOld = persistentUsuario.getIdRol();
            Rol idRolNew = usuario.getIdRol();
            List<VacanteReclutador> vacanteReclutadorListOld = persistentUsuario.getVacanteReclutadorList();
            List<VacanteReclutador> vacanteReclutadorListNew = usuario.getVacanteReclutadorList();
            List<Vacante> vacanteListOld = persistentUsuario.getVacanteList();
            List<Vacante> vacanteListNew = usuario.getVacanteList();
            List<Evaluacion> evaluacionListOld = persistentUsuario.getEvaluacionList();
            List<Evaluacion> evaluacionListNew = usuario.getEvaluacionList();
            List<BitacoraPostulacion> bitacoraPostulacionListOld = persistentUsuario.getBitacoraPostulacionList();
            List<BitacoraPostulacion> bitacoraPostulacionListNew = usuario.getBitacoraPostulacionList();
            List<String> illegalOrphanMessages = null;
            for (VacanteReclutador vacanteReclutadorListOldVacanteReclutador : vacanteReclutadorListOld) {
                if (!vacanteReclutadorListNew.contains(vacanteReclutadorListOldVacanteReclutador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain VacanteReclutador " + vacanteReclutadorListOldVacanteReclutador + " since its usuario field is not nullable.");
                }
            }
            for (Vacante vacanteListOldVacante : vacanteListOld) {
                if (!vacanteListNew.contains(vacanteListOldVacante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Vacante " + vacanteListOldVacante + " since its idUsuarioCreador field is not nullable.");
                }
            }
            for (Evaluacion evaluacionListOldEvaluacion : evaluacionListOld) {
                if (!evaluacionListNew.contains(evaluacionListOldEvaluacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Evaluacion " + evaluacionListOldEvaluacion + " since its idEvaluador field is not nullable.");
                }
            }
            for (BitacoraPostulacion bitacoraPostulacionListOldBitacoraPostulacion : bitacoraPostulacionListOld) {
                if (!bitacoraPostulacionListNew.contains(bitacoraPostulacionListOldBitacoraPostulacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain BitacoraPostulacion " + bitacoraPostulacionListOldBitacoraPostulacion + " since its idUsuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idRolNew != null) {
                idRolNew = em.getReference(idRolNew.getClass(), idRolNew.getIdRol());
                usuario.setIdRol(idRolNew);
            }
            List<VacanteReclutador> attachedVacanteReclutadorListNew = new ArrayList<VacanteReclutador>();
            for (VacanteReclutador vacanteReclutadorListNewVacanteReclutadorToAttach : vacanteReclutadorListNew) {
                vacanteReclutadorListNewVacanteReclutadorToAttach = em.getReference(vacanteReclutadorListNewVacanteReclutadorToAttach.getClass(), vacanteReclutadorListNewVacanteReclutadorToAttach.getVacanteReclutadorPK());
                attachedVacanteReclutadorListNew.add(vacanteReclutadorListNewVacanteReclutadorToAttach);
            }
            vacanteReclutadorListNew = attachedVacanteReclutadorListNew;
            usuario.setVacanteReclutadorList(vacanteReclutadorListNew);
            List<Vacante> attachedVacanteListNew = new ArrayList<Vacante>();
            for (Vacante vacanteListNewVacanteToAttach : vacanteListNew) {
                vacanteListNewVacanteToAttach = em.getReference(vacanteListNewVacanteToAttach.getClass(), vacanteListNewVacanteToAttach.getIdVacante());
                attachedVacanteListNew.add(vacanteListNewVacanteToAttach);
            }
            vacanteListNew = attachedVacanteListNew;
            usuario.setVacanteList(vacanteListNew);
            List<Evaluacion> attachedEvaluacionListNew = new ArrayList<Evaluacion>();
            for (Evaluacion evaluacionListNewEvaluacionToAttach : evaluacionListNew) {
                evaluacionListNewEvaluacionToAttach = em.getReference(evaluacionListNewEvaluacionToAttach.getClass(), evaluacionListNewEvaluacionToAttach.getIdEvaluacion());
                attachedEvaluacionListNew.add(evaluacionListNewEvaluacionToAttach);
            }
            evaluacionListNew = attachedEvaluacionListNew;
            usuario.setEvaluacionList(evaluacionListNew);
            List<BitacoraPostulacion> attachedBitacoraPostulacionListNew = new ArrayList<BitacoraPostulacion>();
            for (BitacoraPostulacion bitacoraPostulacionListNewBitacoraPostulacionToAttach : bitacoraPostulacionListNew) {
                bitacoraPostulacionListNewBitacoraPostulacionToAttach = em.getReference(bitacoraPostulacionListNewBitacoraPostulacionToAttach.getClass(), bitacoraPostulacionListNewBitacoraPostulacionToAttach.getIdBitacora());
                attachedBitacoraPostulacionListNew.add(bitacoraPostulacionListNewBitacoraPostulacionToAttach);
            }
            bitacoraPostulacionListNew = attachedBitacoraPostulacionListNew;
            usuario.setBitacoraPostulacionList(bitacoraPostulacionListNew);
            usuario = em.merge(usuario);
            if (idRolOld != null && !idRolOld.equals(idRolNew)) {
                idRolOld.getUsuarioList().remove(usuario);
                idRolOld = em.merge(idRolOld);
            }
            if (idRolNew != null && !idRolNew.equals(idRolOld)) {
                idRolNew.getUsuarioList().add(usuario);
                idRolNew = em.merge(idRolNew);
            }
            for (VacanteReclutador vacanteReclutadorListNewVacanteReclutador : vacanteReclutadorListNew) {
                if (!vacanteReclutadorListOld.contains(vacanteReclutadorListNewVacanteReclutador)) {
                    Usuario oldUsuarioOfVacanteReclutadorListNewVacanteReclutador = vacanteReclutadorListNewVacanteReclutador.getUsuario();
                    vacanteReclutadorListNewVacanteReclutador.setUsuario(usuario);
                    vacanteReclutadorListNewVacanteReclutador = em.merge(vacanteReclutadorListNewVacanteReclutador);
                    if (oldUsuarioOfVacanteReclutadorListNewVacanteReclutador != null && !oldUsuarioOfVacanteReclutadorListNewVacanteReclutador.equals(usuario)) {
                        oldUsuarioOfVacanteReclutadorListNewVacanteReclutador.getVacanteReclutadorList().remove(vacanteReclutadorListNewVacanteReclutador);
                        oldUsuarioOfVacanteReclutadorListNewVacanteReclutador = em.merge(oldUsuarioOfVacanteReclutadorListNewVacanteReclutador);
                    }
                }
            }
            for (Vacante vacanteListNewVacante : vacanteListNew) {
                if (!vacanteListOld.contains(vacanteListNewVacante)) {
                    Usuario oldIdUsuarioCreadorOfVacanteListNewVacante = vacanteListNewVacante.getIdUsuarioCreador();
                    vacanteListNewVacante.setIdUsuarioCreador(usuario);
                    vacanteListNewVacante = em.merge(vacanteListNewVacante);
                    if (oldIdUsuarioCreadorOfVacanteListNewVacante != null && !oldIdUsuarioCreadorOfVacanteListNewVacante.equals(usuario)) {
                        oldIdUsuarioCreadorOfVacanteListNewVacante.getVacanteList().remove(vacanteListNewVacante);
                        oldIdUsuarioCreadorOfVacanteListNewVacante = em.merge(oldIdUsuarioCreadorOfVacanteListNewVacante);
                    }
                }
            }
            for (Evaluacion evaluacionListNewEvaluacion : evaluacionListNew) {
                if (!evaluacionListOld.contains(evaluacionListNewEvaluacion)) {
                    Usuario oldIdEvaluadorOfEvaluacionListNewEvaluacion = evaluacionListNewEvaluacion.getIdEvaluador();
                    evaluacionListNewEvaluacion.setIdEvaluador(usuario);
                    evaluacionListNewEvaluacion = em.merge(evaluacionListNewEvaluacion);
                    if (oldIdEvaluadorOfEvaluacionListNewEvaluacion != null && !oldIdEvaluadorOfEvaluacionListNewEvaluacion.equals(usuario)) {
                        oldIdEvaluadorOfEvaluacionListNewEvaluacion.getEvaluacionList().remove(evaluacionListNewEvaluacion);
                        oldIdEvaluadorOfEvaluacionListNewEvaluacion = em.merge(oldIdEvaluadorOfEvaluacionListNewEvaluacion);
                    }
                }
            }
            for (BitacoraPostulacion bitacoraPostulacionListNewBitacoraPostulacion : bitacoraPostulacionListNew) {
                if (!bitacoraPostulacionListOld.contains(bitacoraPostulacionListNewBitacoraPostulacion)) {
                    Usuario oldIdUsuarioOfBitacoraPostulacionListNewBitacoraPostulacion = bitacoraPostulacionListNewBitacoraPostulacion.getIdUsuario();
                    bitacoraPostulacionListNewBitacoraPostulacion.setIdUsuario(usuario);
                    bitacoraPostulacionListNewBitacoraPostulacion = em.merge(bitacoraPostulacionListNewBitacoraPostulacion);
                    if (oldIdUsuarioOfBitacoraPostulacionListNewBitacoraPostulacion != null && !oldIdUsuarioOfBitacoraPostulacionListNewBitacoraPostulacion.equals(usuario)) {
                        oldIdUsuarioOfBitacoraPostulacionListNewBitacoraPostulacion.getBitacoraPostulacionList().remove(bitacoraPostulacionListNewBitacoraPostulacion);
                        oldIdUsuarioOfBitacoraPostulacionListNewBitacoraPostulacion = em.merge(oldIdUsuarioOfBitacoraPostulacionListNewBitacoraPostulacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<VacanteReclutador> vacanteReclutadorListOrphanCheck = usuario.getVacanteReclutadorList();
            for (VacanteReclutador vacanteReclutadorListOrphanCheckVacanteReclutador : vacanteReclutadorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the VacanteReclutador " + vacanteReclutadorListOrphanCheckVacanteReclutador + " in its vacanteReclutadorList field has a non-nullable usuario field.");
            }
            List<Vacante> vacanteListOrphanCheck = usuario.getVacanteList();
            for (Vacante vacanteListOrphanCheckVacante : vacanteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Vacante " + vacanteListOrphanCheckVacante + " in its vacanteList field has a non-nullable idUsuarioCreador field.");
            }
            List<Evaluacion> evaluacionListOrphanCheck = usuario.getEvaluacionList();
            for (Evaluacion evaluacionListOrphanCheckEvaluacion : evaluacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Evaluacion " + evaluacionListOrphanCheckEvaluacion + " in its evaluacionList field has a non-nullable idEvaluador field.");
            }
            List<BitacoraPostulacion> bitacoraPostulacionListOrphanCheck = usuario.getBitacoraPostulacionList();
            for (BitacoraPostulacion bitacoraPostulacionListOrphanCheckBitacoraPostulacion : bitacoraPostulacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the BitacoraPostulacion " + bitacoraPostulacionListOrphanCheckBitacoraPostulacion + " in its bitacoraPostulacionList field has a non-nullable idUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Rol idRol = usuario.getIdRol();
            if (idRol != null) {
                idRol.getUsuarioList().remove(usuario);
                idRol = em.merge(idRol);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
