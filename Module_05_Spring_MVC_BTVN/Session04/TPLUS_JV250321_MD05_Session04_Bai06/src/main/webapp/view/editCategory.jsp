<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/4/25
  Time: 3:25 PM
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

        form input[type=text],
        form input[type=number]{
            display: block;
            width: 100%;
            height: 40px;
            padding: 5px;
            border: 1px solid gray;
            border-radius: 5px;
            font-size: 16px;
            margin-bottom: 10px;
        }

        form label:nth-child(1),
        form label:nth-child(2),
        form label:nth-child(3) {
            display: block;
        }

        .status {
            font-size: 16px;
        }

        form button {
            padding: 2px 5px;
            border: none;
            border-radius: 5px;
            background-color: cornflowerblue;
            color: white;
            font-size: 20px;
            margin-top: 10px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<h2>Sua danh muc</h2>
<form action="${pageContext.request.contextPath}/EditCategoryController?action=updateCategory" method="post">
    <label>Id:</label>
    <input type="number" name="id" value="${category.id}" readonly/>
    <label>Catename:</label>
    <input type="text" name="cateName" placeholder="Nhap ten danh muc" value="${category.cateName}" required/>
    <label>Description:</label>
    <input type="text" name="description" value="${category.description}" placeholder="Nhap mo ta" required/>
    <label>Status:</label>
    <label class="status">Active</label>
    <input type="radio" name="status" value="true" ${category.status ? "checked" : ""}/>
    <label class="status">Deactive</label>
    <input type="radio" name="status" value="false" ${category.status ? "" : "checked"}/><br>
    <button type="submit">Cap nhat</button>
    <c:if test="${error != null}">
        <p style="color: red">${error}</p>
    </c:if>
</form>
</body>
</html>
