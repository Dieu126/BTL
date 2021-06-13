package com.example.quanlyquanan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlyquanan.Adapter.ThucDonAdapter;
import com.example.quanlyquanan.Models.MONAN;

import java.util.ArrayList;

public class ThucDonActivity extends AppCompatActivity {

    ListView lstMonAn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuc_don);
        setTitle("Danh Sách Món Ăn");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final ArrayList<MONAN> monans= new ArrayList<MONAN>();
        Cursor cursorMA= MainActivity.db.GetData("SELECT * FROM MONAN");
        while (cursorMA.moveToNext())
        {
            MONAN tmp= new MONAN();
            tmp.ID=cursorMA.getString(0);
            tmp.TenMon=cursorMA.getString(1);
            tmp.DVT=cursorMA.getString(2);
            tmp.Gia=Double.parseDouble(cursorMA.getString(3));
            monans.add(tmp);
        }

        ThucDonAdapter adapter= new ThucDonAdapter(ThucDonActivity.this, R.layout.row_monan, monans);
        lstMonAn= findViewById(R.id.lstMonAn);
        lstMonAn.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        lstMonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ThucDonActivity.this,SuaMon.class);
                intent.putExtra("Mon", monans.get(i));
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.thucdon_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.navThemMon:
                Intent intent6= new Intent(ThucDonActivity.this,ThemMonActivity.class);
                //  intent.putExtra("lstMA", monans);
                startActivity(intent6);
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}