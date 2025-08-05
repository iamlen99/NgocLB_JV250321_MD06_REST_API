<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/5/25
  Time: 7:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            background:#f2f2f2;
            font-family: Arial, sans-serif;
        }
        .content {
            width: 600px;
            margin: 40px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 7px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 25px;
        }
        p{margin:8px 0;}

        .schedule {
            display: flex;
            flex-direction: row;
            justify-content: center;
        }

        .schedule a {
            display:inline-block;
            padding:8px 14px;
            margin:5px;
            background:#007bff;
            color:#fff;
            border-radius:4px;
            text-decoration:none;
        }

        .back {
            display:block;
            text-align:center;
            margin-top:20px;
            color:#007bff;
            text-decoration:none;
        }
    </style>
</head>
<body>
<div class="content">
    <h2>Chi Tiết Phim: ${movie.title}</h2>
    <p><strong>Đạo Diễn:</strong> ${movie.director}</p>
    <p><strong>Thể Loại:</strong> ${movie.genre}</p>
    <p><strong>Mô Tả:</strong> ${movie.description}</p>
    <p><strong>Thời Gian:</strong> ${movie.duration}</p>
    <p><strong>Ngôn Ngữ:</strong> ${movie.language}</p>

    <h3>Lịch Chiếu</h3>
    <div class="schedule">
        <a href="#">17/07/2025 00:00</a>
        <a href="#">17/07/2025 21:54</a>
    </div>
    <a class="back" href="${pageContext.request.contextPath}/MovieListServlet">Quay lại Danh Sách Phim</a>
</div>
</body>
</html>
