<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/7/25
  Time: 4:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit movie</title>
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
            box-shadow: 0 0 8px rgba(0,0,0,0.1);
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
            background-color: #007bff;
            border: none;
            color: white;
            border-radius: 6px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        .error {
            color: red;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
<h2>✏️ Edit movie info</h2>

<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>

<form:form modelAttribute="category" method="post" action="/updateCategory">
    <form:hidden path="id"/>

    <label>Category name:</label>
    <form:input path="categoryName"/>
    <form:errors path="categoryName" cssClass="error"/>

    <label>Description:</label>
    <form:input path="description"/>
    <form:errors path="description" cssClass="error"/>

    <label>Status:</label><br/>
    <form:radiobutton path="status" value="true"/> Active
    <form:radiobutton path="status" value="false"/> Deactive
    <br/>
    <form:errors path="status" cssClass="error"/>
    <button type="submit">Edit movie</button>
    <button type="button" onclick="window.location.href = '/categoryController'" >Back</button>
</form:form>
</body>
</html>

