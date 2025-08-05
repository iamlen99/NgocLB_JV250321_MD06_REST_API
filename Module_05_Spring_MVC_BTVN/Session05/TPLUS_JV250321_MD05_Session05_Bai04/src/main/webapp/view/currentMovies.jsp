<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/5/25
  Time: 6:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            background: #f2f2f2;
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            text-align: center;
        }
        h2 {
            margin: 30px 0;
            color: #444;
        }
        .movie-card {
            display: inline-block;
            width: 27%;
            background: white;
            border-radius: 8px;
            padding: 20px;
            margin: 10px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        }
        .movie-card h3 {
            margin: 0 0 15px;
            color: #0056d6;
        }
        .movie-card p {
            margin: 5px 0;
        }
        .movie-card a {
            display: inline-block;
            margin-top: 10px;
            color: #007bff;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Danh Sách Phim Đang Chiếu</h2>
    <c:forEach items="${movieList}" var="movie">
        <div class="movie-card">
            <h3>${movie.title}</h3>
            <p><b>Đạo Diễn:</b>${movie.director}</p>
            <p><b>Thể Loại:</b>${movie.genre}g</p>
            <a href="${pageContext.request.contextPath}/DisplayMovieDetailServlet?action=displayDetail&id=${movie.id}">Xem Chi Tiết</a>
        </div>
    </c:forEach>
</div>
</body>
</html>
