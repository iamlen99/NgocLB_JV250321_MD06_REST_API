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

        table a {
            border: none;
            padding: 2px 5px;
            border-radius: 4px;
            text-decoration: none;
            color: #fff;
            background-color: cornflowerblue;
        }

        table a:nth-child(2) {
            background-color: rosybrown;
            margin-left: 5px;
        }
    </style>
</head>
<body>
<h1>Danh sach danh muc</h1>
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
                <a href="${pageContext.request.contextPath}/EditCategoryController?action=updatePage&id=${c.id}">Edit</a>
                <a href="${pageContext.request.contextPath}/DeleteCategoryController?id=${c.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<a href="${pageContext.request.contextPath}/AddCategoryController?action=addPage">Them moi danh muc</a>
</body>
</html>
