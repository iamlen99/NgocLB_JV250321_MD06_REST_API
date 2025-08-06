<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/6/25
  Time: 11:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <style>
        table{
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
        }
    </style>
</head>
<body>
<h1>Danh sach san pham</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Ten san pham</th>
        <th>Gia san pham</th>
        <th>Mo ta san pham</th>
        <th>Url hinh anh san pham</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${productList}" var="p">
        <tr>
            <td>${p.id}</td>
            <td>${p.productName}</td>
            <td>${p.price}</td>
            <td>${p.description}</td>
            <td>${p.imageUrl}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
