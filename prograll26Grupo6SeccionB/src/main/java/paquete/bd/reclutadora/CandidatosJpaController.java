package paquete.bd.reclutadora;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CandidatosJpaController {

    private EntityManagerFactory emf;

    public CandidatosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Candidatos> findCandidatosEntities() {

        EntityManager em = getEntityManager();

        try {
            return em.createQuery(
                    "SELECT c FROM Candidatos c",
                    Candidatos.class
            ).getResultList();

        } finally {
            em.close();
        }
    }
}