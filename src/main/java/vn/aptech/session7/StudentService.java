package vn.aptech.session7;

import jakarta.servlet.http.HttpServletResponse;

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
}
