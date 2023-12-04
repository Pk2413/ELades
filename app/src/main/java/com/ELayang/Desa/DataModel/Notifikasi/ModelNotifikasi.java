package com.ELayang.Desa.DataModel.Notifikasi;

import java.util.ArrayList;

public class ModelNotifikasi {


    private String id;
    private String nopengajuan;
    private String kode;
    private String tanggal;
    private String status;
    private String alasan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }
}
