package com.example.quanlyquanan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlyquanan.Models.MONAN;

import java.util.ArrayList;
import java.util.UUID;

public class ThemMonActivity extends AppCompatActivity {

    Spinner spn;
    Button btnThem;
    TextView txtMon, txtGia;
    public static ArrayAdapter<String> adapter;
    public static ArrayList<String> DVTs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_mon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Thêm Món Ăn");
        DVTs= new ArrayList<>();
        DVTs.add("Bát");
        DVTs.add("Đĩa");
        DVTs.add("Lon");
        DVTs.add("Chai");
        DVTs.add("Cốc");
        spn= findViewById(R.id.spnDVT);
        txtMon= findViewById(R.id.txtTenMon);
        txtGia= findViewById(R.id.txtGia);
        btnThem= findViewById(R.id.btnThemMon);

        adapter= new ArrayAdapter(ThemMonActivity.this, android.R.layout.simple_spinner_dropdown_item, DVTs);
        spn.setAdapter(adapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MONAN x = new MONAN();
                //tao doi tuong UUID và lấy giá trị random
                UUID idOne = UUID.randomUUID();
                x.ID= idOne.toString();
                x.TenMon= txtMon.getText()+"";
                x.Gia= Double.parseDouble(txtGia.getText().toString());
                x.DVT= spn.getSelectedItem().toString();
                MainActivity.db.AddMonAn(x);
                Intent intent= new Intent(ThemMonActivity.this, ThucDonActivity.class);
                startActivity(intent);
            }
        });


    }


}