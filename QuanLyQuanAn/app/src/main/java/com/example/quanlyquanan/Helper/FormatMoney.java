package com.example.quanlyquanan.Helper;

import java.text.DecimalFormat;

public class FormatMoney {
    static DecimalFormat formatter = new DecimalFormat("###,###,###");
    public static String GetMoneyVND(Double money)
    {
        return formatter.format(money)+" đ";
    }
}
