package vn.aptech.session6;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

import java.io.IOException;

@WebFilter(filterName = "MyFilter",
        urlPatterns = {"/product-servlet"},
        initParams = {
                @WebInitParam(name = "typeTime", value = "s")
        })
public class MyFilter implements Filter {

    private String typeTime;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.typeTime = filterConfig.getInitParameter("typeTime");

        System.out.println("MyFilter init done!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter doFilter invoke!");
        long startTime = System.currentTimeMillis();

        filterChain.doFilter(servletRequest, servletResponse);

        long endTime = System.currentTimeMillis();

        long totalTime = endTime - startTime;
        String unit;
        if (typeTime.equals("s")){
            totalTime = totalTime / 1000;
            unit = "s";
        } else {
            unit = "ms";
        }

        System.out.println("Total time access to ProductServlet is: " + totalTime  +  "(" + unit + ")");
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter destroy done!");
    }
}
