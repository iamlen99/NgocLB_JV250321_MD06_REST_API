<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<form action="StudentServlet" method="post">
    <label>Name:</label>
    <input type="text" name="name" />
    <label>Age:</label>
    <input type="text" name="age" />
    <label>Address:</label>
    <input type="text" name="address" />
     <button type="submit">Add</button>
</form>
</body>
</html>