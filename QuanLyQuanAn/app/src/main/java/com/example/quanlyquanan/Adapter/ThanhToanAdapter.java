package com.example.quanlyquanan.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlyquanan.Helper.FormatMoney;
import com.example.quanlyquanan.Models.HOADONMODEL;
import com.example.quanlyquanan.R;
import com.example.quanlyquanan.ThanhToanActivity;

import java.util.ArrayList;

public class ThanhToanAdapter extends BaseAdapter {

    public ThanhToanAdapter(ThanhToanActivity context, int layout, ArrayList<HOADONMODEL> lstChitiet) {
        this.context = context;
        Layout = layout;
        this.lstChitiet = lstChitiet;
    }

    ThanhToanActivity context;
    int Layout;
    ArrayList<HOADONMODEL> lstChitiet;

    @Override
    public int getCount() {
        return lstChitiet.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ThanhToanHolder
    {
        TextView MonAn;
        TextView DonGia;
        TextView SoLuong;
        TextView ThanhTien;


    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ThanhToanHolder holder;
        if(view==null)
        {
            LayoutInflater inflater= context.getLayoutInflater();
            view = inflater.inflate(Layout, null);
            holder=new ThanhToanHolder();
            holder.MonAn= view.findViewById(R.id.tvTenMon);
            holder.SoLuong= view.findViewById(R.id.tvSoLuong);
            holder.DonGia= view.findViewById(R.id.tvDonGia);
            holder.ThanhTien= view.findViewById(R.id.tvThanhTien);
            view.setTag(holder);
        }
        else {
            holder= (ThanhToanHolder) view.getTag();
        }
        HOADONMODEL detail= lstChitiet.get(i);

        holder.MonAn.setText(detail.TenMon);
        holder.SoLuong.setText(detail.SoLuong+"");
        holder.ThanhTien.setText(FormatMoney.GetMoneyVND(detail.Gia* detail.SoLuong));
        holder.DonGia.setText(FormatMoney.GetMoneyVND(detail.Gia));

        return view;
    }
}
