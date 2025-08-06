<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/6/25
  Time: 2:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <style>
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px auto;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
<c:if test="${success != null}">
    <p style="color: green">${success}</p>
</c:if>
<h2 style="text-align: center;">Danh sách người dùng</h2>

<table>
    <thead>
    <tr>
        <th>STT</th>
        <th>Tên người dùng</th>
        <th>Email</th>
        <th>Số điện thoại</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}" varStatus="loop">
        <tr>
            <td>${loop.index + 1}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${user.phoneNumber}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
