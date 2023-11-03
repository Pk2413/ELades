package com.ELayang.Desa.DataModel.RiwayatSurat;

import java.util.List;

public class ResponDiajukan {
    int kode;
    String pesan;
    List<ModelDiajukan> data;

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

    public List<ModelDiajukan> getData() {
        return data;
    }

    public void setData(List<ModelDiajukan> data) {
        this.data = data;
    }
}
