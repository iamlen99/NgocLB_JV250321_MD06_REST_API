<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/3/25
  Time: 3:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <style>
        html, body{
            height: 100vh;
            font-family: Arial, Helvetica, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            font-size: 20px;
        }

        form {
            padding: 20px;
            width: 300px;
            border: 3px solid gray;
            border-radius: 10px;
        }

        form input {
            width: 100%;
            height: 30px;
            border: 1px solid #ccc;
            border-radius: 5px;
            display: block;
            margin-bottom: 10px;
        }

        form button {
            background-color: cadetblue;
            padding: 5px 10px;
            border: none;
            color: white;
            cursor: pointer;
            border-radius: 5px;
            font-size: 16px;
        }

        form label {
            display: block;
        }

    </style>
</head>
<body>
    <h1>Dang nhap</h1>
    <form action="${pageContext.request.contextPath}/StudentServlet?action=login" method="post">
        <label>Username:</label>
        <input type="text" name="username" />
        <label>Password:</label>
        <input type="password" name="password" />
        <button type="submit">Login</button>
    </form>
    <c:if test="${error != null}">
        <p style="color: red">${error}</p>
    </c:if>
</body>
</html>
