<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
<%--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">--%>
    <link href="css/style.css" rel="stylesheet" />
</head>
<body>
<h1>Quan ly san pham</h1>

<h2>Them san pham</h2>
<form action="servlet" method="post">
    <label for="id">ID</label>
    <input type="text" id="id" name="id">

    <label for="name">Ten san pham</label>
    <input type="text" id="name" name="name">

    <label for="price">Gia</label>
    <input type="text" id="price" name="price">

    <label for="desc">Mo ta</label>
    <input type="text" id="desc" name="desc">

    <label for="quantity">So luong</label>
    <input type="text" id="quantity" name="quantity">

    <button type="submit">Them san pham</button>
</form>

<h2>Danh sach san pham da them:</h2>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Ten san pham</th>
        <th>Gia</th>
        <th>Mo ta</th>
        <th>So luong</th>
        <th>Trang thai</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach item = "${products}" var= "p">
        <tr>
            <td>${p.getId()}</td>
            <td>${p.getProductName()}</td>
            <td>${p.getPrice()}</td>
            <td>${p.getDescription()}</td>
            <td>${p.getStock()}</td>
            <td>${p.getStatus() ? "Con hang" : "Het hang"} </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>--%>
</body>
</html>