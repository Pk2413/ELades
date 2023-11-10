package com.ELayang.Desa.DataModel.Surat;

import java.util.ArrayList;

public class ResponSkck {
    public boolean kode;
    String pesan;
    ArrayList<ModelSkck> data;

    public boolean isKode() {
        return kode;
    }

    public void setKode(boolean kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public ArrayList<ModelSkck> getData() {
        return data;
    }

    public void setData(ArrayList<ModelSkck> data) {
        this.data = data;
    }
}
