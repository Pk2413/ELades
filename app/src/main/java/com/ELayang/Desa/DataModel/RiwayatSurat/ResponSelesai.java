package com.ELayang.Desa.DataModel.RiwayatSurat;

import java.util.List;

public class ResponSelesai {

    int kode;
    String pesan;
    List<ModelSelesai> data;

    public List<ModelSelesai> getData() {
        return data;
    }

    public void setData(List<ModelSelesai> data) {
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
