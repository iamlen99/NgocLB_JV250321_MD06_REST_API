package ra.exercise.model.dao;

import ra.exercise.model.entity.Schedule;

import java.util.List;

public interface ScheduleDAO {
    List<Schedule> getAllSchedule();

    Schedule getScheduleById(long id);

    boolean createSchedule(Schedule schedule);

    boolean updateSchedule(Schedule schedule);

    boolean deleteSchedule(long id);
}
