<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/6/25
  Time: 2:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
            background-color: #f2f2f2;
        }

        .form-container {
            max-width: 400px;
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin: auto;
        }

        .form-container h2 {
            text-align: center;
            margin-bottom: 25px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="email"],
        input[type="tel"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            width: 100%;
            padding: 12px;
            background-color: #4CAF50;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Thông tin Người dùng</h2>
    <form action="${pageContext.request.contextPath}/user/addUser" method="post">
        <c:if test="${usernameError != null}">
            <p style="color: red">${usernameError}</p>
        </c:if>
        <div class="form-group">
            <label for="username">Tên người dùng</label>
            <input type="text" id="username" name="username" value="${user.username}" placeholder="Nhập tên người dùng" >
        </div>
        <c:if test="${emailError != null}">
            <p style="color: red">${emailError}</p>
        </c:if>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="text" id="email" name="email" value="${user.email}" placeholder="Nhập email" >
        </div>
        <c:if test="${phoneError != null}">
            <p style="color: red">${phoneError}</p>
        </c:if>
        <div class="form-group">
            <label for="phoneNumber">Số điện thoại</label>
            <input type="text" id="phoneNumber" name="phoneNumber" value="${user.phoneNumber}" placeholder="Nhập số điện thoại" >
        </div>
        <button type="submit">Gửi</button>
    </form>
</div>
</body>
</html>
