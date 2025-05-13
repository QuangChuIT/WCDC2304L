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
    <div class="row justify-content-center align-content-center d-flex">
        <table class="table table-border">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>BirthDay</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="s" items="${requestScope.students}">
                <tr>
                    <td>${s.id}</td>
                    <td>${s.name}</td>
                    <td>${s.birthDay}</td>
                    <td>${s.email}</td>
                    <td>${s.phone}</td>
                    <td>
                        <c:url var="DeleteStudentURL" value="/student-servlet-jpa">
                            <c:param name="studentId" value="${s.id}"/>
                            <c:param name="action" value="delete"/>
                        </c:url>
                        <a href="${DeleteStudentURL}" onclick="return confirm('Are you sure to delete this student')" class="btn btn-sm btn-danger">Delete</a>

                        <c:url var="PreUpdateStudentURL" value="/student-servlet-jpa">
                            <c:param name="studentId" value="${s.id}"/>
                            <c:param name="action" value="preUpdate"/>
                        </c:url>
                        <a href="${PreUpdateStudentURL}" class="btn btn-sm btn-info">Edit</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <c:url var="addStudent" value="/student-servlet-jpa?action=add"/>
        <a href="${addStudent}">Add new </a>
    </div>
</div>
<c:url var="boostrapJSURL" value="/asset/js/bootstrap.min.js"/>
<script src="${boostrapJSURL}"></script>
</body>
</html>
