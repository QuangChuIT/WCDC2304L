package vn.aptech.session9;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserService {

    private final EntityManagerFactory emf;
    private ValidatorFactory validatorFactory;

    public UserService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public User login(String username, String password) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT u FROM User u WHERE u.username = :username and u.password =:password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            return (User) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error login: " + e.getMessage());
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public CommonResult registration(User user) {
        EntityManager em = emf.createEntityManager();
        CommonResult result;
        List<String> errors = new ArrayList<>();
        try {
            Validator validator = validatorFactory.getValidator();

            Set<ConstraintViolation<User>> violations = validator.validate(user);

            if (!violations.isEmpty()) {
                errors = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
                result = new CommonResult(false, "Invalid request", errors);
                return result;
            }

            if (existsByEmail(user.getEmail())) {
                errors.add("Email already exists");
            }

            if (existsByUsername(user.getUsername())) {
                errors.add("Username already exists");
            }

            if (!errors.isEmpty()) {
                result = new CommonResult(false, "Invalid request", errors);
                return result;
            }

            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            result = new CommonResult(true, "Registration user successfully", errors);
            return result;
        } catch (Exception e) {
            em.getTransaction().rollback();
            errors.add(e.getMessage());
            result = new CommonResult(false, "Error registration user", errors);
            return result;
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

    public ValidatorFactory getValidatorFactory() {
        return validatorFactory;
    }

    public void setValidatorFactory(ValidatorFactory validatorFactory) {
        this.validatorFactory = validatorFactory;
    }
}
