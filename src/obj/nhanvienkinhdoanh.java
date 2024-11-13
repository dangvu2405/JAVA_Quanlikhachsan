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
import obj.dichvu.dichvu;
import obj.room.phong;

/**
 *
 * @author vuwin
 */
public class nhanvienkinhdoanh extends nhanvien {
    List<KhachHang> khachHangList = new ArrayList<>();
    List<dichvu> dichVuList = new ArrayList<>();
    List<phong> PhongList = new ArrayList<>();
    public nhanvienkinhdoanh(int ID, String name, String email, String phone, String adress, String username, String ngaysinh, String cccd, String password, int chucvu) {
        super(ID, name, email, phone, adress, username, ngaysinh, cccd, password, chucvu);
    }
        public nhanvienkinhdoanh() {
    }
    
    // quan li khach hang



    public List<KhachHang> getkhachhang() {
        List<KhachHang> khachHangList = new ArrayList<>();
        Connection connection = SQLServerConnection.getInstance();
        String sql = "SELECT * FROM KhachHang";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setName(rs.getString("name"));
                kh.setEmail(rs.getString("email"));
                kh.setPhone(rs.getString("phone"));
                kh.setCccd(rs.getString("cccd"));
                kh.setAdress(rs.getString("address"));
                kh.setUsername(rs.getString("username"));
                kh.setPassword(rs.getString("password"));
                kh.setNgaysinh(rs.getString("ngay_sinh"));
                khachHangList.add(kh);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return khachHangList;
    }

    public void addkhachhang(KhachHang KH) {
        Connection connection = SQLServerConnection.getInstance();
        String insertQuery = "INSERT INTO KhachHang (name, email, phone, address, username, password, cccd, ngaySinh) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection.setAutoCommit(false); // Start a transaction
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate localDate = LocalDate.parse(KH.getNgaysinh(), formatter);
            Date sqlDate = Date.valueOf(localDate);

            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                insertStmt.setString(1, KH.getName());
                insertStmt.setString(2, KH.getEmail());
                insertStmt.setString(3, KH.getPhone());
                insertStmt.setString(4, KH.getAdress());
                insertStmt.setString(5, KH.getUsername());
                insertStmt.setString(6, KH.getPassword());
                insertStmt.setString(7, KH.getCccd());
                insertStmt.setDate(8, sqlDate);

                int rowsInserted = insertStmt.executeUpdate();

                if (rowsInserted > 0) {
                    connection.commit();
                    System.out.println("Đã thêm một khách hàng mới thành công!");
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

    public void updatekhachhang(KhachHang KH, int id) {
        Connection connection = SQLServerConnection.getInstance();
        String updateSql = "UPDATE KhachHang SET name = ?, email = ?, phone = ?, address = ?, username = ?, password = ?, cccd = ?, ngaySinh = ? WHERE ID = ?";

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate localDate = LocalDate.parse(KH.getNgaysinh(), formatter);
            Date sqlDate = Date.valueOf(localDate);

            try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
                updateStmt.setString(1, KH.getName());
                updateStmt.setString(2, KH.getEmail());
                updateStmt.setString(3, KH.getPhone());
                updateStmt.setString(4, KH.getAdress());
                updateStmt.setString(5, KH.getUsername());
                updateStmt.setString(6, KH.getPassword());
                updateStmt.setString(7, KH.getCccd());
                updateStmt.setDate(8, sqlDate);
                //updateStmt.setInt(9, id); 
                // Truyền ID vào điều kiện WHERE

                int rowsUpdated = updateStmt.executeUpdate();
                System.out.println("Số hàng đã cập nhật: " + rowsUpdated);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletekhachhang(int id) {
        Connection connection = SQLServerConnection.getInstance();
        String deleteSql = "DELETE FROM KhachHang WHERE ID = ?";

        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {
            deleteStmt.setInt(1, id);
            int rowsDeleted = deleteStmt.executeUpdate();
            System.out.println("Số hàng đã xóa: " + rowsDeleted);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // quan li dich vu 
    public List<dichvu> getdichvu() {
        List<dichvu> dichVuList = new ArrayList<>();
        Connection connection = SQLServerConnection.getInstance();
        String sql = "SELECT * FROM DichVu";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                dichvu dv = new dichvu();
                dv.setTenDichVu(rs.getString("TenDichVu")); // Thay "TenDichVu" bằng tên cột chính xác
                dv.setGia(rs.getFloat("Gia")); // Thay "Gia" bằng tên cột chứa giá dịch vụ
                dichVuList.add(dv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dichVuList;
    }

    public void adddichvu(dichvu DV) {
        Connection connection = SQLServerConnection.getInstance();
        String insertQuery = "INSERT INTO DichVu (TenDichVu, Gia) VALUES (?, ?)";

        try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStmt.setString(1, DV.getTenDichVu());
            insertStmt.setFloat(2, DV.getGia());

            int rowsInserted = insertStmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Đã thêm dịch vụ mới thành công!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updatedichvu(dichvu DV, int id) {
        Connection connection = SQLServerConnection.getInstance();
        String updateSql = "UPDATE DichVu SET TenDichVu = ?, Gia = ? WHERE ID_DichVu = ?";

        try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
            updateStmt.setString(1, DV.getTenDichVu());
            updateStmt.setFloat(2, DV.getGia());
            updateStmt.setInt(3, id); // Đảm bảo truyền ID đúng vào câu lệnh

            int rowsUpdated = updateStmt.executeUpdate();
            System.out.println("Số hàng cập nhật: " + rowsUpdated);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletedichvu(int id) {
        Connection connection = SQLServerConnection.getInstance();
        String deleteSql = "DELETE FROM DichVu WHERE ID_DichVu = ?";

        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {
            deleteStmt.setInt(1, id);
            int rowsDeleted = deleteStmt.executeUpdate();
            System.out.println("Số hàng đã xóa: " + rowsDeleted);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // quan li phong 
    public List<phong> getphong(){
        List<phong> PhongList = new ArrayList<>();
        Connection connection = SQLServerConnection.getInstance();
        String sql = "SELECT * FROM Phong";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                phong pog = new phong();
                pog.setLoaiPhong(rs.getString("loaiPhong")); // Thay thế "loaiPhong" bằng tên cột đúng
                pog.setTenphong(rs.getString("tenPhong")); // Thay thế "tenPhong" bằng tên cột đúng
                pog.setTrangthai(rs.getString("trangThai")); // Thay thế "trangThai" bằng tên cột đúng
                PhongList.add(pog);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PhongList;
    }

    public void addphong(phong Pog){
        Connection connection = SQLServerConnection.getInstance();
        String insertQuery = "INSERT INTO Phong (loaiPhong, tenPhong, trangThai) VALUES (?, ?, ?)";

        try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStmt.setString(1, Pog.getLoaiPhong());
            insertStmt.setString(2, Pog.getTenphong());
            insertStmt.setString(3, Pog.getTrangthai());

            int rowsInserted = insertStmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Đã thêm một phòng mới thành công!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updatephong(phong Pog){
        Connection connection = SQLServerConnection.getInstance();
        String updateSql = "UPDATE Phong SET loaiPhong = ?, tenPhong = ?, trangThai = ? WHERE ID = ?";

        try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
            updateStmt.setString(1, Pog.getLoaiPhong());
            updateStmt.setString(2, Pog.getTenphong());
            updateStmt.setString(3, Pog.getTrangthai());
            //updateStmt.setInt(4, Pog.getID());
            // Cần có phương thức getID() trong lớp phong

            int rowsUpdated = updateStmt.executeUpdate();
            System.out.println("Số hàng cập nhật: " + rowsUpdated);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletephong(int id){
        Connection connection = SQLServerConnection.getInstance();
        String deletesql = "DELETE FROM Phong WHERE ID = ?";

        try (PreparedStatement deleteStmt = connection.prepareStatement(deletesql)) {
            deleteStmt.setInt(1, id);
            int rowsDeleted = deleteStmt.executeUpdate();
            System.out.println("Số hàng đã xóa: " + rowsDeleted);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}