package com.example.quanlyquanan.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlyquanan.Helper.FormatMoney;
import com.example.quanlyquanan.Models.MONAN;
import com.example.quanlyquanan.R;

import java.util.ArrayList;

public class ThucDonAdapter extends BaseAdapter {

    public ThucDonAdapter(Activity context, int layout, ArrayList<MONAN> lstMA) {
        this.context = context;
        Layout = layout;
        this.lstMA = lstMA;
    }
    public ThucDonAdapter(){}

    Activity context;
    int Layout;
    ArrayList<MONAN> lstMA;
    @Override
    public int getCount() {
        return lstMA.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ThucDonHolder
    {
        TextView TenMon;
        TextView Gia;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        ThucDonHolder holder;
        if(view==null)
        {
            LayoutInflater inflater= context.getLayoutInflater();
            view = inflater.inflate(Layout, null);
            holder=new ThucDonHolder();
            holder.TenMon= view.findViewById(R.id.lbTenMon);
            holder.Gia= view.findViewById(R.id.lbGia);
            view.setTag(holder);
        }
        else {
            holder= (ThucDonHolder) view.getTag();
        }


        MONAN m= lstMA.get(i);

        holder.TenMon.setText(m.TenMon);
        holder.Gia.setText("Giá: "+ FormatMoney.GetMoneyVND(m.Gia));

        return view;
    }
}
