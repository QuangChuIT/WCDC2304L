package vn.aptech.session7;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "StudentServlet1", urlPatterns = {"/student-servlet-jpa"})
public class StudentServlet extends HttpServlet {
    private EntityManagerFactory emf;
    private StudentService studentService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();

        this.emf = (EntityManagerFactory) context.getAttribute("emf");

        studentService = new StudentService(emf);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            List<Student> students = studentService.getStudents();
            req.setAttribute("students", students);
            req.getRequestDispatcher("/WEB-INF/session7/student.jsp").forward(req,resp);
        } else if (action.equals("add")) {
            req.getRequestDispatcher("/WEB-INF/session7/add_student.jsp").forward(req,resp);
        } else if (action.equals("create")) {
            String name = req.getParameter("name");
            String birthDay = req.getParameter("birthDay");
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");
            DateTimeFormatter birthDayFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            Student student = new Student();
            student.setName(name);
            student.setBirthDay(LocalDate.parse(birthDay, birthDayFmt));
            student.setEmail(email);
            student.setPhone(phone);
            studentService.addStudent(student);
            resp.sendRedirect("/student-servlet-jpa");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
