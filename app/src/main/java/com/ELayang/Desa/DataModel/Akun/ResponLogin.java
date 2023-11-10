package com.ELayang.Desa.DataModel.Akun;

import java.util.List;

public class ResponLogin {


    public int kode;

    public String pesan;
    private List<ModelLogin> data;

    public List<ModelLogin> getData() {
        return data;
    }

    public void setData(List<ModelLogin> data) {
        this.data = data;
    }

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
        this.pesan = pesan;
    }
}
