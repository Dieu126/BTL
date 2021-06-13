package com.example.quanlyquanan.Models;

import java.io.Serializable;

public class ChiTietModel implements Serializable {
    public ChiTietModel(String tenMon, Integer soLuong, Double donGia) {
        TenMon = tenMon;
        SoLuong = soLuong;
        DonGia = donGia;
    }

    public ChiTietModel() {
    }

    public String TenMon;
    public Integer SoLuong;
    public Double DonGia;
    public Double ThanhTien(){
        return SoLuong* DonGia;
    }
}
