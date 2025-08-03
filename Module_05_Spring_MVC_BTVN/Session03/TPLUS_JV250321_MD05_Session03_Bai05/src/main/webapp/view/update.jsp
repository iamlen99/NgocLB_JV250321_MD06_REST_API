<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/3/25
  Time: 11:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
<form action="StudentServlet?action=UpdateStudent" method="post">
    <input type="hidden" name="idx" value="${idx}">
    <label>Name:</label>
    <input type="text" name="name" value= "${student.name}" required>
    <label>Age:</label>
    <input type="number" name="age" value= "${student.age}" required>
    <label>Address:</label>
    <input type="text" name="address" value= "${student.address}" required>
    <button type="submit">Update</button>
    <c:if test="${error != null}">
        <h3 style="color: red">${message}</h3>
        <p>${error}</p>
    </c:if>

</form>
</body>
</html>
