package paquete.bd.reclutadora;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ContratacionesJpaController {

    private EntityManagerFactory emf;

    public ContratacionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Contrataciones> findContratacionesEntities() {

        EntityManager em = getEntityManager();

        try {
            return em.createQuery(
                    "SELECT c FROM Contrataciones c",
                    Contrataciones.class
            ).getResultList();

        } finally {
            em.close();
        }
    }
}

