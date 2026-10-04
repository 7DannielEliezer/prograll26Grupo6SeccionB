/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete.bd.reclutadora;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EvaluacionesJpaController {

    private EntityManagerFactory emf;

    public EvaluacionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Evaluaciones> findEvaluacionesEntities() {

        EntityManager em = getEntityManager();

        try {
            return em.createQuery(
                    "SELECT e FROM Evaluaciones e",
                    Evaluaciones.class
            ).getResultList();

        } finally {
            em.close();
        }
    }
}

