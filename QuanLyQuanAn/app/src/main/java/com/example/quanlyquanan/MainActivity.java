package com.example.quanlyquanan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlyquanan.Models.MONAN;

import java.util.ArrayList;

import static com.example.quanlyquanan.Database.COL_DVT;
import static com.example.quanlyquanan.Database.COL_Gia;
import static com.example.quanlyquanan.Database.COL_ID;
import static com.example.quanlyquanan.Database.COL_NgayBan;
import static com.example.quanlyquanan.Database.COL_SoBan;
import static com.example.quanlyquanan.Database.COL_SoLuong;
import static com.example.quanlyquanan.Database.COL_TenMon;
import static com.example.quanlyquanan.Database.COL_TrangThai;

public class MainActivity extends AppCompatActivity {

    public static Database db;

    public void ThemMonAn()
    {
        db.AddMonAn(new MONAN("m1", "Phở Gà", "Bát", 20000.0));
        db.AddMonAn(new MONAN("m2", "Phở Bò", "Bát", 30000.0));
        db.AddMonAn(new MONAN("m3", "Phở Vịt", "Bát", 25000.0));
        db.AddMonAn(new MONAN("m4", "Cháo Gà", "Bát", 25000.0));
        db.AddMonAn(new MONAN("m5", "Chân gà xào xả ớt", "Đĩa", 30000.0));
    }
    Cursor cursorMA;
    public static ArrayList<String> DVTs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DVTs= new ArrayList<>();
        DVTs.add("Bát");
        DVTs.add("Đĩa");
        DVTs.add("Lon");
        DVTs.add("Chai");
        DVTs.add("Cốc");


        //deleteDatabase("QLQA.sqlite");
        db= new Database(MainActivity.this, "QLQA.sqlite", null, 1);
        db.ExcuteNonQuery("CREATE TABLE IF NOT EXISTS MONAN ("+COL_ID+" TEXT PRIMARY KEY, "+COL_TenMon+" TEXT, "+COL_DVT+" TEXT,"+COL_Gia+" DOUBLE)");
        db.ExcuteNonQuery("CREATE TABLE IF NOT EXISTS HOADON("+COL_ID+" TEXT PRIMARY KEY, "+COL_NgayBan+" TEXT, "+COL_SoBan+" INTEGER, "+COL_TrangThai+" INTEGER)");
        db.ExcuteNonQuery("CREATE TABLE IF NOT EXISTS CHITIETHD ( " +
                "HoaDon_ID TEXT, " +
                "MonAn_ID TEXT , " +
                ""+COL_SoLuong+" INTEGER," +
                ""+COL_Gia+" DOUBLE,  FOREIGN KEY(HoaDon_ID) REFERENCES HOADON("+COL_ID+"), FOREIGN KEY(MonAn_ID) REFERENCES MONAN("+COL_ID+")" +
                "PRIMARY KEY(HoaDon_ID, MonAn_ID) )");

        ThemMonAn();



        Intent intent3= new Intent(MainActivity.this, ChuaThanhToanActivity.class);
        startActivity(intent3);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.navThucDon:
                Intent intent= new Intent(MainActivity.this, ThucDonActivity.class);
                //  intent.putExtra("lstMA", monans);
                startActivity(intent);
                break;
            case R.id.navDatMon:
                Intent intent2= new Intent(MainActivity.this, DatMonActivity.class);
                //  intent.putExtra("lstMA", monans);
                startActivity(intent2);
                break;

            case R.id.navBanHang:
                Intent intent3= new Intent(MainActivity.this, ChuaThanhToanActivity.class);
                //  intent.putExtra("lstMA", monans);
                startActivity(intent3);
                break;
            case  R.id.navBaoCao:
                Intent intent4= new Intent(MainActivity.this, ThongKeActivity.class);
                //  intent.putExtra("lstMA", monans);
                startActivity(intent4);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}