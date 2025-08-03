<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/2/25
  Time: 8:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
            border: 1px solid #ddd;
            padding: 8px;
        }

        th, td:nth-child(odd) {
            text-align: center;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        table a {
            text-decoration: none;
            padding: 2px 5px;
            background-color: cornflowerblue;
            color: white;
            border: none;
            border-radius: 4px;
        }

    </style>
</head>
<body>
<h1>Danh sach sinh vien</h1>
<table>
    <thead>
    <tr>
        <th>STT</th>
        <th>Ten</th>
        <th>Tuoi</th>
        <th>Dia chi</th>
        <th>Action</th>
    </thead>
    </tr>
    <tbody>
    <c:forEach items="${students}" var="s" varStatus="loop">
        <tr>
            <td>${loop.index + 1}</td>
            <td>${s.name}</td>
            <td>${s.age}</td>
            <td>${s.address}</td>
            <td>
                <a href="StudentServlet?action=Update&idx=${loop.index}">Update</a>
                <a href="StudentServlet?action=deleteStudent&idx=${loop.index}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<a href="${pageContext.request.contextPath}/view/addStudent.jsp">Them sinh vien</a>
<a href="login.jsp">Dang xuat</a>
</body>
</html>
