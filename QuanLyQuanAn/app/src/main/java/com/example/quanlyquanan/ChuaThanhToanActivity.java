package com.example.quanlyquanan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlyquanan.Adapter.ChuaThanhToanAdapter;
import com.example.quanlyquanan.Models.HOADON;
import com.example.quanlyquanan.Models.HOADONMODEL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.example.quanlyquanan.Database.COL_TrangThai;

public class ChuaThanhToanActivity extends AppCompatActivity {
    ListView lst;
    TextView t1;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ArrayList<HOADON> hoadons;
    ChuaThanhToanAdapter adapter;

    public void LoadHoaDon() {

        Cursor cursor =MainActivity.db.GetData("SELECT * FROM HOADON WHERE " + COL_TrangThai + "= -1");
        hoadons.clear();
        while (cursor.moveToNext()) {
            HOADON h1 = new HOADON();
            h1.ID = cursor.getString(0);
            try {
                h1.NgayBan = dateFormat.parse(cursor.getString(1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            h1.SoBan = Integer.parseInt(cursor.getString(2));
            h1.TrangThai = Integer.parseInt(cursor.getString(3));
            hoadons.add(h1);
        }
        lst.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chua_thanh_toan);
        //setTitle("Các Món Đã Đặt");

        lst = findViewById(R.id.lstChuaThanhToan);
        hoadons = new ArrayList<HOADON>();
        adapter = new ChuaThanhToanAdapter(com.example.quanlyquanan.ChuaThanhToanActivity.this, R.layout.hoadon_row, hoadons);
        LoadHoaDon();

    }

    public void XoaHoaDon(String ID) {
        com.example.quanlyquanan.MainActivity.db.ExcuteNonQuery("DELETE from HOADON WHERE ID='" + ID + "'");
        LoadHoaDon();
        Toast.makeText(com.example.quanlyquanan.ChuaThanhToanActivity.this, "Đã Xóa", Toast.LENGTH_LONG).show();
    }


    public void XacNhanThanhToan(HOADON hd, Double gia, ArrayList<HOADONMODEL> hoadonmodels) {
        Intent intent = new Intent(com.example.quanlyquanan.ChuaThanhToanActivity.this, ThanhToanActivity.class);
        intent.putExtra("hoadon", hd);
        intent.putExtra("TongTien", gia);
        intent.putExtra("hoadonmodels", hoadonmodels);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navThucDon:
                Intent intent = new Intent(com.example.quanlyquanan.ChuaThanhToanActivity.this, com.example.quanlyquanan.ThucDonActivity.class);
                //  intent.putExtra("lstMA", monans);
                startActivity(intent);
                break;
            case R.id.navDatMon:
                Intent intent2 = new Intent(com.example.quanlyquanan.ChuaThanhToanActivity.this, com.example.quanlyquanan.DatMonActivity.class);
                //  intent.putExtra("lstMA", monans);
                startActivity(intent2);
                break;

            case R.id.navBanHang:
                Intent intent3 = new Intent(com.example.quanlyquanan.ChuaThanhToanActivity.this, com.example.quanlyquanan.ChuaThanhToanActivity.class);
            //  intent.putExtra("lstMA", monans);
            startActivity(intent3);
            break;
            case R.id.navBaoCao:
                Intent intent4 = new Intent(com.example.quanlyquanan.ChuaThanhToanActivity.this, com.example.quanlyquanan.ThongKeActivity.class);
                //  intent.putExtra("lstMA", monans);
                startActivity(intent4);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}