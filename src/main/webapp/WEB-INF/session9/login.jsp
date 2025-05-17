<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<c:url var="LoginURL" value="/user-login-servlet"/>
<form method="post" action="${LoginURL}">
    <table border="1">
        <tr>
            <td colspan="2" align="center">
                User Login
            </td>
        </tr>
        <c:if test="${not empty requestScope.error}">
            <tr>
                <td colspan="2" align="center" style="color: red">
                    ${requestScope.error}
                </td>
            </tr>
        </c:if>
        <tr>
            <td>Username: </td>
            <td><input type="text" name="username" placeholder="Enter username"/></td>
        </tr>
        <tr>
            <td>Password: </td>
            <td><input type="password" name="password" placeholder="Enter password"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Login">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
