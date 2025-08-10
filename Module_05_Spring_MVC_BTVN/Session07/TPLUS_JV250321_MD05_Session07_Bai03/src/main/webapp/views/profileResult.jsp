<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/8/25
  Time: 11:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
    </style>
</head>
<body>
<h2>Thông tin người dùng:</h2>
<p><strong>Tên: </strong> ${userProfile.username}</p>
<img src="${imgUrl}" alt="Ảnh chân dung" width="300"/>
<p>Ảnh đại diện</p>
</body>
</html>
