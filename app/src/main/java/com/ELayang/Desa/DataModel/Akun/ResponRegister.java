package com.ELayang.Desa.DataModel.Akun;

import java.util.ArrayList;

public class ResponRegister {


    public int kode;
    String Pesan;

    ArrayList <ModelRegister> dataRegister;

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

    public ArrayList<ModelRegister> getDataRegister() {
        return dataRegister;
    }

    public void setDataRegister(ArrayList<ModelRegister> dataRegister) {
        this.dataRegister = dataRegister;
    }
}


