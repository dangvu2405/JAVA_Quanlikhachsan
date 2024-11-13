/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package obj.dichvu;

/**
 *
 * @author vuwin
 */
public class dichvu {
    private String TenDichVu;  
    private float Gia; 

    public dichvu() {
    }
   
    public dichvu(String TenDichVu, float Gia) {
        this.TenDichVu = TenDichVu;
        this.Gia = Gia;
    }

    public String getTenDichVu() {
        return TenDichVu;
    }

    public void setTenDichVu(String TenDichVu) {
        this.TenDichVu = TenDichVu;
    }

    public float getGia() {
        return Gia;
    }

    public void setGia(float Gia) {
        this.Gia = Gia;
    }
    
}
