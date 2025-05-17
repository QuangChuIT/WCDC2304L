package vn.aptech.session9;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookingService {
    private final EntityManagerFactory emf;
    private ValidatorFactory validatorFactory;

    public BookingService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public CommonResult booking(Booking booking) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Validator validator = validatorFactory.getValidator();

            Set<ConstraintViolation<Booking>> violations = validator.validate(booking);
            if (!violations.isEmpty()) {
                List<String> errors = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
                return new CommonResult(false, "Error booking", errors);
            }
            em.persist(booking);
            em.getTransaction().commit();
            return new CommonResult(true, "Booking Success", null);
        } catch (Exception e) {
            List<String> errors = List.of(e.getMessage());
            return new CommonResult(false, "Error booking", errors);
        } finally {
            em.close();
        }
    }

    public void setValidatorFactory(ValidatorFactory validatorFactory) {
        this.validatorFactory = validatorFactory;
    }

    public List<Booking> getBookings(User user) {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery("Select b from Booking b where b.user.id = :userid");
            query.setParameter("userid", user.getId());
            return (List<Booking>) query.getResultList();
        } catch (Exception e) {
            System.out.println("Error getting bookings: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    CommonResult cancelBooking(Long bookingId) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Booking booking = em.find(Booking.class, bookingId);
            booking.setStatus(BookingStatus.Canceled);
            em.merge(booking);
            em.getTransaction().commit();
            return new CommonResult(true, "Booking Canceled Success", null);
        } catch (Exception e) {
            return new CommonResult(true, "Booking Canceled Failure: " + e.getMessage(), null);
        }
    }
}
