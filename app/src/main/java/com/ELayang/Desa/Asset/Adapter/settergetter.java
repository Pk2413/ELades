package com.ELayang.Desa.Asset.Adapter;

public class settergetter {

    private String nama;
    private String keterangan;

    public settergetter(String nama, String keterangan){
        this.nama = nama;
        this.keterangan = keterangan;
    }
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }


}
