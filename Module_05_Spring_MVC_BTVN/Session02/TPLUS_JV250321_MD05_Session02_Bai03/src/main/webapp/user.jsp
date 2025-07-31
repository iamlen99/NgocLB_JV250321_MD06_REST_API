<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 7/31/25
  Time: 6:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: #f5f7fa;
            margin: 0;
            padding: 40px;
        }

        h3 {
            text-align: center;
            color: #333;
        }

        table {
            width: 60%;
            margin: 20px auto;
            border-collapse: collapse;
            background: white;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px 15px;
            text-align: center;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        a {
            display: block;
            width: 200px;
            text-align: center;
            margin: 30px auto;
            padding: 10px 15px;
            background-color: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: 0.3s;
        }

        a:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
<% String message = (String) request.getAttribute("message"); %>
<% if (message != null) { %>
<p style="color: green; font-weight: bold;"><%= message %></p>
<% } %>
<h3>Danh sach nguoi da dang ky:</h3>
<table>
    <tr>
        <th>Username</th>
        <th>Email</th>
    </tr>
    <c:forEach var="u" items="${users}">
        <tr>
            <td>${u.username}</td>
            <td>${u.email}</td>
        </tr>
    </c:forEach>
</table>

<br>
<a href="index.jsp">Quay lại đăng ký</a>
</body>
</html>
