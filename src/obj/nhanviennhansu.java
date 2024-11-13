/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package obj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javaapplication1.database.SQLServerConnection;

/**
 *
 * @author vuwin
 */
public class nhanviennhansu extends nhanvien {

    public nhanviennhansu(int ID, String name, String email, String phone, String adress, String username, String ngaysinh, String cccd, String password, int chucvu) {
        super(ID, name, email, phone, adress, username, ngaysinh, cccd, password, chucvu);
    } 
    // QUAN LI NHAN VIEN
public List<nhanvien> getnhanvien() {
    List<nhanvien> nhanVienList = new ArrayList<>();
    Connection connection = SQLServerConnection.getInstance();
    String sql = "SELECT * FROM NhanVien";

    try (Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        
        while (rs.next()) {
            nhanvien nv = new nhanvien();
            nv.setID(rs.getInt("ID")); // Điều chỉnh tên cột nếu cần
            nv.setName(rs.getString("name"));
            nv.setEmail(rs.getString("email"));
            nv.setPhone(rs.getString("phone"));
            nv.setCccd(rs.getString("cccd"));
            nv.setAdress(rs.getString("address"));
            nv.setUsername(rs.getString("username"));
            nv.setPassword(rs.getString("password"));

            // Lấy giá trị ngày sinh
            Date ngaysinh = rs.getDate("ngay_sinh"); // Sử dụng getDate để lấy kiểu DATE
            if (ngaysinh != null) {
                nv.setNgaysinh(ngaysinh.toString()); // Chuyển đổi sang String nếu cần
            } else {
                nv.setNgaysinh(null); // Hoặc xử lý trường hợp ngày sinh null
            }

            nv.setChucvu(rs.getInt("ID_ChucVu"));
            nhanVienList.add(nv);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return nhanVienList;
}
    public void addNhanVien(nhanvien NV) {
        String insertQuery = "INSERT INTO NhanVien (name, email, phone, address, ID_ChucVu, username, password, cccd, ngaySinh) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = SQLServerConnection.getInstance();

        try {
            connection.setAutoCommit(false); // Start a transaction

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate localDate = LocalDate.parse(NV.getNgaysinh(), formatter);
            Date sqlDate = Date.valueOf(localDate);

            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                insertStmt.setString(1, NV.getName());
                insertStmt.setString(2, NV.getEmail());
                insertStmt.setString(3, NV.getPhone());
                insertStmt.setString(4, NV.getAdress());
                insertStmt.setInt(5, NV.getChucvu());
                insertStmt.setString(6, NV.getUsername());
                insertStmt.setString(7, NV.getPassword());
                insertStmt.setString(8, NV.getCccd());
                insertStmt.setDate(9, sqlDate);

                int rowsInserted = insertStmt.executeUpdate();

                if (rowsInserted > 0) {
                    connection.commit();
                    System.out.println("Đã thêm một nhân viên mới thành công!");
                    }
            }
        } catch (SQLException e) {
            try {
                connection.rollback(); // Rollback transaction in case of error
                System.out.println("Đã hoàn tác giao dịch do lỗi: " + e.getMessage());
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true); // Reset auto-commit
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void updatenhanvien(nhanvien NV){
        Connection connection = SQLServerConnection.getInstance();
        String updateSql = "UPDATE NhanVien SET name = ?, email = ?, phone = ?, address = ?, ID_ChucVu = ?, username = ?, password = ?, cccd = ?, ngaySinh = ? WHERE ID = ?";

        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate localDate = LocalDate.parse(NV.getNgaysinh(), formatter);
            Date sqlDate = Date.valueOf(localDate);
            PreparedStatement updateStmt = connection.prepareStatement(updateSql);
                updateStmt.setString(1, NV.getName());
                updateStmt.setString(2, NV.getEmail());
                updateStmt.setString(3, NV.getPhone());
                updateStmt.setString(4, NV.getAdress());
                updateStmt.setInt(5, NV.getChucvu());
                updateStmt.setString(6, NV.getUsername());
                updateStmt.setString(7, NV.getPassword());
                updateStmt.setString(8, NV.getCccd());
                updateStmt.setDate(9, sqlDate);
                updateStmt.setInt(10, NV.getID());
                int rs = updateStmt.executeUpdate();
                System.out.println(rs);
        }
        catch (SQLException e) {
                e.printStackTrace();
        }
    }
    public void deletenhanvien(int id){
        Connection connection = SQLServerConnection.getInstance();
        String deletesql = "DELETE FROM NhanVien WHERE ID_NhanVien = ?";
        try{
             PreparedStatement updateStmt = connection.prepareStatement(deletesql);
             updateStmt.setInt(1,id);
             int rs = updateStmt.executeUpdate();
             System.out.println(rs);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // QUAN LI CA LAM
    
   
    @Override
    public void setChucvu(int chucvu) {
        super.setChucvu(chucvu); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public int getChucvu() {
        return super.getChucvu(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String getPassword() {
        return super.getPassword(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void setCccd(String cccd) {
        super.setCccd(cccd); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String getCccd() {
        return super.getCccd(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void setNgaysinh(String ngaysinh) {
        super.setNgaysinh(ngaysinh); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String getNgaysinh() {
        return super.getNgaysinh(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String getUsername() {
        return super.getUsername(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void setAdress(String adress) {
        super.setAdress(adress); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String getAdress() {
        return super.getAdress(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }



    @Override
    public void setEmail(String email) {
        super.setEmail(email); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String getEmail() {
        return super.getEmail(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void setName(String name) {
        super.setName(name); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String getName() {
        return super.getName(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }




}
