package vn.aptech.session9;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class UserService {

    private final EntityManagerFactory emf;

    public UserService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void registration(User user) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error registration user", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
