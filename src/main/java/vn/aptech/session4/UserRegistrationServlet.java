package vn.aptech.session4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "UserRegistrationServlet", urlPatterns = "/user-registration-servlet")
public class UserRegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/session4/user-registration-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        boolean isValid = true;
        Map<String,String> errors = new HashMap<>();
        if (email == null || email.isEmpty()) {
            isValid = false;
            errors.put("email", "Email is required");
        }

        if (name == null || name.isEmpty()) {
            isValid = false;
            errors.put("name", "Name is required");
        }
        boolean checkPassword = true;
        if (password == null || password.isEmpty()) {
            checkPassword = false;
            isValid = false;
            errors.put("password", "Password is required");
        }

        if (confirmPassword == null || confirmPassword.isEmpty()) {
            checkPassword = false;
            isValid = false;
            errors.put("confirmPassword", "Confirm Password is required");
        }

        if (checkPassword && !password.equals(confirmPassword)) {
            errors.put("confirmPassword", "Confirm Password is not match");
            isValid = false;
        }

        if (isValid) {
            resp.sendRedirect("/home");
        } else {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/session4/user-registration-form.jsp").forward(req, resp);
        }

    }
}
