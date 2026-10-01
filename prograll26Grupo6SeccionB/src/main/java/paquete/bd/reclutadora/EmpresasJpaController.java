
package paquete.bd.reclutadora;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EmpresasJpaController {

    private EntityManagerFactory emf;

    public EmpresasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // Crear empresa
    public void create(Empresas empresa) {
        EntityManager em = null;

        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            em.persist(empresa);

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw e;

        } finally {

            if (em != null) {
                em.close();
            }
        }
    }

    // Buscar todas las empresas
    public List<Empresas> findEmpresasEntities() {

        EntityManager em = emf.createEntityManager();

        try {

            return em.createQuery(
                    "SELECT e FROM Empresas e",
                    Empresas.class
            ).getResultList();

        } finally {

            em.close();
        }
    }

    // Buscar una empresa por ID
    public Empresas findEmpresas(int id) {

        EntityManager em = emf.createEntityManager();

        try {

            return em.find(Empresas.class, id);

        } finally {

            em.close();
        }
    }

    // Actualizar empresa
    public void edit(Empresas empresa) {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            em.merge(empresa);

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw e;

        } finally {

            em.close();
        }
    }

    // Eliminar empresa
    public void destroy(int id) {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            Empresas empresa = em.find(Empresas.class, id);

            if (empresa != null) {
                em.remove(empresa);
            }

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw e;

        } finally {

            em.close();
        }  
        
    }
}

