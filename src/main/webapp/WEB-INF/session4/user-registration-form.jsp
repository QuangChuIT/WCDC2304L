<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Registration</title>
    <c:url var="boostrapCCSURL" value="/asset/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${boostrapCCSURL}">
</head>
<body>
<div class="container">
    <div class="row justify-content-center align-content-center m-0">
        <c:url var="submitRegistration" value="/user-registration-servlet"/>
        <form method="post" action="${submitRegistration}">
            <div class="mb-3">
                <label for="email" class="form-label">Email address</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Enter email">
                <p id="errorEmail" class="text-danger"> ${requestScope.errors['email']}</p>
            </div>
            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="Enter your name">
                <p id="errorName" class="text-danger"> ${requestScope.errors['name']}</p>
            </div>
            <div class="mb-3 ">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter password">
                <p id="errorPassword" class="text-danger"> ${requestScope.errors['password']}</p>
            </div>
            <div class="mb-3">
                <label for="confirmPassword" class="form-label">Confirm Password</label>
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Enter confirm password">
                <p id="errorConfPassword" class="text-danger"> ${requestScope.errors['confirmPassword']}</p>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>

    </div>
</div>
<c:url var="boostrapJSURL" value="/asset/js/bootstrap.min.js"/>
<script src="${boostrapJSURL}"></script>
</body>
</html>
