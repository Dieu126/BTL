package com.example.quanlyquanan.Models;

import java.io.Serializable;

public class CHITIETHD implements Serializable {


    public CHITIETHD(String monAn_ID, String hoaDon_ID, Integer soLuong, Double gia) {
        MonAn_ID = monAn_ID;
        HoaDon_ID = hoaDon_ID;
        SoLuong = soLuong;
        Gia = gia;
    }

    public String MonAn_ID;
    public String HoaDon_ID;
    public Integer SoLuong;
    public Double Gia;



    public CHITIETHD() {
    }
}
