package com.ELayang.Desa.DataModel.Register;

import java.util.ArrayList;

public class ResponRegister2   {
    public int kode;
    String Pesan;

    ArrayList<ModelRegister2> data;

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return Pesan;
    }

    public void setPesan(String pesan) {
        Pesan = pesan;
    }

    public ArrayList<ModelRegister2> getData() {
        return data;
    }

    public void setData(ArrayList<ModelRegister2> data) {
        this.data = data;
    }
}
