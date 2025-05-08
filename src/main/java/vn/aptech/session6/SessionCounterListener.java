package vn.aptech.session6;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

@WebListener
public class SessionCounterListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        ServletContext context = event.getSession().getServletContext();

        int counter = context.getAttribute("counter") == null ? 0 : Integer.parseInt(context.getAttribute("counter").toString());
        if ("username".equals(event.getName())) {
            System.out.println("Current user " + event.getValue() + " logged in");
            counter++;
            context.setAttribute("counter", counter);
        }

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        ServletContext context = event.getSession().getServletContext();

        int counter = context.getAttribute("counter") == null ? 0 : Integer.parseInt(context.getAttribute("counter").toString());
        if ("username".equals(event.getName())) {
            System.out.println("Current user " + event.getValue() + " logout");
            counter--;
            context.setAttribute("counter", counter);
        }
    }
}
