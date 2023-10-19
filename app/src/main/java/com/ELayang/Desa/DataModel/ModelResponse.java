package com.ELayang.Desa.DataModel;

import java.util.List;

public class ModelResponse {


    public int kode;

    public String pesan;
    private List<ModelUsers> data;

    public List<ModelUsers> getData() {
        return data;
    }

    public void setData(List<ModelUsers> data) {
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
