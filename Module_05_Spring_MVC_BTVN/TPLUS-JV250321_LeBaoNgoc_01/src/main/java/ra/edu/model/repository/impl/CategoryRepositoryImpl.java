package ra.edu.model.repository.impl;

import org.springframework.stereotype.Repository;
import ra.edu.model.entity.Category;
import ra.edu.model.repository.CategoryRepository;
import ra.edu.model.utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public List<Category> findAll(int page, int size) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("{call get_categories_per_page(?,?)}");
            callStmt.setInt(1, page);
            callStmt.setInt(2, size);
            ResultSet rs = callStmt.executeQuery();
            List<Category> categories = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setDescription(rs.getString("description"));
                category.setStatus(rs.getBoolean("status"));
                categories.add(category);
            }
            return categories;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return null;
    }

    @Override
    public List<Category> findAll(String name, int page, int size) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("{call get_categories_by_name_per_page(?,?,?)}");
            callStmt.setString(1, name);
            callStmt.setInt(2, page);
            callStmt.setInt(3, size);
            ResultSet rs = callStmt.executeQuery();
            List<Category> categories = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setDescription(rs.getString("description"));
                category.setStatus(rs.getBoolean("status"));
                categories.add(category);
            }
            return categories;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return null;
    }

    @Override
    public int getTotalPages(int size) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("{call get_categories_total_pages(?,?)}");
            callStmt.setInt(1, size);
            callStmt.registerOutParameter(2, Types.INTEGER);
            callStmt.execute();
            return callStmt.getInt(2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return 0;
    }

    @Override
    public int getTotalPage(String name, int size) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("{call get_categories_by_name_total_pages(?,?,?)}");
            callStmt.setString(1, name);
            callStmt.setInt(2, size);
            callStmt.registerOutParameter(3, Types.INTEGER);
            callStmt.execute();
            return callStmt.getInt(3);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return 0;
    }

    @Override
    public boolean save(Category category) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String sql = "insert into category (category_name, description) values(?,?)";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareCall(sql);
            preStmt.setString(1, category.getCategoryName());
            preStmt.setString(2, category.getDescription());
            preStmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, preStmt);
        }
        return false;
    }

    @Override
    public Optional<Category> findById(int id) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String sql = "select * from category where category_id = ?";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareCall(sql);
            preStmt.setInt(1, id);
            ResultSet rs = preStmt.executeQuery();
            if (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setDescription(rs.getString("description"));
                category.setStatus(rs.getBoolean("status"));
                return Optional.of(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, preStmt);
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Category author) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String sql = "update category set category_name = ?, description = ? where category_id = ?";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareCall(sql);
            preStmt.setString(1, author.getCategoryName());
            preStmt.setString(2, author.getDescription());
            preStmt.setInt(3, author.getCategoryId());
            preStmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, preStmt);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String sql = "delete from category where category_id = ?";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareCall(sql);
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


    @Override
    public Optional<Category> findByName(String name) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String sql = "select * from category where category_name = ?";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareCall(sql);
            preStmt.setString(1, name.trim());
            ResultSet rs = preStmt.executeQuery();
            if (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setDescription(rs.getString("description"));
                category.setStatus(rs.getBoolean("status"));
                return Optional.of(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, preStmt);
        }
        return Optional.empty();
    }
}
