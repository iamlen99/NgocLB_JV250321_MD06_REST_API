<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/5/25
  Time: 1:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Danh sach phim</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<div class="container">
    <h2>Danh Sách Phim</h2>
    <button class="add-btn" onclick="window.location.href='${pageContext.request.contextPath}/AddMovieServlet?action=goToAddPage'">Thêm Mới Phim</button>
    <c:if test="${message !=null}">
        <p style="color: red">${message}</p>
    </c:if>
    <table>
        <thead>
        <tr>
            <th>Mã phim</th>
            <th>Tiêu Đề</th>
            <th>Đạo Diễn</th>
            <th>Thể Loại</th>
            <th>Thời Lượng</th>
            <th>Ngôn Ngữ</th>
            <th>Hành Động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${movieList}" var="movie">
            <tr>
                <td>${movie.id}</td>
                <td>${movie.title}</td>
                <td>${movie.director}</td>
                <td>${movie.genre}</td>
                <td>${movie.duration}</td>
                <td>${movie.language}</td>
                <td>
                    <button class="edit-btn" onclick="window.location.href='${pageContext.request.contextPath}/EditMovieServlet?action=goToUpdatePage&id=${movie.id}'">Sửa</button>
                    <button class="delete-btn" onclick="confirmDelete(${movie.id})">Xóa</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    function confirmDelete(id) {
        if (confirm("Bạn có chắc chắn muốn xóa phim này !")) {
            window.location.href='${pageContext.request.contextPath}/DeleteMovieServlet?action=deleteMovie&id=' + id;
            alert("Đã xóa phim.");
        }
    }
</script>
</body>
</html>
