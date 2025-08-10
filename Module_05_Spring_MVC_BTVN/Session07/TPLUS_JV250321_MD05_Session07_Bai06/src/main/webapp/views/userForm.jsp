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
        table td {
            vertical-align: top;
        }

    </style>
</head>
<body>
<form:form modelAttribute="user" method="post" action="addUser">
    <table>
        <thead>
        <tr>
            <td><label>Số điện thoại:</label></td>
            <td><form:input path="phoneNumber"/><br/>
                <form:errors path="phoneNumber" cssStyle="color: red"/>
            </td>
        </tr>
    </table>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>
