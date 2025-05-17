package vn.aptech.session9;

import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "BookingServlet", urlPatterns = {"/bookings"})
public class BookingServlet extends HttpServlet {
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private BookingService bookingService;
    private ValidatorFactory validatorFactory;

    @Override
    public void init(ServletConfig config) throws ServletException {
        EntityManagerFactory emf = (EntityManagerFactory) config.getServletContext().getAttribute("emf");
        validatorFactory = Validation.buildDefaultValidatorFactory();
        this.bookingService = new BookingService(emf);
        bookingService.setValidatorFactory(validatorFactory);
    }


    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            User user;
            HttpSession session = req.getSession(false);
            if (session == null) {
                resp.sendRedirect("/user-login-servlet");
                return;
            } else {
                user = (User) session.getAttribute("user");
                if (user == null) {
                    resp.sendRedirect("/user-login-servlet");
                    return;
                }
            }
            List<Booking> bookings = bookingService.getBookings(user);
            req.setAttribute("bookings", bookings);
            req.getRequestDispatcher("/WEB-INF/session9/dashboard.jsp").forward(req, resp);
        } else if (action.equals("book-form")) {
            Booking booking = new Booking();
            req.setAttribute("booking", booking);
            req.getRequestDispatcher("/WEB-INF/session9/booking.jsp").forward(req, resp);
        } else if (action.equals("book")) {
            String destination = req.getParameter("destination");
            String departureDateStr = req.getParameter("departureDate");
            String priceStr = req.getParameter("price");
            Double price = null;
            if (priceStr != null && !priceStr.isEmpty()) {
                price = Double.valueOf(req.getParameter("price"));
            }


            HttpSession session = req.getSession(false);
            if (session == null) {
                resp.sendRedirect("/user-login-servlet");
                return;
            } else {
                User user = (User) session.getAttribute("user");
                if (user == null) {
                    resp.sendRedirect("/user-login-servlet");
                    return;
                }
            }
            User user = (User) session.getAttribute("user");

            Booking booking = new Booking();
            booking.setDestination(destination);
            booking.setUser(user);
            booking.setStatus(BookingStatus.Pending);
            booking.setPrice(price);

            if (departureDateStr != null && !departureDateStr.isEmpty()) {
                LocalDate departureDate = LocalDate.parse(departureDateStr, FORMATTER);
                booking.setDepartureDate(departureDate);
            }

            CommonResult result = bookingService.booking(booking);

            if (!result.isSuccess()) {
                req.setAttribute("booking", booking);
                req.setAttribute("errors", result.getErrors());
                req.getRequestDispatcher("/WEB-INF/session9/booking.jsp").forward(req, resp);
                return;
            }

            resp.sendRedirect("/bookings");
        } else if (action.equals("cancel")) {
            Long id = Long.parseLong(req.getParameter("id"));
            CommonResult result = bookingService.cancelBooking(id);
            if (!result.isSuccess()) {
                resp.sendRedirect("/bookings?error=" + result.getErrors());
            } else {
                resp.sendRedirect("/bookings");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

}
