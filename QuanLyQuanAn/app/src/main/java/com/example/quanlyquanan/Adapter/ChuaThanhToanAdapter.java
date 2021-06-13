package com.example.quanlyquanan.Adapter;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.quanlyquanan.ChuaThanhToanActivity;
import com.example.quanlyquanan.Helper.FormatMoney;
import com.example.quanlyquanan.MainActivity;
import com.example.quanlyquanan.Models.HOADON;
import com.example.quanlyquanan.Models.HOADONMODEL;
import com.example.quanlyquanan.R;

import java.util.ArrayList;

public class ChuaThanhToanAdapter extends BaseAdapter {

    public ChuaThanhToanAdapter(ChuaThanhToanActivity context, int layout, ArrayList<HOADON> lstHD) {
        this.context = context;
        Layout = layout;
        this.lstHD = lstHD;
    }

    public ChuaThanhToanAdapter() {
    }

    ChuaThanhToanActivity context;
    int Layout;
    ArrayList<HOADON> lstHD;
    @Override
    public int getCount() {
        return lstHD.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ChuaThanhToanHolder
    {
        TextView MonAn;
        TextView Gia;
        TextView SoBan;
        Button Huy, ThanhToan;

    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ChuaThanhToanHolder holder;
        if(view==null)
        {
            LayoutInflater inflater= context.getLayoutInflater();
            view = inflater.inflate(Layout, null);
            holder=new ChuaThanhToanHolder();
            holder.MonAn= view.findViewById(R.id.tvMonAn);
            holder.Gia= view.findViewById(R.id.tvGia);
            holder.SoBan= view.findViewById(R.id.tvSoBan);
            holder.Huy= view.findViewById(R.id.btnHuy);
            holder.ThanhToan= view.findViewById(R.id.btnThanhToan);
            view.setTag(holder);
        }
        else {
            holder= (ChuaThanhToanHolder) view.getTag();
        }

        final HOADON hd= lstHD.get(i);

        final ArrayList<HOADONMODEL> hoadonmodels= new ArrayList<HOADONMODEL>();
        Cursor cursor = MainActivity.db.GetData("SELECT m.ID, m.TenMon, c1.SoLuong, c1.Gia FROM " +
                "CHITIETHD c1 INNER JOIN HOADON h ON c1.HoaDon_ID == h.ID " +
                "INNER JOIN MONAN m ON c1.MonAn_ID== m.ID " +
                "WHERE c1.HoaDon_ID = '"+hd.ID+"'");
        String tenmon="";
        Double gia=0.0;
        while(cursor.moveToNext())
        {
            HOADONMODEL c1= new HOADONMODEL();
            c1.MonAn_ID= cursor.getString(0);
            c1.HoaDon_ID= hd.ID;
            c1.TenMon = cursor.getString(1);
            c1.SoLuong= Integer.parseInt(cursor.getString(2));
            c1.Gia = Double.parseDouble(cursor.getString(3));
            tenmon+= c1.TenMon +" ("+FormatMoney.GetMoneyVND(c1.Gia)+" x "+c1.SoLuong+") \n";
            gia+=c1.Gia*c1.SoLuong;
            hoadonmodels.add(c1);
        }

        holder.MonAn.setText(tenmon);

        holder.Gia.setText(FormatMoney.GetMoneyVND(gia));
        holder.SoBan.setText(hd.SoBan.toString());

        holder.Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.XoaHoaDon(hd.ID);
            }
        });

        final Double finalGia = gia;
        holder.ThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.XacNhanThanhToan(hd, finalGia, hoadonmodels);
            }
        });
        return view;
    }

}
