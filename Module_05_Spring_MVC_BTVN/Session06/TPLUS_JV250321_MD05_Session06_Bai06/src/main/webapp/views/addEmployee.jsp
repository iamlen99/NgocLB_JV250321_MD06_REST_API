<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/6/25
  Time: 12:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm nhân viên</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 30px;
            background-color: #f9f9f9;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            max-width: 400px;
            margin: auto;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        form div {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="email"] {
            width: 100%;
            padding: 8px 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            background-color: #28a745;
            color: white;
            padding: 10px 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
<h2 style="text-align: center">Thêm nhân viên mới</h2>
<c:if test="${message != null}">
    <p style="color: red">${message}</p>
</c:if>
<form action="${pageContext.request.contextPath}/addEmployee" method="post">
    <div>
        <label for="name">Tên nhân viên:</label>
        <input type="text" id="name" name="name" placeholder="Nhập tên" required>
    </div>
    <div>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" placeholder="Nhập email" required>
    </div>
    <div>
        <label for="position">Vị trí:</label>
        <input type="text" id="position" name="position" placeholder="Nhập vị trí" required>
    </div>
    <div>
        <button type="submit">Thêm mới</button>
    </div>
</form>
</body>
</html>
