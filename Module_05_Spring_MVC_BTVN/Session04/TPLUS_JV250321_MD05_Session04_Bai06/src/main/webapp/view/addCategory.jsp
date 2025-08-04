<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/4/25
  Time: 2:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <style>
        body, html {
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 20px;
            background-color: #fff;
        }

        form {
            width: 400px;
            padding: 20px;
            border: 1px solid gray;
            border-radius: 10px;
            background-color: peachpuff;
        }

        form input {
            display: block;
            width: 100%;
            height: 40px;
            padding: 5px;
            border: 1px solid gray;
            border-radius: 5px;
            font-size: 16px;
            margin-bottom: 10px;
        }

        form label {
            display: block;
        }

        form button {
            padding: 2px 5px;
            border: none;
            border-radius: 5px;
            background-color: cornflowerblue;
            color: white;
            font-size: 16px;
        }
    </style>
</head>
<body>
<h2>Them danh muc</h2>
<form action="${pageContext.request.contextPath}/AddCategoryController?action=addCategory" method="post">
    <label>Catename:</label>
    <input type="text" name="cateName" placeholder="Nhap ten danh muc" value="${cateName}" required/>
    <label>Description:</label>
    <input type="text" name="description" value="${description}" placeholder="Nhap mo ta" required/>
    <button type="submit">Them</button>
</form>
<c:if test="${error != null}">
    <p style="color: red">${error}</p>
</c:if>
</body>
</html>
