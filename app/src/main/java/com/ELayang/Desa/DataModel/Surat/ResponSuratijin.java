package com.ELayang.Desa.DataModel.Surat;

import java.util.ArrayList;

public class ResponSuratijin {

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

    ArrayList<ModelSuratijin> data;

    public ArrayList<ModelSuratijin> getData() {
        return data;
    }

    public void setData(ArrayList<ModelSuratijin> data) {
        this.data = data;
    }
}
