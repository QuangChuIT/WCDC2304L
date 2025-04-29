package vn.aptech.session4;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StudentServlet", urlPatterns = {"/student-servlet"})
public class StudentServlet extends HttpServlet {
    private List<Student> students;

    @Override
    public void init(ServletConfig config) throws ServletException {
        students = new ArrayList<>();
        students.add(new Student("ST01", "Hai", 19));
        students.add(new Student("ST02", "Nam", 20));
        students.add(new Student("ST03", "Thanh", 21));
        students.add(new Student("ST04", "Tuan", 22));
        students.add(new Student("ST05", "Tien", 23));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", students);
        req.getRequestDispatcher("/WEB-INF/session4/students.jsp").forward(req, resp);
    }
}
