package vn.aptech.session7;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
            req.getRequestDispatcher("/WEB-INF/session7/student.jsp").forward(req, resp);
        } else if (action.equals("add")) {
            Student student = new Student();
            req.setAttribute("student", student);
            req.getRequestDispatcher("/WEB-INF/session7/add_student.jsp").forward(req, resp);
        } else if (action.equals("create")) {

            String name = req.getParameter("name");
            String birthDay = req.getParameter("birthDay");
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");
            DateTimeFormatter birthDayFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            List<String> errors = new ArrayList<>();

            Student student = new Student();
            String studentIdStr = req.getParameter("studentId");
            if (studentIdStr != null && !studentIdStr.isEmpty()) {
                Long studentId = Long.parseLong(req.getParameter("studentId"));
                student.setId(studentId);
            }
            student.setName(name);
            if (birthDay != null && !birthDay.isEmpty()) {
                student.setBirthDay(LocalDate.parse(birthDay, birthDayFmt));
            }
            student.setEmail(email);
            student.setPhone(phone);

            if (name == null || name.isEmpty()) {
                errors.add("Name is required");
            }

            if (phone == null || phone.isEmpty()) {
                errors.add("Phone is required");
            }

            if (email == null || email.isEmpty()) {
                errors.add("Email is required");
            }
            Student checkExist = studentService.existsStudentByPhone(phone);
            if (checkExist != null) {
                errors.add("Phone is already use by " + checkExist.getName());
            }

            if (!errors.isEmpty()) {
                req.setAttribute("errors", errors);
                req.setAttribute("student", student);
                req.getRequestDispatcher("/WEB-INF/session7/add_student.jsp").forward(req, resp);
                return;
            }
            if (studentIdStr == null || studentIdStr.isEmpty()) {
                studentService.addStudent(student);
            } else {
                studentService.updateStudent(student);
            }

            resp.sendRedirect("/student-servlet-jpa");
        } else if (action.equals("delete")) {
            Long studentId = Long.parseLong(req.getParameter("studentId"));
            studentService.deleteStudent(studentId);
            resp.sendRedirect("/student-servlet-jpa");
        } else if (action.equals("preUpdate")) {
            Long studentId = Long.parseLong(req.getParameter("studentId"));
            Student student = studentService.getStudentById(studentId);
            if (student == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Student not found with id " + studentId);
                return;
            }

            req.setAttribute("student", student);
            req.getRequestDispatcher("/WEB-INF/session7/add_student.jsp").forward(req, resp);
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
