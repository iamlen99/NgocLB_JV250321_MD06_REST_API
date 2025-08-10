<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/7/25
  Time: 8:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <style>
        body, html {
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
    </style>
</head>
<body>
<form:form modelAttribute="product" method="post" action="addProduct">
    <table>
        <thead>
        <tr>
            <td><label>Tên sản phẩm:</label</td>
            <td><form:input path="name"/><br/>
                <form:errors path="name" cssStyle="color: red"/>
            </td>
        </tr>
        <tr>
            <td><label>Giá sản phẩm:</label></td>
            <td><form:input path="price"/><br/>
                <form:errors path="price" cssStyle="color: red"/>
            </td>
        </tr>
        <tr>
            <td><label>Mô tả sản phẩm:</label></td>
            <td><form:input path="description"/><br/>
                <form:errors path="description" cssStyle="color: red"/>
            </td>
        </tr>
    </table>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>
