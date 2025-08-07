<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/5/25
  Time: 1:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sua phim</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<div class="form-container">
    <h2>Sửa Phim</h2>
    <c:if test="${message !=null}">
        <p style="color: red">${message}</p>
    </c:if>
    <form action="${pageContext.request.contextPath}/EditMovieServlet?action=updateMovie" method="post">
        <input type="hidden" name="id" value="${movie.movieId}" readonly>

        <label>Tiêu Đề:</label>
        <input type="text" name="title" value="${movie.title}" required>

        <label>Đạo Diễn:</label>
        <input type="text" name="director" value="${movie.director}" required>

        <label>Thể Loại:</label>
        <input type="text" name="genre" value="${movie.genre}" required>

        <label>Mô Tả:</label>
        <textarea name="description">${movie.description}</textarea>

        <label>Thời Gian (phút):</label>
        <input type="number" name="duration" value="${movie.duration}" required>

        <label>Ngôn Ngữ:</label>
        <input type="text" name="language" value="${movie.language}" required>

        <button type="submit" class="edit-btn">Cập Nhật Phim</button>
    </form>
</div>
</body>
</html>
