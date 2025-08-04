<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/4/25
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
        table {
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 8px;
        }
    </style>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>STT</th>
        <th>Id</th>
        <th>CateName</th>
        <th>Description</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${categories}" var="c" varStatus="loop">
        <tr>
            <td>${loop.index + 1}</td>
            <td>${c.id}</td>
            <td>${c.cateName}</td>
            <td>${c.description}</td>
            <td>${c.status ? "Active" : "Deactive"}</td>
            <td>
                <a href="#">Edit</a>
                <a href="#">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
