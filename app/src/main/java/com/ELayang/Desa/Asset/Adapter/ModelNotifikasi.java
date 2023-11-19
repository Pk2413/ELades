package com.ELayang.Desa.Asset.Adapter;

public class ModelNotifikasi {

    private String nopengajuan;
    private String kode;
    private String tanggal;
    private String keterangan;


    public ModelNotifikasi(String nopengajuan, String kode, String tanggal, String keterangan) {
        this.nopengajuan = nopengajuan;
        this.kode = kode;
        this.tanggal = tanggal;
        this.keterangan = keterangan;
    }

    public String getNopengajuan() {
        return nopengajuan;
    }

    public void setNopengajuan(String nopengajuan) {
        this.nopengajuan = nopengajuan;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
