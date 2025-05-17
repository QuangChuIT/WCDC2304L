<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Dashboard Page</title>
</head>
<body>
<c:if test="${not empty param.error}">
    <span style="color: red">${param.error}</span>
</c:if>
<table border="1">
    <tr>
        <th>Booking ID</th>
        <th>Destination</th>
        <th>Departure Date</th>
        <th>Price</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="b" items="${requestScope.bookings}">
        <tr>
            <td>${b.id}</td>
            <td>${b.destination}</td>
            <td>${b.departureDate}</td>
            <td>${b.price}</td>
            <td>${b.status}</td>
            <td>
                <c:if test="${b.status == 'Pending'}">
                    <c:url var="CancelBookingURL" value="/bookings?action=cancel&id=${b.id}"/>
                    <a href="${CancelBookingURL}">Cancel</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
