package vn.aptech.session9;

import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "UserLoginServlet", urlPatterns = {"/user-login-servlet"})
public class UserLoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("vao day");
        EntityManagerFactory emf = (EntityManagerFactory) config.getServletContext().getAttribute("emf");
        this.userService = new UserService(emf);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/session9/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userService.login(username, password);

        if (user == null) {
            req.setAttribute("error", "Invalid username or password");
            req.getRequestDispatcher("/WEB-INF/session9/login.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("user", user);
        resp.sendRedirect("/bookings");
    }
}
