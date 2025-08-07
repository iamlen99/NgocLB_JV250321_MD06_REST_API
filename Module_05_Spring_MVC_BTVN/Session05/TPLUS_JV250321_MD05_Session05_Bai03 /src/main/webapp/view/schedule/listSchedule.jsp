<%@ page import="ra.exercise.model.entity.Schedule" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/schedule/listScheduleStyle.css">
    <title>List Movies</title>
</head>
<body>
<div class="container">
    <h1>Danh sách lịch chiếu phim</h1>
    <a href="<%=request.getContextPath()%>/view/schedule/addSchedule.jsp" class="btn-add">Thêm mới lịch chiếu phim</a>
    <table>
        <thead>
        <tr>
            <th class="col-movie-title">Tiêu đề phim</th>
            <th class="col-show-time">Thời gian</th>
            <th class="col-screen-room">Phòng chiếu</th>
            <th class="col-available-seat">Số ghế có sẵn</th>
            <th class="col-format">Định dạng</th>
            <th class="col-action">Hành động</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Schedule> scheduleList = (List<Schedule>) request.getAttribute("list");
            if (scheduleList != null) {
                for (Schedule schedule : scheduleList) {
        %>
        <tr>
            <td><%=schedule.getMovieId()%>
            </td>
            <td><%=schedule.getShowTime()%>
            </td>
            <td><%=schedule.getScreenRoomId()%>
            </td>
            <td><%=schedule.getAvailableSeat()%>
            </td>
            <td><%=schedule.getFormat()%>
            </td>
            <td>
                <a href="<%=request.getContextPath()%>/schedule?action=edit&scheduleId=<%=schedule.getId()%>"
                   class="btn-edit">Sửa</a>
                <a href="<%=request.getContextPath()%>/schedule?action=delete&scheduleId=<%=schedule.getId()%>"
                   class="btn-delete"
                   onclick="return confirm('Bạn có chắc chắn muốn xóa lịch chiếu này không?');">Xóa</a>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
    <%
        if (request.getAttribute("error") != null) {
    %>
    <p class="error"><%=request.getAttribute("error")%>
    </p>
    <%
        }
    %>
</div>
</body>
</html>
