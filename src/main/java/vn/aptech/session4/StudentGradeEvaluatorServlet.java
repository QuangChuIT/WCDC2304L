package vn.aptech.session4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "StudentGradeEvaluatorServlet", urlPatterns = {"/student-grade-evaluator"})
public class StudentGradeEvaluatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/session4/student_grade_evaluator.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int score = Integer.parseInt(req.getParameter("score"));
        req.setAttribute("name", name);
        req.setAttribute("score", score);
        req.getRequestDispatcher("/WEB-INF/session4/student_grade_evaluator.jsp").forward(req, resp);
    }
}
