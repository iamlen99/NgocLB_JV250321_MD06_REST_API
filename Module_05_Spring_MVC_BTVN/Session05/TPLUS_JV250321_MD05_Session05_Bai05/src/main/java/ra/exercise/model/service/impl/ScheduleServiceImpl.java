package ra.exercise.model.service.impl;

import ra.exercise.model.dao.ScheduleDAO;
import ra.exercise.model.dao.impl.ScheduleDAOImpl;
import ra.exercise.model.entity.Schedule;
import ra.exercise.model.service.ScheduleService;

import java.util.List;

public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleDAO scheduleDAO;

    public ScheduleServiceImpl() {
        scheduleDAO = new ScheduleDAOImpl();
    }

    @Override
    public List<Schedule> getAllSchedule() {
        return scheduleDAO.getAllSchedule();
    }

    @Override
    public Schedule getScheduleById(long id) {
        return scheduleDAO.getScheduleById(id);
    }

    @Override
    public boolean createSchedule(Schedule schedule) {
        return scheduleDAO.createSchedule(schedule);
    }

    @Override
    public boolean updateSchedule(Schedule schedule) {
        return scheduleDAO.updateSchedule(schedule);
    }

    @Override
    public boolean deleteSchedule(long id) {
        return scheduleDAO.deleteSchedule(id);
    }
}
