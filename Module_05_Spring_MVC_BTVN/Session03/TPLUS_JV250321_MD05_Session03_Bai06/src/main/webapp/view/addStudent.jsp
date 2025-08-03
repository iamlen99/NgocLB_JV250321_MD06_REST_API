<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        html, body{
            height: 100vh;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: sans-serif;
            font-size: 20px;
        }

        form {
            width: 300px;
            padding: 20px;
            border: 1px solid #ccc;
            background: #f9f9f9;
            border-radius: 5px;
        }

        form label, form input {
            display: block;
            width: 100%;
            margin-bottom: 10px;
        }

        form input {
            height: 30px;
            border: 1px solid #ccc;
            border-radius: 3px;
            font-size: 18px;
        }

        form button {
            padding: 5px 10px;
            font-size: 18px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<form action="${pageContext.request.contextPath}/StudentServlet?action=addStudent" method="post">
    <label>Name:</label>
    <input type="text" name="name" value= "${name != null ? name : ""}" required>
    <label>Age:</label>
    <input type="number" name="age" value= "${age != null ? age : ""}" required>
    <label>Address:</label>
    <input type="text" name="address" value= "${address != null ? address : ""}" required>
     <button type="submit">Add</button>
    <br>
    <c:if test="${error != null}">
        <h3 style="color: red">${message}</h3>
        <p>${error}</p>
    </c:if>
</form>
</body>
</html>