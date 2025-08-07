package ra.exercise.model.service;

import ra.exercise.model.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getAllSchedule();

    Schedule getScheduleById(long id);

    boolean createSchedule(Schedule schedule);

    boolean updateSchedule(Schedule schedule);

    boolean deleteSchedule(long id);
}
