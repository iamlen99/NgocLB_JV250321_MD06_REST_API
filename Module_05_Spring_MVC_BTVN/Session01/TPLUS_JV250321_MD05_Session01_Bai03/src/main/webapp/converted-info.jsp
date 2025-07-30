<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 7/30/25
  Time: 4:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            background: #f5f5f5;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        #result-box {
            background: white;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            text-align: center;
        }

        .result-box h2 {
            margin: 15px 0;
        }
    </style>
</head>
<body>
<%
    Double rate = (Double) request.getAttribute("rate");
    Double usd = (Double) request.getAttribute("usd");
    Double vnd = (Double) request.getAttribute("vnd");
%>

<div id="result-box">
    <h2>Rate: <%= rate %>
    </h2>
    <h2>USD: <%= usd %> - USD</h2>
    <h2>VND: <%= vnd %> - VNĐ</h2>
</div>
<form action="index.jsp">
    <button type="submit">Quay lai</button>
</form>
</body>
</html>
