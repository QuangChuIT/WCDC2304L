package vn.aptech.session5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/session5/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            req.setAttribute("error", "Invalid username or password");
            req.getRequestDispatcher("/WEB-INF/session5/login.jsp").forward(req, resp);
            return;
        }

        if (username.equals("admin") && password.equals("123")) {
            HttpSession session = req.getSession(true);
            session.setAttribute("username", username);
            resp.sendRedirect("/home");
        } else {
            req.setAttribute("error", "Invalid username or password");
            req.getRequestDispatcher("/WEB-INF/session5/login.jsp").forward(req, resp);
        }
    }
}
