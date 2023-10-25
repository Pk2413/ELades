package com.ELayang.Desa.DataModel.Lupa_Password;

import java.util.ArrayList;

public class ResponPassword1 {
    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public ArrayList<ModelPassword1> getData() {
        return data;
    }

    public void setData(ArrayList<ModelPassword1> data) {
        this.data = data;
    }

    String pesan;
    public int kode;
    ArrayList<ModelPassword1> data;
}
