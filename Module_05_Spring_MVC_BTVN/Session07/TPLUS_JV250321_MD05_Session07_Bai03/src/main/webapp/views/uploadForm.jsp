<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/7/25
  Time: 8:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form modelAttribute="userProfile" method="post" action="uploadAvatar" enctype="multipart/form-data">
    <table>
        <tr>
            <td><label>Tên người dùng:</label></td>
            <td><form:input path="username"/><br/>
                <form:errors path="username" cssStyle="color: red"/>
            </td>
        </tr>
        <tr>
            <td><label>File ảnh đại diện:</label></td>
            <td><input type="file" name="avatar"/><br/>
                <c:if test="${not empty errorMessage}">
                    <p class="error">${errorMessage}</p>
                </c:if>

            </td>
        </tr>
    </table>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>
