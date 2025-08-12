package ra.edu.model.repository.impl;

import org.springframework.stereotype.Repository;
import ra.edu.model.dto.LoginDTO;
import ra.edu.model.entity.Customer;
import ra.edu.model.entity.Role;
import ra.edu.model.repository.CustomerRepository;
import ra.edu.utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public List<Customer> findAll() {
        Connection conn = null;
        PreparedStatement preStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareStatement("select * from customers");
            ResultSet rs = preStmt.executeQuery();
            List<Customer> list = new ArrayList<>();
            while(rs.next()){
                Customer customer = new  Customer();
                customer.setId(rs.getInt("id"));
                customer.setFullName(rs.getString("full_name"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                customer.setRole(Role.valueOf(rs.getString("role")));
                list.add(customer);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }finally {
            ConnectionDB.closeConnection(preStmt, conn);
        }
        return null;
    }

    @Override
    public boolean register(Customer customer) {
        Connection conn = null;
        PreparedStatement preStmt = null;

        conn = ConnectionDB.openConnection();
        try {
            preStmt = conn.prepareStatement("insert into customers(full_name,phone_number,email,password,address,role) values (?,?,?,?,?,?)");
            preStmt.setString(1, customer.getFullName());
            preStmt.setString(2, customer.getPhoneNumber());
            preStmt.setString(3, customer.getEmail());
            preStmt.setString(4, customer.getPassword());
            preStmt.setString(5, customer.getAddress());
            preStmt.setString(6, customer.getRole().toString());
            int i = preStmt.executeUpdate();
            if(i>0) return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(preStmt, conn);
        }
        return false;
    }

    @Override
    public Customer loginCustomer(LoginDTO loginDTO) {
        Customer c = null;
        Connection con = null;
        PreparedStatement preStmt = null;
        ResultSet rs;

        con = ConnectionDB.openConnection();
        try {
            preStmt = con.prepareStatement("select * from customers where email=? and password=?");
            preStmt.setString(1, loginDTO.getEmail());
            preStmt.setString(2, loginDTO.getPassword());
            rs = preStmt.executeQuery();
            if(rs.next()){
                c = new  Customer();
                c.setFullName(rs.getString("full_name"));
                c.setPhoneNumber(rs.getString("phone_number"));
                c.setEmail(rs.getString("email"));
                c.setAddress(rs.getString("address"));
                c.setRole(Role.valueOf(rs.getString("role")));
                c.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(preStmt, con);
        }
        return c;
    }

    @Override
    public boolean emailExist(String email) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet rs;

        conn = ConnectionDB.openConnection();
        try {
            preStmt = conn.prepareStatement("select * from customers where email=?");
            preStmt.setString(1, email);
            rs = preStmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }finally {
            ConnectionDB.closeConnection(preStmt,conn);
        }
        return false;
    }
}
