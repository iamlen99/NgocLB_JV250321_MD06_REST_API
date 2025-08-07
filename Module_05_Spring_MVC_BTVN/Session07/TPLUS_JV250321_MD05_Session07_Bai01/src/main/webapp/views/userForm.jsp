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
</head>
<body>
<form:form modelAttribute="user" method="post" action="addUser">
    <table>
        <thead>
        <tr>
            <td><label>Name:</label</td>
            <td><form:input path="name"/><br/>
                <form:errors path="name" cssStyle="color: red"/>
            </td>
        </tr>
        <tr>
            <td><label>Age:</label></td>
            <td><form:input path="age"/><br/>
                <form:errors path="age" cssStyle="color: red"/>
            </td>
        </tr>
        <tr>
            <td><label>Address:</label></td>
            <td><form:input path="address"/><br/>
                <form:errors path="address" cssStyle="color: red"/>
            </td>
        </tr>
    </table>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>
