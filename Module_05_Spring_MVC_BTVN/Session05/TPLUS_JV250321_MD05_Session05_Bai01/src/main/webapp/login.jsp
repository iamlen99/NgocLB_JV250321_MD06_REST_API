<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 20px;
            box-sizing: border-box;
        }

        html,body {
            height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            background-color: lightgray;
            color: cadetblue;
        }

        h1 {
            font-size: 35px;
        }

        form {
            padding: 20px;
            width: 400px;
        }

        form input {
            display: block;
            width: 100%;
            height: 40px;
            border: 1px solid gray;
            border-radius: 5px;
            font-size: 16px;
            padding: 0 10px;
            margin-bottom: 15px;
        }

        form label {
            display: block;
        }

        form button {
            width: 100%;
            padding: 8px 0;
            color: white;
            background-color: cornflowerblue;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            font-size: 16px;
        }

    </style>
</head>
<body>
<h1>Login</h1>
<c:if test="${error != null}">
    <p style="color: red">${error}</p>
</c:if>
<form action="LoginServlet" method="post">
    <label>Username:</label>
    <input type="text" name="username"/>
    <label>Password:</label>
    <input type="password" name="password"/>
    <button type="submit">Login</button>
</form>
</body>
</html>