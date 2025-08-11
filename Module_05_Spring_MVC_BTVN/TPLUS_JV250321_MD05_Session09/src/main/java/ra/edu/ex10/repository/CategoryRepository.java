package ra.edu.ex10.repository;

import org.springframework.stereotype.Repository;
import ra.edu.ex10.model.Category_EN;
import ra.edu.ex10.model.Category_VI;
import ra.edu.ex10.utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepository {
    public List<Category_EN> findAllEN() {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String query = "SELECT * FROM categories_en";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareStatement(query);
            ResultSet rs = preStmt.executeQuery();
            List<Category_EN> categoryEnList = new ArrayList<>();
            while (rs.next()) {
                Category_EN categoryEn = new Category_EN();
                categoryEn.setId(rs.getInt("id"));
                categoryEn.setCategoryName(rs.getString("category_name"));
                categoryEn.setDescription(rs.getString("description"));
                categoryEnList.add(categoryEn);
            }
            return categoryEnList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, preStmt);
        }
        return null;
    }

    public List<Category_VI> getAllVI(){
        Connection conn = null;
        PreparedStatement preStmt = null;
        String query = "SELECT * FROM categories_vi";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareStatement(query);
            ResultSet rs = preStmt.executeQuery();
            List<Category_VI> categoryViList = new ArrayList<>();
            while (rs.next()) {
                Category_VI categoryVi = new Category_VI();
                categoryVi.setId(rs.getInt("id"));
                categoryVi.setCategoryName(rs.getString("category_name"));
                categoryVi.setDescription(rs.getString("description"));
                categoryViList.add(categoryVi);
            }
            return categoryViList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, preStmt);
        }
        return null;
    }

    public boolean addCategory(Category_VI categoryVi, Category_EN categoryEn) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("{call add_category(?,?,?,?)}");
            callStmt.setString(1, categoryVi.getCategoryName());
            callStmt.setString(2, categoryVi.getDescription());
            callStmt.setString(3, categoryEn.getCategoryName());
            callStmt.setString(4, categoryEn.getDescription());
            callStmt.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return false;
    }
}
