package vn.aptech.session6;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("AuthenticationFilter invoke !!!!!");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();

        if (uri.contains("/login-cookie") || uri.contains("/asset")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        boolean isAuthenticated = false;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    isAuthenticated = true;
                    break;
                }
            }
        }

        if (!isAuthenticated) {
            response.sendRedirect("/login-cookie");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
