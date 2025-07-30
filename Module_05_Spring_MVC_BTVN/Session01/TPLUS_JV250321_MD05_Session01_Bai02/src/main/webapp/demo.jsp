<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 7/30/25
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Data</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-size: 20px;
            line-height: 1.7;
        }

        body {
            margin-top: 30px;
            margin-left: 30px;
        }

        .user-infor {
            border: 3px solid black;
            padding: 20px;
            /*text-align: center;*/
            width: 300px;
            height: 300px;
        }

        h1 {
            text-align: center;
            font-size: 35px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<div class="user-infor">
    <h1><%= "User Data" %>
    </h1>
    <p><strong>ID: </strong>${id}</p>
    <p><strong>Name: </strong>${name}</p>
    <p><strong>Email: </strong>${email}</p>
    <p><strong>Age: </strong>${age}</p>
</div>

</body>
</html>
