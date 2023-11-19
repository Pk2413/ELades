package com.ELayang.Desa.DataModel.Surat;

import java.util.ArrayList;

public class ResponSktm {
    boolean kode;

    public boolean isKode() {
        return kode;
    }

    public void setKode(boolean kode) {
        this.kode = kode;
    }

    String Pesan;


    public String getPesan() {
        return Pesan;
    }

    public void setPesan(String pesan) {
        Pesan = pesan;
    }

    ArrayList<ModelSktm> data;

    public ArrayList<ModelSktm> getData() {
        return data;
    }

    public void setData(ArrayList<ModelSktm> data) {
        this.data = data;
    }
}
