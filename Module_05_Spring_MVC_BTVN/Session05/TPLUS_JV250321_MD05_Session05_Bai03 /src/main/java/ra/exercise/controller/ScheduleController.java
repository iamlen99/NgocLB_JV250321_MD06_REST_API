package ra.exercise.controller;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ra.exercise.model.entity.Schedule;
import ra.exercise.model.service.ScheduleService;
import ra.exercise.model.service.impl.ScheduleServiceImpl;

@WebServlet(urlPatterns = "/schedule")
public class ScheduleController extends HttpServlet {
    private final ScheduleService scheduleService;

    public ScheduleController() {
        scheduleService = new ScheduleServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "list" -> findAllSchedule(req, resp);
            case "delete" -> {
                long id = Long.parseLong(req.getParameter("scheduleId"));
                boolean success = scheduleService.deleteSchedule(id);
                if (success) {
                    findAllSchedule(req, resp);
                } else {
                    String error = "Có lỗi trong quá trình xóa lịch chiếu";
                    req.setAttribute("error", error);
                    req.getRequestDispatcher("view/schedule/listSchedule.jsp").forward(req, resp);
                }
            }
            case "edit" -> {
                long id = Long.parseLong(req.getParameter("scheduleId"));
                Schedule scheduleEdit = scheduleService.getScheduleById(id);
                req.setAttribute("scheduleEdit", scheduleEdit);
                req.getRequestDispatcher("view/schedule/updateSchedule.jsp").forward(req, resp);
            }
        }
    }

    public void findAllSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Schedule> scheduleList = scheduleService.getAllSchedule();
        req.setAttribute("list", scheduleList);
        req.getRequestDispatcher("view/schedule/listSchedule.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Schedule schedule = setSchedule(req);
        if (action.equals("save")) {
            boolean success = scheduleService.createSchedule(schedule);
            if (success) {
                findAllSchedule(req, resp);
            } else {
                String error = "Có lỗi trong quá trình thêm mới lịch chiếu";
                req.setAttribute("error", error);
                req.getRequestDispatcher("view/schedule/addSchedule.jsp").forward(req, resp);
            }
        } else if (action.equals("update")) {
            boolean success = scheduleService.updateSchedule(schedule);
            if (success) {
                findAllSchedule(req, resp);
            } else {
                String error = "Có lỗi trong quá trình cập nhật lịch chiếu";
                req.setAttribute("error", error);
                req.getRequestDispatcher("view/schedule/updateSchedule.jsp").forward(req, resp);
            }
        }
    }

    public Schedule setSchedule(HttpServletRequest req) {
        Schedule schedule = new Schedule();
        String scheduleIdStr = req.getParameter("scheduleId");
        if (scheduleIdStr != null && !scheduleIdStr.isEmpty()) {
            schedule.setId(Long.parseLong(scheduleIdStr));
        }
        schedule.setMovieId(Long.parseLong(req.getParameter("movieId")));
        schedule.setScreenRoomId(Long.parseLong(req.getParameter("roomId")));
        schedule.setShowTime(LocalDateTime.parse(req.getParameter("showTime")));
        schedule.setFormat(req.getParameter("format"));
        return schedule;
    }
}