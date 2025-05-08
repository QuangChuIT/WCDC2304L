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
        <c:url var="searchProduct" value="/product-servlet"/>
        <form method="get" action="${searchProduct}">
            <select class="form-select" name="category">
                <option value="">Select category</option>
                <c:forEach var="c" items="${requestScope.categories}">
                    <option value="${c}">${c}</option>
                </c:forEach>
            </select>
            <button type="submit" class="btn btn-info">Search</button>
        </form>

        <table class="table table-bordered table-striped">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Category</th>
                <th>Price</th>
            </tr>
            <c:forEach var="p" items="${requestScope.products}">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.category}</td>
                    <td>${p.price}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<c:url var="boostrapJSURL" value="/asset/js/bootstrap.min.js"/>
<script src="${boostrapJSURL}"></script>
</body>
</html>
