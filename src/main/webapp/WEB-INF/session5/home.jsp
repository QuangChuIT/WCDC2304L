<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home Page</title>
    <c:url var="boostrapCCSURL" value="/asset/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${boostrapCCSURL}">
</head>
<body>
<div class="container">
    <div class="row justify-content-center align-content-center m-0">
       <%--<span> Welcome ${sessionScope.username}</span>--%>
        <span> Welcome ${cookie['username'].value}</span>
    </div>
    <h1>Total session active: ${applicationScope.counter}</h1>
    <c:url var="logoutURL" value="/logout"/>
    <a href="${logoutURL}">Logout</a>
</div>
<c:url var="boostrapJSURL" value="/asset/js/bootstrap.min.js"/>
<script src="${boostrapJSURL}"></script>
</body>
</html>
