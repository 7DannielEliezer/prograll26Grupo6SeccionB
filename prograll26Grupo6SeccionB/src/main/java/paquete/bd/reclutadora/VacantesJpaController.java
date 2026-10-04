/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete.bd.reclutadora;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class VacantesJpaController {

    private EntityManagerFactory emf;

    public VacantesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Vacantes> findVacantesEntities() {

        EntityManager em = getEntityManager();

        try {
            return em.createQuery(
                    "SELECT v FROM Vacantes v",
                    Vacantes.class
            ).getResultList();

        } finally {
            em.close();
        }
    }
}
