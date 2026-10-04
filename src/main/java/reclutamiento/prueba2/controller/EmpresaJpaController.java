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
import reclutamiento.prueba2.model.Vacante;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import reclutamiento.prueba2.controller.exceptions.IllegalOrphanException;
import reclutamiento.prueba2.controller.exceptions.NonexistentEntityException;
import reclutamiento.prueba2.model.Empresa;
import reclutamiento.prueba2.model.Factura;

/**
 *
 * @author PGutierrez
 */
public class EmpresaJpaController implements Serializable {

    public EmpresaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empresa empresa) {
        if (empresa.getVacanteList() == null) {
            empresa.setVacanteList(new ArrayList<Vacante>());
        }
        if (empresa.getFacturaList() == null) {
            empresa.setFacturaList(new ArrayList<Factura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Vacante> attachedVacanteList = new ArrayList<Vacante>();
            for (Vacante vacanteListVacanteToAttach : empresa.getVacanteList()) {
                vacanteListVacanteToAttach = em.getReference(vacanteListVacanteToAttach.getClass(), vacanteListVacanteToAttach.getIdVacante());
                attachedVacanteList.add(vacanteListVacanteToAttach);
            }
            empresa.setVacanteList(attachedVacanteList);
            List<Factura> attachedFacturaList = new ArrayList<Factura>();
            for (Factura facturaListFacturaToAttach : empresa.getFacturaList()) {
                facturaListFacturaToAttach = em.getReference(facturaListFacturaToAttach.getClass(), facturaListFacturaToAttach.getIdFactura());
                attachedFacturaList.add(facturaListFacturaToAttach);
            }
            empresa.setFacturaList(attachedFacturaList);
            em.persist(empresa);
            for (Vacante vacanteListVacante : empresa.getVacanteList()) {
                Empresa oldIdEmpresaOfVacanteListVacante = vacanteListVacante.getIdEmpresa();
                vacanteListVacante.setIdEmpresa(empresa);
                vacanteListVacante = em.merge(vacanteListVacante);
                if (oldIdEmpresaOfVacanteListVacante != null) {
                    oldIdEmpresaOfVacanteListVacante.getVacanteList().remove(vacanteListVacante);
                    oldIdEmpresaOfVacanteListVacante = em.merge(oldIdEmpresaOfVacanteListVacante);
                }
            }
            for (Factura facturaListFactura : empresa.getFacturaList()) {
                Empresa oldIdEmpresaOfFacturaListFactura = facturaListFactura.getIdEmpresa();
                facturaListFactura.setIdEmpresa(empresa);
                facturaListFactura = em.merge(facturaListFactura);
                if (oldIdEmpresaOfFacturaListFactura != null) {
                    oldIdEmpresaOfFacturaListFactura.getFacturaList().remove(facturaListFactura);
                    oldIdEmpresaOfFacturaListFactura = em.merge(oldIdEmpresaOfFacturaListFactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empresa empresa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresa persistentEmpresa = em.find(Empresa.class, empresa.getIdEmpresa());
            List<Vacante> vacanteListOld = persistentEmpresa.getVacanteList();
            List<Vacante> vacanteListNew = empresa.getVacanteList();
            List<Factura> facturaListOld = persistentEmpresa.getFacturaList();
            List<Factura> facturaListNew = empresa.getFacturaList();
            List<String> illegalOrphanMessages = null;
            for (Vacante vacanteListOldVacante : vacanteListOld) {
                if (!vacanteListNew.contains(vacanteListOldVacante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Vacante " + vacanteListOldVacante + " since its idEmpresa field is not nullable.");
                }
            }
            for (Factura facturaListOldFactura : facturaListOld) {
                if (!facturaListNew.contains(facturaListOldFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Factura " + facturaListOldFactura + " since its idEmpresa field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Vacante> attachedVacanteListNew = new ArrayList<Vacante>();
            for (Vacante vacanteListNewVacanteToAttach : vacanteListNew) {
                vacanteListNewVacanteToAttach = em.getReference(vacanteListNewVacanteToAttach.getClass(), vacanteListNewVacanteToAttach.getIdVacante());
                attachedVacanteListNew.add(vacanteListNewVacanteToAttach);
            }
            vacanteListNew = attachedVacanteListNew;
            empresa.setVacanteList(vacanteListNew);
            List<Factura> attachedFacturaListNew = new ArrayList<Factura>();
            for (Factura facturaListNewFacturaToAttach : facturaListNew) {
                facturaListNewFacturaToAttach = em.getReference(facturaListNewFacturaToAttach.getClass(), facturaListNewFacturaToAttach.getIdFactura());
                attachedFacturaListNew.add(facturaListNewFacturaToAttach);
            }
            facturaListNew = attachedFacturaListNew;
            empresa.setFacturaList(facturaListNew);
            empresa = em.merge(empresa);
            for (Vacante vacanteListNewVacante : vacanteListNew) {
                if (vacanteListNew == null) {
                    vacanteListNew = new ArrayList<>();
                }
                if (facturaListNew == null) {
                    facturaListNew = new ArrayList<>();
                }
                if (!vacanteListOld.contains(vacanteListNewVacante)) {
                    Empresa oldIdEmpresaOfVacanteListNewVacante = vacanteListNewVacante.getIdEmpresa();
                    vacanteListNewVacante.setIdEmpresa(empresa);
                    vacanteListNewVacante = em.merge(vacanteListNewVacante);
                    if (oldIdEmpresaOfVacanteListNewVacante != null && !oldIdEmpresaOfVacanteListNewVacante.equals(empresa)) {
                        oldIdEmpresaOfVacanteListNewVacante.getVacanteList().remove(vacanteListNewVacante);
                        oldIdEmpresaOfVacanteListNewVacante = em.merge(oldIdEmpresaOfVacanteListNewVacante);
                    }
                }
            }
            for (Factura facturaListNewFactura : facturaListNew) {
                if (!facturaListOld.contains(facturaListNewFactura)) {
                    Empresa oldIdEmpresaOfFacturaListNewFactura = facturaListNewFactura.getIdEmpresa();
                    facturaListNewFactura.setIdEmpresa(empresa);
                    facturaListNewFactura = em.merge(facturaListNewFactura);
                    if (oldIdEmpresaOfFacturaListNewFactura != null && !oldIdEmpresaOfFacturaListNewFactura.equals(empresa)) {
                        oldIdEmpresaOfFacturaListNewFactura.getFacturaList().remove(facturaListNewFactura);
                        oldIdEmpresaOfFacturaListNewFactura = em.merge(oldIdEmpresaOfFacturaListNewFactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empresa.getIdEmpresa();
                if (findEmpresa(id) == null) {
                    throw new NonexistentEntityException("The empresa with id " + id + " no longer exists.");
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
            Empresa empresa;
            try {
                empresa = em.getReference(Empresa.class, id);
                empresa.getIdEmpresa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empresa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Vacante> vacanteListOrphanCheck = empresa.getVacanteList();
            for (Vacante vacanteListOrphanCheckVacante : vacanteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empresa (" + empresa + ") cannot be destroyed since the Vacante " + vacanteListOrphanCheckVacante + " in its vacanteList field has a non-nullable idEmpresa field.");
            }
            List<Factura> facturaListOrphanCheck = empresa.getFacturaList();
            for (Factura facturaListOrphanCheckFactura : facturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empresa (" + empresa + ") cannot be destroyed since the Factura " + facturaListOrphanCheckFactura + " in its facturaList field has a non-nullable idEmpresa field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(empresa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empresa> findEmpresaEntities() {
        return findEmpresaEntities(true, -1, -1);
    }

    public List<Empresa> findEmpresaEntities(int maxResults, int firstResult) {
        return findEmpresaEntities(false, maxResults, firstResult);
    }

    private List<Empresa> findEmpresaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empresa.class));
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

    public Empresa findEmpresa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empresa.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpresaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empresa> rt = cq.from(Empresa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
