package com.example.quanlyquanan;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlyquanan.Adapter.ThucDonAdapter;
import com.example.quanlyquanan.Models.CHITIETHD;
import com.example.quanlyquanan.Models.HOADON;
import com.example.quanlyquanan.Models.MONAN;
import com.example.quanlyquanan.Models.PreviewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DatMonActivity extends AppCompatActivity {

    ListView lstDatMon, lstDaDat;
    ArrayList<PreviewModel> previewModel;
    TextView t1;
    Button btnOK, btnHuy;
    HOADON h;
    ArrayList<CHITIETHD> details;
    Boolean dup;
    SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_mon);
        setTitle("Chọn Món Ăn");
        lstDatMon= findViewById(R.id.lstDatMon);
        lstDaDat= findViewById(R.id.lstDaDat);


        final ArrayList<MONAN> monans= new ArrayList<MONAN>();
        Cursor cursorMA= com.example.quanlyquanan.MainActivity.db.GetData("SELECT * FROM MONAN");
        while (cursorMA.moveToNext())
        {
            MONAN tmp= new MONAN();
            tmp.ID=cursorMA.getString(0);
            tmp.TenMon=cursorMA.getString(1);
            tmp.DVT=cursorMA.getString(2);
            tmp.Gia=Double.parseDouble(cursorMA.getString(3));
            monans.add(tmp);
        }

        ThucDonAdapter adapter= new ThucDonAdapter(com.example.quanlyquanan.DatMonActivity.this, R.layout.row_monan, monans);
        lstDatMon.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        previewModel= new ArrayList<PreviewModel>();
        final ArrayAdapter<PreviewModel> adapter1= new ArrayAdapter<PreviewModel>(com.example.quanlyquanan.DatMonActivity.this, android.R.layout.simple_list_item_1, previewModel);
        lstDaDat.setAdapter(adapter1);
        Button btnTaoHD= findViewById(R.id.btnTaoHD);
        btnTaoHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText soban= findViewById(R.id.txtSoBan);
                if(soban.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(com.example.quanlyquanan.DatMonActivity.this, "Bạn phải nhập số bàn", Toast.LENGTH_LONG).show();
                    return;
                }
                if(details.size()<1)
                {
                    Toast.makeText(com.example.quanlyquanan.DatMonActivity.this, "Bạn phải chọn ít nhất 1 món", Toast.LENGTH_LONG).show();
                    return;
                }
                h.SoBan= Integer.parseInt(soban.getText().toString());
                com.example.quanlyquanan.MainActivity.db.AddHoaDon(h);
                for(int i=0;i<details.size();i++)
                {
                    com.example.quanlyquanan.MainActivity.db.AddChiTietHD(details.get(i));
                }
                Toast.makeText(com.example.quanlyquanan.DatMonActivity.this, "Xong", Toast.LENGTH_LONG).show();
                Intent intent3= new Intent(com.example.quanlyquanan.DatMonActivity.this, ChuaThanhToanActivity.class);
                //  intent.putExtra("lstMA", monans);
                startActivity(intent3);
            }
        });

        details= new ArrayList<>();
        h = new HOADON();

        try {
            h.NgayBan= curFormater.parse(curFormater.format(Calendar.getInstance().getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        h.TrangThai= -1; //chua thanh toan


        lstDatMon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final MONAN m= monans.get(i);


                //Tao Hoa don

               final Dialog dialog= new Dialog(com.example.quanlyquanan.DatMonActivity.this);

               dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.input_layout);
                t1= dialog.findViewById(R.id.txtTenMonPreview);
                btnOK= dialog.findViewById(R.id.btnOK);
                btnHuy= dialog.findViewById(R.id.btnHuy);
                t1.setText(m.TenMon);
                dialog.show();

               btnHuy.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       dialog.dismiss();
                   }
               });
               btnOK.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       dup= false;
                       EditText t2= dialog.findViewById(R.id.txtSlDat);
                       CHITIETHD c= new CHITIETHD();
                       c.SoLuong= Integer.parseInt(t2.getText()+"");
                       c.HoaDon_ID= h.ID;
                       c.MonAn_ID= m.ID;
                       c.Gia= m.Gia;
                       for(int i=0;i<details.size();i++)
                       {
                           CHITIETHD x= details.get(i);
                           PreviewModel p= previewModel.get(i);
                           if(x.HoaDon_ID.equalsIgnoreCase(h.ID) && x.MonAn_ID.equalsIgnoreCase(m.ID))
                           {
                                p.SL+=c.SoLuong;
                                previewModel.remove(previewModel.get(i));
                                previewModel.add(i, p);
                               x.SoLuong+= c.SoLuong;
                               details.remove(details.get(i));
                               details.add(i, x);
                               dup=true;
                               break;
                           }
                       }
                       if(dup==false)
                       {
                           details.add(c);
                           previewModel.add(new PreviewModel(m.TenMon, Integer.parseInt(t2.getText().toString())));
                       }
                       adapter1.notifyDataSetChanged();
                       dialog.dismiss();
                   }
               });


            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.navTroVe:
                Intent intent= new Intent(com.example.quanlyquanan.DatMonActivity.this, ChuaThanhToanActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}