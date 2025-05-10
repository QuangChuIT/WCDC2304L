<%--
    Document   : add_student
    Created on : May 10, 2025, 9:11:02 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Student Page</title>
    </head>
    <body>
        <c:url var="CreateStudentURL" value="/student-servlet-jpa?action=create"/>
        <form action="${CreateStudentURL}" method="post">
            <table>
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="name" placeholder="enter student name"></td>
                </tr>
                <tr>
                    <td>BirthDay</td>
                    <td><input type="date" name="birthDay"></td>
                </tr>
                <tr>
                    <td>Phone</td>
                    <td><input type="text" name="phone" placeholder="enter student phone"></td>
                </tr>

                <tr>
                    <td>Email</td>
                    <td><input type="email" name="email" placeholder="enter student email"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Add"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>