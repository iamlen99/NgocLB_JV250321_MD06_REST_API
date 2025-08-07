package ra.exercise.model.repository.impl;

import org.springframework.stereotype.Repository;
import ra.exercise.model.entity.Category;
import ra.exercise.model.repository.CategoryRepository;
import ra.exercise.model.util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public List<Category> findAll() {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String query = "select * from category";
        try {
            conn = DBUtil.openConnection();
            preStmt = conn.prepareStatement(query);
            ResultSet rs = preStmt.executeQuery();
            List<Category> categories = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setDescription(rs.getString("description"));
                category.setStatus(Boolean.parseBoolean(rs.getString("status")));
                categories.add(category);
            }
            return categories;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, preStmt);
        }
        return null;
    }

    @Override
    public boolean save(Category category) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = DBUtil.openConnection();
            callStmt = conn.prepareCall("call add_category(?,?,?)");
            callStmt.setString(1, category.getCategoryName());
            callStmt.setString(2, category.getDescription());
            callStmt.setBoolean(3, category.isStatus());
            callStmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callStmt);
        }
        return false;
    }

    @Override
    public boolean update(Category category) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = DBUtil.openConnection();
            callStmt = conn.prepareCall("call update_category(?,?,?,?)");
            callStmt.setInt(1, category.getId());
            callStmt.setString(2, category.getCategoryName());
            callStmt.setString(3, category.getDescription());
            callStmt.setBoolean(4, category.isStatus());
            callStmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callStmt);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String query = "delete from category where id = ?";
        try {
            conn = DBUtil.openConnection();
            preStmt = conn.prepareStatement(query);
            preStmt.setInt(1, id);
            preStmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, preStmt);
        }
        return false;
    }

    @Override
    public Optional<Category> findById(int id) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String query = "SELECT * FROM category where id = ?";
        try {
            conn = DBUtil.openConnection();
            preStmt = conn.prepareStatement(query);
            preStmt.setInt(1, id);
            ResultSet rs = preStmt.executeQuery();
            if (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setDescription(rs.getString("description"));
                category.setStatus(Boolean.parseBoolean(rs.getString("status")));
                return Optional.of(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, preStmt);
        }
        return Optional.empty();
    }
}
