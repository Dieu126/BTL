package com.example.quanlyquanan.Models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HOADON implements Serializable {
    public static int id_inc=0;
    public String ID;
    public Date NgayBan;
    public Integer SoBan;
    public Integer TrangThai;

    Calendar calendar= Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    public HOADON(){
        id_inc+=1;
        ID=id_inc+""+dateFormat.format(calendar.getTime());
    }
    public HOADON(String ID, Date ngayBan, Integer soBan, Integer trangThai) {
        this.ID = ID;
        NgayBan = ngayBan;
        TrangThai = trangThai;
        SoBan = soBan;
    }
}
