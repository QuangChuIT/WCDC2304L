package vn.aptech.session4;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ProductServlet", urlPatterns = {"/product-servlet"})
public class ProductServlet extends HttpServlet {

    private List<String> categories;
    private List<Product> products;

    @Override
    public void init(ServletConfig config) throws ServletException {
        categories = new ArrayList<>();
        categories.add("Phone");
        categories.add("Clothes");
        categories.add("Electronics");
        categories.add("Shoes");

        products = new ArrayList<>();

        products.add(new Product(100L, "Iphone 16", "Phone", new BigDecimal("1500")));
        products.add(new Product(101L, "Jeans Slight", "Clothes", new BigDecimal("20")));
        products.add(new Product(102L, "Fan", "Electronics", new BigDecimal("50")));
        products.add(new Product(103L, "Vans", "Shoes", new BigDecimal("30")));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category = req.getParameter("category");
        if (category != null && !category.isEmpty()) {
            List<Product> pros = this.products.stream().filter(it -> it.getCategory().equals(category)).collect(Collectors.toList());
            req.setAttribute("products", pros);
        } else {
            req.setAttribute("products", products);
        }
        req.setAttribute("categories", categories);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/WEB-INF/session4/product-list.jsp").forward(req, resp);
    }
}
