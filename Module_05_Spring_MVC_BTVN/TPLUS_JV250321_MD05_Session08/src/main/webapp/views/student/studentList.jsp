<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/10/25
  Time: 10:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách sinh viên</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f9fc;
            margin: 20px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        thead {
            background-color: #4CAF50;
            color: white;
        }

        th, td {
            padding: 10px 15px;
            border: 1px solid #ddd;
            text-align: center;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #e6f7ff;
        }

        a.action-btn {
            padding: 5px 10px;
            text-decoration: none;
            color: white;
            border-radius: 4px;
            margin: 0 3px;
        }

        a.action-btn:hover {
            opacity: 0.85;
        }

        a.action-btn.delete {
            background-color: #e74c3c;
        }

        a.action-btn:not(.delete) {
            background-color: #3498db;
        }

        div[style*="text-align: right"] a {
            background-color: #2ecc71;
            padding: 6px 12px;
            border-radius: 4px;
            color: white;
            text-decoration: none;
        }

        div[style*="text-align: right"] a:hover {
            background-color: #27ae60;
        }
    </style>
</head>
<body>
<c:if test="${not empty message}">
    <p style="color: #2ecc71">${message}</p>
</c:if>
<h2>Danh sách sinh viên</h2>

<div style="text-align: right; margin-bottom: 10px;">
    <a href="${pageContext.request.contextPath}/studentController/goToAddPage">Thêm sinh viên</a>
</div>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Họ tên</th>
        <th>Email</th>
        <th>Ngày sinh</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="student" items="${studentList}">
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.email}</td>
            <td>${student.dob}</td>
            <td>
                <a class="action-btn" href="${pageContext.request.contextPath}/studentController/goToUpdatePage?id=${student.id}">Sửa</a>
                <a class="action-btn delete"
                   href="${pageContext.request.contextPath}/studentController/deleteStudent/${student.id}"
                   onclick="return confirm('Bạn có chắc chắn muốn xóa sinh viên này không?');">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
