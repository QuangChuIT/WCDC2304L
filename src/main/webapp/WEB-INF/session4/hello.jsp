<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<c:set var="num1" value="3"/>
<c:set var="num2" value="4"/>
<c:choose>
    <c:when test="${num1 > num2}">
        <h1>${num1} is greater than ${num2}</h1>
    </c:when>
    <c:otherwise>
        <h1>${num1} is less than ${num2}</h1>
    </c:otherwise>
</c:choose>
<c:if test="${!empty param.name}">
    <h1>Hello ${param.name}</h1>
</c:if>
<%--<h1>Hello ${paramValues.names[0]}</h1>
<h1>Hello ${paramValues.names[1]}</h1>
<h1>Hello ${paramValues.names[2]}</h1>--%>
</body>
</html>
