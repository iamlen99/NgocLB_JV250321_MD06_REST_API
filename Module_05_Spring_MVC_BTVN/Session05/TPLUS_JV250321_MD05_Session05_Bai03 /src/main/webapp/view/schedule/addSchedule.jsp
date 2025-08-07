<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/schedule/addScheduleStyle.css">
    <title>Add Schedule</title>
</head>
<body>
<div class="container">
    <h1>Thêm lịch chiếu phim</h1>
    <%
        if (request.getAttribute("error") != null) {
    %>
    <p class="error"><%=request.getAttribute("error")%>
    </p>
    <%
        }
    %>
    <form action="<%=request.getContextPath()%>/schedule?action=save" method="post">
        <label for="movieId">Mã phim</label>
        <input type="text" id="movieId" name="movieId" required>

        <label for="roomId">Mã phòng chiếu</label>
        <input type="text" id="roomId" name="roomId" required>

        <label for="showTime">Thời gian</label>
        <input type="datetime-local" id="showTime" name="showTime" required>

        <label for="format">Định dạng</label>
        <input type="text" id="format" name="format">

        <input type="submit" value="Thêm lịch chiếu" class="btn-add">
    </form>
</div>
</body>
</html>