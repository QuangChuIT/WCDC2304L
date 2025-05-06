<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <c:url var="boostrapCCSURL" value="/asset/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${boostrapCCSURL}">
</head>
<body>
<div class="container">
    <div class="row justify-content-center align-content-center m-0">
        <c:url var="LoginURL" value="/login"/>
        <c:url var="LoginCookieURL" value="/login-cookie"/>
        <form action="${LoginCookieURL}" method="post">
            <div class="col-md-12 ">
                <div class="mb-3">
                    <h1>Login</h1>
                </div>
                <c:if test="${not empty requestScope.error}">
                    <div class="mb-3 text-danger">
                        ${requestScope.error}
                    </div>
                </c:if>

                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" class="form-control" id="username" name="username">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
            </div>
        </form>
    </div>
</div>
<c:url var="boostrapJSURL" value="/asset/js/bootstrap.min.js"/>
<script src="${boostrapJSURL}"></script>
</body>
</html>
