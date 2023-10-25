package com.ELayang.Desa.DataModel.Register;

import java.util.ArrayList;

public class ResponRegister3 {
    public int kode;
    String Pesan;

    ArrayList<ModelRegister3> data;

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

    public ArrayList<ModelRegister3> getData() {
        return data;
    }

    public void setData(ArrayList<ModelRegister3> data) {
        this.data = data;
    }
}
