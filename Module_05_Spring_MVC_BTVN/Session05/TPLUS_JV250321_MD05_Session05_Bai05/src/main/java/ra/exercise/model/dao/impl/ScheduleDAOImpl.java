package ra.exercise.model.dao.impl;

import ra.exercise.model.dao.ScheduleDAO;
import ra.exercise.model.entity.Schedule;
import ra.exercise.model.ulti.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAOImpl implements ScheduleDAO {
    @Override
    public List<Schedule> getAllSchedule() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Schedule> scheduleList = null;
        Schedule schedule = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_all_schedules()}");
            ResultSet resultSet = callableStatement.executeQuery();
            scheduleList = new ArrayList<>();
            while (resultSet.next()) {
                schedule = new Schedule();
                schedule.setId(resultSet.getLong("id"));
                schedule.setMovieId(resultSet.getLong("movie_id"));
                schedule.setScreenRoomId(resultSet.getLong("screen_room_id"));
                schedule.setShowTime(resultSet.getTimestamp("show_time").toLocalDateTime());
                schedule.setFormat(resultSet.getString("format"));
                schedule.setAvailableSeat(resultSet.getInt("available_seats"));
                scheduleList.add(schedule);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return scheduleList;
    }

    @Override
    public Schedule getScheduleById(long id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Schedule schedule = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_schedule_by_id(?)}");
            callableStatement.setLong(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                schedule = new Schedule();
                schedule.setId(resultSet.getLong("id"));
                schedule.setMovieId(resultSet.getLong("movie_id"));
                schedule.setScreenRoomId(resultSet.getLong("screen_room_id"));
                schedule.setShowTime(resultSet.getTimestamp("show_time").toLocalDateTime());
                schedule.setFormat(resultSet.getString("format"));
                schedule.setAvailableSeat(resultSet.getInt("available_seats"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return schedule;
    }

    @Override
    public boolean createSchedule(Schedule schedule) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call create_schedule(?,?,?,?)}");
            callableStatement.setLong(1, schedule.getMovieId());
            callableStatement.setLong(2, schedule.getScreenRoomId());
            callableStatement.setTimestamp(3, Timestamp.valueOf(schedule.getShowTime()));
            callableStatement.setString(4, schedule.getFormat());
            int rows = callableStatement.executeUpdate();
            if (rows > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public boolean updateSchedule(Schedule schedule) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call update_schedule(?,?,?,?,?)}");
            callableStatement.setLong(1, schedule.getId());
            callableStatement.setLong(2, schedule.getMovieId());
            callableStatement.setLong(3, schedule.getScreenRoomId());
            callableStatement.setTimestamp(4, Timestamp.valueOf(schedule.getShowTime()));
            callableStatement.setString(5, schedule.getFormat());
            int rows = callableStatement.executeUpdate();
            if (rows > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public boolean deleteSchedule(long id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call delete_schedule(?)}");
            callableStatement.setLong(1, id);
            int rows = callableStatement.executeUpdate();
            if (rows > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }
}
