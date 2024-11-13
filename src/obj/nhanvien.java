/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package obj;

//import java.util.Date;
import java.sql.Date;
/**
 *
 * @author vuwin
 */
public class nhanvien {
    private int ID;
    private String name;
    private String email;
    private String phone ;
    private String adress;
    private String username;
    private String ngaysinh;
    private String cccd;
    private String password;   
    private int chucvu;

    public nhanvien() {
    }
    
    public nhanvien(int ID, String name, String email, String phone, String adress, String username, String ngaysinh, String cccd, String password, int chucvu) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.adress = adress;
        this.username = username;
        this.ngaysinh = ngaysinh;
        this.cccd = cccd;
        this.password = password;
        this.chucvu = chucvu;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getChucvu() {
        return chucvu;
    }

    public void setChucvu(int chucvu) {
        this.chucvu = chucvu;
    }

}
