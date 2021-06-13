package com.example.quanlyquanan.Models;

import java.io.Serializable;

public class MONAN implements Serializable {

    public MONAN() {

    }
    public MONAN(String ID, String tenMon, String DVT, Double gia) {
        this.ID = ID;
        TenMon = tenMon;
        this.DVT = DVT;
        Gia = gia;
    }

    public String ID;
    public String TenMon;
    public String DVT;
    public Double Gia;
}
