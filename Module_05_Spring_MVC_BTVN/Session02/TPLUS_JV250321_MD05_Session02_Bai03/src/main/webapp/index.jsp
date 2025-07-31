<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="title-bar">
        <div class="title">Webrelaration</div>
        <div class="close">×</div>
    </div>
    <form class="form" method="post" action="userServlet">
        <label>Username:</label>
        <input type="text" name="username" placeholder="Enter username">
        <label>Email:</label>
        <input type="email" name="email" placeholder="Enter email">
        <label>Password:</label>
        <input type="password" name="password" placeholder="Enter password">
        <button type="submit">Register</button>
    </form>
</div>

</body>
</html>