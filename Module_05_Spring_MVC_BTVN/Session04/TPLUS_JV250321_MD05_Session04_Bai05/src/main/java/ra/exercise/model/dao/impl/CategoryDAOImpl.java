package ra.exercise.model.dao.impl;

import ra.exercise.model.dao.CategoryDAO;
import ra.exercise.model.entity.Category;
import ra.exercise.ulti.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDAOImpl implements CategoryDAO {
    public List<Category> findAll() {
        Connection conn = null;
        PreparedStatement preStmt = null;
        List<Category> categories = null;
        String sql = "select * from category where status=1";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareStatement(sql);
            ResultSet rs = preStmt.executeQuery();
            categories = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setCateName(rs.getString("cate_name"));
                category.setDescription(rs.getString("description"));
                category.setStatus(rs.getBoolean("status"));
                categories.add(category);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }  finally {
            ConnectionDB.closePrepareStatement(preStmt);
            ConnectionDB.closeConnection(conn);
        }
        return categories;
    }

    @Override
    public boolean save(Category category) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String sql = "insert into category (cate_name,description) values (?,?)";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, category.getCateName());
            preStmt.setString(2, category.getDescription());
            preStmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            ConnectionDB.closePrepareStatement(preStmt);
            ConnectionDB.closeConnection(conn);
        }
        return false;
    }

    @Override
    public boolean update(Category category) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String sql = "UPDATE category set cate_name = ? ,description = ?, status = ? where id=?";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, category.getCateName());
            preStmt.setString(2, category.getDescription());
            preStmt.setBoolean(3, category.isStatus());
            preStmt.setInt(4, category.getId());
            preStmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            ConnectionDB.closePrepareStatement(preStmt);
            ConnectionDB.closeConnection(conn);
        }
        return false;
    }

    @Override
    public Optional<Category> findById(int id) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String sql = "select * from category where id=?";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareStatement(sql);
            preStmt.setInt(1, id);
           ResultSet rs = preStmt.executeQuery();
           if (rs.next()) {
               Category category = new Category();
               category.setId(rs.getInt("id"));
               category.setCateName(rs.getString("cate_name"));
               category.setDescription(rs.getString("description"));
               category.setStatus(rs.getBoolean("status"));
               return Optional.of(category);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            ConnectionDB.closePrepareStatement(preStmt);
            ConnectionDB.closeConnection(conn);
        }
        return Optional.empty();
    }
}
