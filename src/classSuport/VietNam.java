/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classSuport;

import java.util.ArrayList;
import java.util.List;

public class VietNam {
    private static List<String> xa = new ArrayList<String>();
    private static List<String> huyen = new ArrayList<String>();
    private static List<String> tinh = new ArrayList<String>();
    
    public static List<String> gettDatatinh(){
        tinh.add("a");
        return tinh;
    }
    public static List<String> getDatahuyen(){
        return huyen;
    }
    public static List<String> getDataxa(){
        return xa;
    }
}
