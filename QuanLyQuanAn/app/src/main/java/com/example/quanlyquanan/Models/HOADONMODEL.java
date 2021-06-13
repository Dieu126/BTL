package com.example.quanlyquanan.Models;

import java.io.Serializable;

public class HOADONMODEL implements Serializable {


    public String MonAn_ID;
    public String HoaDon_ID;
    public String TenMon;
    public Integer SoLuong;
    public Double Gia;

    public HOADONMODEL() {
    }

    public HOADONMODEL(String monAn_ID, String hoaDon_ID, String tenMon, Integer soLuong, Double gia) {
        MonAn_ID = monAn_ID;
        HoaDon_ID = hoaDon_ID;
        TenMon = tenMon;
        SoLuong = soLuong;
        Gia = gia;
    }
}
