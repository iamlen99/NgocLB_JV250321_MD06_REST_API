<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/10/25
  Time: 12:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background-color: #f0f2f5;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        form {
            width: 500px;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 8px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="date"] {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        button {
            margin-top: 20px;
            padding: 10px 16px;
            background-color: #28a745;
            border: none;
            color: white;
            border-radius: 6px;
            cursor: pointer;
        }

        button:hover {
            background-color: #218838;
        }

        .error {
            color: red;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
<h2>➕ Thay đổi thông tin sinh viên</h2>

<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>

<form:form modelAttribute="student" method="post" action="updateStudent">
    <form:hidden path="id"/>
    <label>Tên sinh viên:</label>
    <form:input path="name"/>
    <form:errors path="name" cssClass="error"/>

    <label>Email:</label>
    <form:input path="email"/>
    <form:errors path="email" cssClass="error"/>

    <label>Ngày sinh:</label>
    <form:input path="dob" type="date"/>
    <form:errors path="dob" cssClass="error"/>
    <br>
    <button type="submit">Cập nhật</button>
    <button type="button" onclick="window.location.href='/studentController'">Quay lại</button>
</form:form>
</body>
</html>
