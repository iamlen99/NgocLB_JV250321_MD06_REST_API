package ra.edu.model.repository.impl;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.Product;
import ra.edu.model.repository.ProductRepository;
import ra.edu.model.utils.ConnectionDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public List<Product> findAll(int page, int size) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("{call get_products_per_page(?,?)}");
            callStmt.setInt(1, page);
            callStmt.setInt(2, size);
            ResultSet rs = callStmt.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setImageUrl(rs.getString("image_url"));
                product.setStatus(rs.getBoolean("status"));
                product.setCreatedAt(LocalDateTime.parse(rs.getString("created_at"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                product.setCategoryId(rs.getInt("category_id"));
                products.add(product);
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return null;
    }

    @Override
    public List<Product> findAll(String name, int page, int size) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("{call get_products_by_name_per_page(?,?,?)}");
            callStmt.setString(1, name);
            callStmt.setInt(2, page);
            callStmt.setInt(3, size);
            ResultSet rs = callStmt.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setImageUrl(rs.getString("image_url"));
                product.setStatus(rs.getBoolean("status"));
                product.setCreatedAt(LocalDateTime.parse(rs.getString("created_at"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                product.setCategoryId(rs.getInt("category_id"));
                products.add(product);
            }
            return products;
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
            callStmt = conn.prepareCall("{call get_products_total_pages(?,?)}");
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
            callStmt = conn.prepareCall("{call get_products_by_name_total_pages(?,?,?)}");
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
    public boolean save(Product product) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String sql = "insert into product (product_name, description, price, image_url, created_at,category_id) values(?,?,?,?,?,?)";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareCall(sql);
            preStmt.setString(1, product.getProductName());
            preStmt.setString(2, product.getDescription());
            preStmt.setDouble(3, product.getPrice());
            preStmt.setString(4, product.getImageUrl());
            preStmt.setTimestamp(5, Timestamp.valueOf(product.getCreatedAt()));
            preStmt.setInt(6, product.getCategoryId());
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
    public Optional<Product> findById(int id) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String sql = "select * from product where product_id = ?";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareCall(sql);
            preStmt.setInt(1, id);
            ResultSet rs = preStmt.executeQuery();
            if (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setImageUrl(rs.getString("image_url"));
                product.setStatus(rs.getBoolean("status"));
                product.setCreatedAt(LocalDateTime.parse(rs.getString("created_at"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                product.setCategoryId(rs.getInt("category_id"));
                return Optional.of(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, preStmt);
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Product product) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String sql = "update product set product_name = ?, description = ?, price, image_url = ?, createAt = ?,category_id = ? where product_id = ?";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareCall(sql);
            preStmt.setString(1, product.getProductName());
            preStmt.setString(2, product.getDescription());
            preStmt.setDouble(3, product.getPrice());
            preStmt.setString(4, product.getImageUrl());
            preStmt.setTimestamp(5, Timestamp.valueOf(product.getCreatedAt()));
            preStmt.setInt(6, product.getCategoryId());
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
        String sql = "delete from product where product_id = ?";
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
    public Optional<Product> findByName(String name) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String sql = "select * from product where product_name = ?";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareCall(sql);
            preStmt.setString(1, name.trim());
            ResultSet rs = preStmt.executeQuery();
            if (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setImageUrl(rs.getString("image_url"));
                product.setStatus(rs.getBoolean("status"));
                product.setCreatedAt(LocalDateTime.parse(rs.getString("created_at")));
                product.setCategoryId(rs.getInt("category_id"));
                return Optional.of(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, preStmt);
        }
        return Optional.empty();
    }
}
