package ra.edu.service;

import ra.edu.model.entity.Bus;
import ra.edu.model.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    List<Schedule> findAll();
    Optional<Schedule> findById(Long id);
    Schedule save(Schedule schedule);
    boolean delete(Long id);
}
