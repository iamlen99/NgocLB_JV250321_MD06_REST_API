package ra.exercise.model.dao.impl;

import ra.exercise.model.dao.CategoryDAO;
import ra.exercise.model.entity.Category;
import ra.exercise.ulti.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
}
