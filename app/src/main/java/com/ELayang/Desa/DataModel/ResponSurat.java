package com.ELayang.Desa.DataModel;

import java.util.ArrayList;

public class ResponSurat {
    public int kode;
    String pesan;
    ArrayList <ModelSurat> dataSurat;

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        pesan = pesan;
    }

    public ArrayList<ModelSurat> getdataSurat() {
        return dataSurat;
    }

    public void setdataSurat(ArrayList<ModelSurat> dataSurat) {
        this.dataSurat = dataSurat;
    }
}
