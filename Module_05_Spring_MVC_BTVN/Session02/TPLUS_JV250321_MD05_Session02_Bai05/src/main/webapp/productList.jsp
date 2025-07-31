<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 7/31/25
  Time: 5:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    table {
      border-collapse: collapse;
    }

    th, td {
      border: 1px solid black;
      padding: 8px;
    }
  </style>
</head>
<body>
<h1><%= "Danh sach san pham" %>
</h1>
<br/>
<table style="width: 800px">
  <thead>
  <th>
    <strong>ID</strong>
  </th>
  <th>
    <strong>Product Name</strong>
  </th>
  <th>
    <strong>Price</strong>
  </th>
  <th>
    <strong>Description</strong>
  </th>
  </thead>
  <tbody>
  <c:forEach items="${products}" var="p">
    <tr>
      <td>${p.id}</td>
      <td>${p.productName}</td>
      <td>${p.price}</td>
      <td>${p.description}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
