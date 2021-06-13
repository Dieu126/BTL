package com.example.quanlyquanan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.quanlyquanan.Models.CHITIETHD;
import com.example.quanlyquanan.Models.HOADON;
import com.example.quanlyquanan.Models.MONAN;

public class Database extends SQLiteOpenHelper {
    public final static String COL_ID="ID";
    public final static String COL_TenMon="TenMon";
    public final static String COL_DVT="DVT";
    public final static String COL_Gia="Gia";
    public final static String COL_NgayBan="NgayBan";
    public final static String COL_SoBan="SoBan";
    public final static String COL_SoLuong="SoLuong";
    public final static String COL_TrangThai="TrangThai";
    public final static String COL_ID_MonAn="ID_MonAn";

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void AddMonAn(MONAN x)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COL_ID, x.ID);
        values.put(COL_TenMon, x.TenMon);
        values.put(COL_DVT, x.DVT);
        values.put(COL_Gia, x.Gia);
        db.insert("MONAN", null, values);
        db.close();
    }

    public void UpdateMonAn(MONAN x)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ExcuteNonQuery("UPDATE MONAN set "+COL_TenMon+"= '"+x.TenMon+"', "+COL_Gia+"= "+x.Gia+", "+COL_DVT+"='"+x.DVT+"' where ID='"+x.ID+"'");
    }

    public void RemoveMonAn(String ID)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ExcuteNonQuery("DELETE FROM MONAN where ID='"+ID+"'");
    }

    public void AddHoaDon(HOADON x)
    {

        SQLiteDatabase db= this.getWritableDatabase();
        ExcuteNonQuery("INSERT INTO HOADON VALUES('"+x.ID+"', datetime('now', '-0 day', 'localtime'), "+x.SoBan+", "+x.TrangThai+")");
    }

    public void AddChiTietHD(CHITIETHD x)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("MonAn_ID", x.MonAn_ID);
        values.put("HoaDon_ID", x.HoaDon_ID);
        values.put(COL_SoLuong, x.SoLuong);
        values.put(COL_Gia, x.Gia);
        db.insert("CHITIETHD", null, values);
        db.close();
    }



    public void ExcuteNonQuery(String query)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL(query);
    }

    public Cursor GetData(String query)
    {
        SQLiteDatabase db= this.getReadableDatabase();
        return db.rawQuery(query, null);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
           // sqLiteDatabase.execSQL("CREATE TABLE MONAN ('"+COL_ID+"', '"+COL_TenMon+"', '"+COL_DVT+"','"+COL_Gia+"')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
