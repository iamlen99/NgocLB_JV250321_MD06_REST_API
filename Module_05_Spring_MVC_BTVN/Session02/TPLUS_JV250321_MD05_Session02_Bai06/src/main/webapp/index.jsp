<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="login-box">
    <h1>Đăng Nhập</h1>
    <form id="loginForm" action="servlet" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">Đăng Nhập</button>
    </form>
    <c:if test="${username == 'admin' && password == '123456'}">
        <p class="success">Dang nhap thanh cong</p>
        <p class="greeting">Xin chao Admin<b id="name"></b> !</p>
    </c:if>

    <c:if test="${ password != '123456' && password != null && username != 'admin' && username!= null}">
        <p class="error">Dang nhap that bai</p>
        <p class="greeting">Username hoac mat khau sai.<b id="name"></b> !</p>
    </c:if>

</div>
</body>
</html>