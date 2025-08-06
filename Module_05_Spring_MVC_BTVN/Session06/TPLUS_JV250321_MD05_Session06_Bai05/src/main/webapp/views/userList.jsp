<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/6/25
  Time: 11:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sach nguoi dung</title>
</head>
<body>
<h2>Danh sách người dùng</h2>

<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>Tên</th>
        <th>Tuổi</th>
        <th>Ngày sinh</th>
        <th>Email</th>
        <th>SĐT</th>
    </tr>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <td>${user.birthday}</td>
            <td>${user.email}</td>
            <td>${user.phone}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
