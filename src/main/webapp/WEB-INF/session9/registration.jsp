<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<c:url var="RegisterURL" value="/user-registration"/>
<form action="${RegisterURL}" method="post">
    <table border="1">
        <tr>
            <td colspan="2" align="center">User Registration</td>
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
            <td>
                <label>Username: </label>
            </td>
            <td>
                <input type="text" name="username" value="${requestScope.user.username}" placeholder="Enter username">
            </td>
        </tr>
        <tr>
            <td>
                <label>Email: </label>
            </td>
            <td>
                <input type="text" name="email" value="${requestScope.user.email}" placeholder="Enter email">
            </td>
        </tr>
        <tr>
            <td>
                <label>Password: </label>
            </td>
            <td>
                <input type="password" name="password" value="${requestScope.user.password}" placeholder="Enter password">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="Register"></td>
        </tr>
    </table>
</form>

</body>
</html>
