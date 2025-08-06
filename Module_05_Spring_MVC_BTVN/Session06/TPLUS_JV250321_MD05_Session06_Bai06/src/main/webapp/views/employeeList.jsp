<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/6/25
  Time: 11:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách nhân viên</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 30px;
            background-color: #f2f2f2;
        }

        h2 {
            text-align: center;
        }

        table {
            width: 70%;
            margin: auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 0 8px rgba(0,0,0,0.1);
            border-radius: 8px;
            overflow: hidden;
        }

        th, td {
            padding: 12px 15px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #007bff;
            color: #fff;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .add-link {
            display: block;
            text-align: center;
            margin-top: 20px;
        }

        .add-link a {
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }

        .add-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<c:if test="${message != null}">
    <p style="color: #28a745">${message}</p>
</c:if>
<h2>Danh sách nhân viên</h2>
<table>
    <thead>
    <tr>
        <th>STT</th>
        <th>Tên</th>
        <th>Email</th>
        <th>Vị trí</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="emp" items="${employees}" varStatus="loop">
        <tr>
            <td>${loop.index + 1}</td>
            <td>${emp.name}</td>
            <td>${emp.email}</td>
            <td>${emp.position}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="add-link">
    <a href="${pageContext.request.contextPath}/goToAddPage">➕ Thêm nhân viên mới</a>
</div>
</body>
</html>
