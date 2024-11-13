package javaapplication1.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

public class SQLServerConnection {

    private static Connection con = null; // Biến lưu kết nối duy nhất
    private static final String CONNECTION_URL = "jdbc:sqlserver://localhost:1433;databaseName=JAVA_new;encrypt=true;trustServerCertificate=true;user=sa;password=dangvu123;";

    // Phương thức getInstance() để lấy đối tượng kết nối duy nhất
    public static Connection getInstance() {
        if (con == null) { // Nếu chưa có kết nối, tạo kết nối mới
            con = connect();
        }
        return con; // Trả về kết nối duy nhất
    }

    // Hàm khởi tạo kết nối
    private static Connection connect() {
        try {
            con = DriverManager.getConnection(CONNECTION_URL);
            System.out.println("Kết nối thành công!");
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Kết nối thất bại!");
            return null;
        }
    }

    // Phương thức kiểm tra kết nối
    public static boolean isConnected() {
        try {
            return con != null && !con.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức đóng kết nối khi không cần thiết nữa
    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Đã đóng kết nối.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Lỗi khi đóng kết nối.");
            }
        }
    }
    public boolean xacminhemail(String email ){
        String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
        if(email == ""){
            return false;
        }
        // tim kiem email co ton tai khong
        return EMAIL_PATTERN.matcher(email).matches();
    }
    public static boolean emailExistsInDatabase(String email, String table) {
        Connection connection = SQLServerConnection.getInstance();
        String sql = "SELECT COUNT(*) FROM "+ table +" WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) { // Sử dụng PreparedStatement
            stmt.setString(1, email); // Gán giá trị cho tham số

            try (ResultSet rs = stmt.executeQuery()) { // Thực hiện truy vấn
                if (rs.next()) {
                    int count = rs.getInt(1); // Lấy số lượng email
                    return count == 0; // Trả về true nếu email không tồn tại
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý ngoại lệ
        }
        return false; // Trả về false nếu có lỗi xảy ra
    }
    public static boolean isPasswordValid(String password) {
    // Kiểm tra độ dài tối thiểu
    if (password.length() < 8) {
        return false; // Mật khẩu phải có ít nhất 8 ký tự
    }

    // Kiểm tra có chữ hoa
    if (!password.matches(".*[A-Z].*")) {
        return false; // Mật khẩu phải có ít nhất một chữ cái hoa
    }

    // Kiểm tra có chữ thường
    if (!password.matches(".*[a-z].*")) {
        return false; // Mật khẩu phải có ít nhất một chữ cái thường
    }

    // Kiểm tra có số
    if (!password.matches(".*[0-9].*")) {
        return false; // Mật khẩu phải có ít nhất một số
    }

    // Kiểm tra có ký tự đặc biệt
    if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
        return false; // Mật khẩu phải có ít nhất một ký tự đặc biệt
    }

    return true; // Mật khẩu hợp lệ
    }
    
    
    public static boolean isPhoneNumberValid(String phoneNumber) {
    // Kiểm tra độ dài tối thiểu và tối đa
    if (phoneNumber.length() < 10 || phoneNumber.length() > 15) {
        return false; // Số điện thoại phải có từ 10 đến 15 ký tự
    }

    // Kiểm tra chỉ chứa số và có thể có ký tự + ở đầu
    if (!phoneNumber.matches("\\+?\\d+")) {
        return false; // Số điện thoại chỉ được chứa số và có thể có ký tự + ở đầu
    }

    return true; // Số điện thoại hợp lệ
}

}

