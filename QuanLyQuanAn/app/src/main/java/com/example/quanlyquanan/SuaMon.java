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


public class SuaMon extends AppCompatActivity {

    Spinner spn;
    Button btnXoa, btnSua;
    TextView txtMon, txtGia;
    public ArrayList<String> DVTs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_mon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final MONAN monan = (MONAN) getIntent().getSerializableExtra("Mon");
        spn= findViewById(R.id.spnDVT);
        txtMon= findViewById(R.id.txtTenMon);
        txtGia= findViewById(R.id.txtGia);
        btnXoa= findViewById(R.id.btnXoa);
        btnSua= findViewById(R.id.btnSua);

        txtMon.setText(monan.TenMon);
        txtGia.setText(monan.Gia+"");
        ArrayAdapter adapter1= new ArrayAdapter(SuaMon.this, android.R.layout.simple_spinner_dropdown_item, MainActivity.DVTs);
        spn.setAdapter(adapter1);
        int c= MainActivity.DVTs.size();
        for(int i=0;i< c;i++)
        {
            if(monan.DVT.equalsIgnoreCase(MainActivity.DVTs.get(i)))
            {
                spn.setSelection(i);
                break;
            }
        }
        txtMon.setText(monan.TenMon);

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.db.RemoveMonAn(monan.ID);
                Intent intent= new Intent(SuaMon.this, ThucDonActivity.class);
                startActivity(intent);
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MONAN upd= new MONAN();
                upd.ID= monan.ID;
                upd.TenMon= txtMon.getText()+"";
                upd.Gia= Double.parseDouble(txtGia.getText()+"");
                upd.DVT= spn.getSelectedItem().toString();
                MainActivity.db.UpdateMonAn(upd);
                Intent intent= new Intent(SuaMon.this, ThucDonActivity.class);
                startActivity(intent);
            }
        });




    }


}