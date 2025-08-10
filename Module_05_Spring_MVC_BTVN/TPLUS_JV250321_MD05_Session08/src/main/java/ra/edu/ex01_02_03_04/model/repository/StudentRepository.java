package ra.edu.ex01_02_03_04.model.repository;

import org.springframework.stereotype.Repository;
import ra.edu.ex01_02_03_04.model.entity.Student;
import ra.edu.utils.ConnectionDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {
    public List<Student> findAll() {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String sql = "select * from student";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareStatement(sql);
            ResultSet rs = preStmt.executeQuery();
            List<Student> studentList = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setDob(LocalDate.parse(rs.getString("dob")));
                studentList.add(student);
            }
            return studentList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, preStmt);
        }
        return null;
    }

    public boolean save(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_student(?,?,?)}");
            callSt.setString(1, student.getName());
            callSt.setString(2, student.getEmail());
            callSt.setDate(3, Date.valueOf(student.getDob()));
            callSt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    public Optional<Student> findById(int id) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String sql = "select * from student where id = ?";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareStatement(sql);
            preStmt.setInt(1, id);
            ResultSet rs = preStmt.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setDob(LocalDate.parse(rs.getString("dob")));
                return Optional.of(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, preStmt);
        }
        return Optional.empty();
    }

    public boolean update(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_student(?,?,?,?)}");
            callSt.setInt(1, student.getId());
            callSt.setString(2, student.getName());
            callSt.setString(3, student.getEmail());
            callSt.setDate(4, java.sql.Date.valueOf(student.getDob()));
            callSt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    public boolean delete(int id) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String sql = "delete from student where id = ?";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareStatement(sql);
            preStmt.setInt(1, id);
            preStmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, preStmt);
        }
        return false;
    }
}
