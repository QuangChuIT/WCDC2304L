<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Booking a Tour</title>
</head>
<c:url var="BookingURL" value="bookings?action=book"/>
<body>
<form action="${BookingURL}" method="post">
    <table border="1">
        <tr>
            <td colspan="2" align="center">
                Book a Tour Package
            </td>
        </tr>
        <c:if test="${not empty requestScope.errors}">
            <tr>
                <td colspan="2" align="center" style="color: red">
                    <c:forEach var="e" items="${requestScope.errors}">
                        <span>${e}</span><br>
                    </c:forEach>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>Destination: </td>
            <td><input type="text" value="${requestScope.booking.destination}" name="destination" placeholder="Enter destination"></td>
        </tr>
        <tr>
            <td>Departure Date: </td>
            <td><input type="date" value="${requestScope.booking.departureDate}" name="departureDate" placeholder="Enter Departure Date"></td>
        </tr>
        <tr>
            <td>Price: </td>
            <td><input type="number" min="100" value="${requestScope.booking.price}" name="price" placeholder="Enter price"></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Booking">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
