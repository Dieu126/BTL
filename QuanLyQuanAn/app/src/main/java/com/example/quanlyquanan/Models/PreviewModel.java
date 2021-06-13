package com.example.quanlyquanan.Models;

public class PreviewModel {
    public PreviewModel(String ten, int SL) {
        Ten = ten;
        this.SL = SL;
    }

    @Override
    public String toString() {
        return Ten+" - "+SL;
    }

    public String Ten;
    public int SL;

    public PreviewModel() {
    }
}
