package vn.aptech.session4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "HelloServlet", urlPatterns = {"/hello-servlet"})
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("names", req.getParameterValues("names"));
        req.setAttribute("name", req.getParameter("name"));
        req.getRequestDispatcher("/WEB-INF/session4/hello.jsp").forward(req, resp);
    }
}
