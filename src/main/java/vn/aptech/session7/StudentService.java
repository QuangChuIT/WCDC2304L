package vn.aptech.session7;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private EntityManagerFactory emf;

    public StudentService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Student> getStudents() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("select s from Student s");
            List<Student> students = (List<Student>) query.getResultList();
            return students;
        } catch (Exception e) {
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    public void addStudent(Student student) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error add student: " + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void updateStudent(Student student) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error update student: " + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void deleteStudent(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Student student = em.find(Student.class, id);
            if (student != null) {
                em.getTransaction().begin();
                em.remove(student);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println("Error delete student: " + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Student getStudentById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Student.class, id);
        } catch (Exception e) {
            System.out.println("Error get student: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public Student existsStudentByPhone(String phone) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("select s from Student s where s.phone = :phone");
            query.setParameter("phone", phone);

            return (Student) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error get student by phone: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }

    }
}
