package vn.aptech.session5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "LoginServletCookie", urlPatterns = {"/login-cookie"})
public class LoginServletCookie extends HttpServlet {
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
            Cookie cookie = new Cookie("username", username);
            cookie.setPath("/");
            cookie.setMaxAge(60);
            cookie.setHttpOnly(true);
            resp.addCookie(cookie);
            resp.sendRedirect("/product-servlet");
        } else {
            req.setAttribute("error", "Invalid username or password");
            req.getRequestDispatcher("/WEB-INF/session5/login.jsp").forward(req, resp);
        }
    }
}
