<%@ page import="ra.exercise.model.entity.Schedule" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/schedule/updateScheduleStyle.css">
    <title>Update Schedule</title>
</head>
<body>
<div class="container">
    <h1>Sửa lịch chiếu</h1>
    <%
        if (request.getAttribute("error") != null) {
    %>
    <p class="error"><%=request.getAttribute("error")%>
    </p>
    <%
        }
    %>
    <form action="<%=request.getContextPath()%>/schedule?action=update" method="post">
        <%
            Schedule schedule = (Schedule) request.getAttribute("scheduleEdit");
            if (schedule != null) {
        %>
        <label for="scheduleId">ID</label>
        <input type="number" id="scheduleId" name="scheduleId" readonly value="<%=schedule.getId()%>">

        <label for="movieId">Tiêu đề phim</label>
        <input type="text" id="movieId" name="movieId" required value="<%=schedule.getMovieId()%>">

        <label for="roomId">Phòng chiếu</label>
        <input type="text" id="roomId" name="roomId" required value="<%=schedule.getScreenRoomId()%>">

        <label for="showTime">Thời gian</label>
        <input type="datetime-local" id="showTime" name="showTime" required value="<%=schedule.getShowTime()%>">

        <label for="format">Định dạng</label>
        <input type="text" id="format" name="format" value="<%=schedule.getFormat()%>">

        <input type="submit" value="Sửa lịch chiếu" class="btn-edit">
        <%
            }
        %>
    </form>
</div>
</body>
</html>
