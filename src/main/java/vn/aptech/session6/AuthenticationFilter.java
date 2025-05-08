package vn.aptech.session6;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter",
        urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "typeLogin", value = "s")
        })
public class AuthenticationFilter implements Filter {
    private String typeLogin;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.typeLogin = filterConfig.getInitParameter("typeLogin");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("AuthenticationFilter invoke !!!!!");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();

        if (uri.contains("/login-cookie")
                || uri.contains("/asset")
                || uri.contains("/login")
                || uri.contains("/logout")
        ) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        boolean isAuthenticated = checkLogin(request);

        if (!isAuthenticated) {
            response.sendRedirect(typeLogin == "c" ? "/login-cookie" : "/login");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean checkLogin(HttpServletRequest request) {
        boolean isAuthenticated = false;
        if (this.typeLogin.equals("c")) {
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("username")) {
                        isAuthenticated = true;
                        break;
                    }
                }
            }
        } else {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("username") != null) {
                isAuthenticated = true;
            }
        }
        return isAuthenticated;

    }
}
