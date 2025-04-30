<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:url var="submitUrl" value="/student-grade-evaluator"/>
<form action="${submitUrl}" method="post">
    <table border="1">
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" value="${requestScope.name}"></td>
        </tr>
        <tr>
            <td>Score:</td>
            <td><input type="number" min="0" name="score" value="${requestScope.score}"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Grade"></td>
            <td></td>
        </tr>
    </table>
</form>

<h1>Result:</h1>
<c:choose>
    <c:when test="${requestScope.score >= 90 && requestScope.score <=100}">
        <span>Excellent</span>
    </c:when>
    <c:when test="${requestScope.score >= 70 && requestScope.score <=89}">
        <span>Good</span>
    </c:when>
    <c:when test="${requestScope.score >= 50 && requestScope.score <=69}">
        <span>Average</span>
    </c:when>
    <c:otherwise>
        <span>Fail</span>
    </c:otherwise>
</c:choose>
</body>
</html>
