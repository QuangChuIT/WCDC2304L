package vn.aptech.session7;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@WebListener
public class ApplicationContextListener implements ServletContextListener {
    private EntityManagerFactory emf;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("Session7PU");
        }
        sce.getServletContext().setAttribute("emf", emf);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (sce.getServletContext().getAttribute("emf") != null) {
            sce.getServletContext().removeAttribute("emf");
        }

        if (emf != null) {
            emf.close();
        }
    }
}
