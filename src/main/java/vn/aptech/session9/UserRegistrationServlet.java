package vn.aptech.session9;

import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.io.IOException;
import java.util.Set;

@WebServlet(name = "UserRegistrationServlet", urlPatterns = {"/user/registration"})
public class UserRegistrationServlet extends HttpServlet {
    private UserService userService;
    private ValidatorFactory validatorFactory;

    @Override
    public void init(ServletConfig config) throws ServletException {
        EntityManagerFactory emf = (EntityManagerFactory) config.getServletContext().getAttribute("emf");
        this.userService = new UserService(emf);
        validatorFactory = Validation.buildDefaultValidatorFactory();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/session9/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) {
            req.setAttribute("errors", violations);
            req.getRequestDispatcher("/WEB-INF/session9/registration.jsp").forward(req, resp);
            return;
        }

        userService.registration(user);
    }
}
