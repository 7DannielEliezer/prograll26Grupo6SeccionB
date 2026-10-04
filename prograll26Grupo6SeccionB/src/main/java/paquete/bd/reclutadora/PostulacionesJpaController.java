/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete.bd.reclutadora;



import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PostulacionesJpaController {

    private EntityManagerFactory emf;

    public PostulacionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Postulaciones> findPostulacionesEntities() {

        EntityManager em = getEntityManager();

        try {
            return em.createQuery(
                    "SELECT p FROM Postulaciones p",
                    Postulaciones.class
            ).getResultList();

        } finally {
            em.close();
        }
    }
}

