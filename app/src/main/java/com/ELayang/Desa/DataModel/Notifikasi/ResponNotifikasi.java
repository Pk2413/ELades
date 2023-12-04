package com.ELayang.Desa.DataModel.Notifikasi;

import java.util.ArrayList;

public class ResponNotifikasi {

    private int kode;
    private String status;
    private String nopengajuan;
    private String pesan;
    private String alasan;
    private String tanggal;
    private String jam;

    ArrayList<ModelNotifikasi> data;

    public String getNopengajuan() {
        return nopengajuan;
    }

    public void setNopengajuan(String nopengajuan) {
        this.nopengajuan = nopengajuan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ModelNotifikasi> getData() {
        return data;
    }

    public void setData(ArrayList<ModelNotifikasi> data) {
        this.data = data;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
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

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }
}
