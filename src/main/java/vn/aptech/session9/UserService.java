package vn.aptech.session9;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

public class UserService {

    private final EntityManagerFactory emf;

    public UserService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public RegistrationResult registration(User user) {
        EntityManager em = emf.createEntityManager();

        // Check unique
        if (existsByUsername(user.getUsername())) {
            return new RegistrationResult(false, "Username already exists");
        }

        if (existsByEmail(user.getEmail())) {
            return new RegistrationResult(false, "Email already exists");
        }

        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return new RegistrationResult(true, "User registered successfully");
        } catch (Exception e) {
            em.getTransaction().rollback();
            return new RegistrationResult(false, "Registration failed: " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public boolean existsByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT u FROM User u WHERE u.email = :email");
            query.setParameter("email", email);
            return query.getSingleResult() != null;
        } catch (Exception e) {
            System.out.println("Error checking if user exists with email " + email);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return false;
    }

    public boolean existsByUsername(String username) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT u FROM User u WHERE u.username = :username");
            query.setParameter("username", username);
            return query.getSingleResult() != null;
        } catch (Exception e) {
            System.out.println("Error checking if user exists with username " + username);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return false;
    }
}
