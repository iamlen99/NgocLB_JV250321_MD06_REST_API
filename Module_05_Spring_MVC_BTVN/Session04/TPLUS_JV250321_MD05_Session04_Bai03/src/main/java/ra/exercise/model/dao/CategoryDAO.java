package ra.exercise.model.dao;

import ra.exercise.model.entity.Category;
import ra.exercise.ulti.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
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
}
