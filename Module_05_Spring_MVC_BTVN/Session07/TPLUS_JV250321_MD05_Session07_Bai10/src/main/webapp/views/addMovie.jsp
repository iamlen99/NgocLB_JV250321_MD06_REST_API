<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/7/25
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Thêm Phim</title>
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
<h2>➕ Thêm mới phim</h2>

<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>

<form:form modelAttribute="movie" method="post" action="/addMovie" enctype="multipart/form-data">
    <label>Tiêu đề:</label>
    <form:input path="title"/>
    <form:errors path="title" cssClass="error"/>

    <label>Đạo diễn:</label>
    <form:input path="director"/>
    <form:errors path="director" cssClass="error"/>

    <label>Ngày phát hành:</label>
    <form:input path="releaseDate" type="date"/>
    <form:errors path="releaseDate" cssClass="error"/>

    <label>Thể loại:</label>
    <form:input path="genre"/>
    <form:errors path="genre" cssClass="error"/>

    <label>Poster:
        <input type="file" name="file"/>
        <c:if test="${not empty posterError}">
            <p class="error">${posterError}</p>
        </c:if>
        <c:if test="${not empty uploadError}">
            <p class="error">${uploadError}</p>
        </c:if>
    </label>

    <button type="submit">Thêm phim</button>
    <button type="button" onclick="window.location.href='movieController'">Quay lại</button>
</form:form>
</body>
</html>



