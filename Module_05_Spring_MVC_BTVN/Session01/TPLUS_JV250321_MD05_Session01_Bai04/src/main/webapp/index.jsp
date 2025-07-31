<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="login-container">
    <h2>Đăng Nhập</h2>
    <form action="servlet" method="post">
        <% String error = (String) request.getAttribute("error"); %>
        <% if (error != null) { %>
        <p style="color: red; font-weight: bold;"><%= error %></p>
        <% } %>
        <input type="text" name="username" placeholder="Tên đăng nhập" required>
        <input type="password" name="password" placeholder="Mật khẩu" required>
        <button type="submit">Đăng Nhập</button>
    </form>
</div>
</body>
</html>