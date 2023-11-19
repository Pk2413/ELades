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

   private String pesan;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    ArrayList<ModelSktm> data;

    public ArrayList<ModelSktm> getData() {
        return data;
    }

    public void setData(ArrayList<ModelSktm> data) {
        this.data = data;
    }
}
