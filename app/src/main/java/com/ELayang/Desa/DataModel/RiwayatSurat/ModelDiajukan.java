package com.ELayang.Desa.DataModel.RiwayatSurat;

public class ModelDiajukan {
    String id, kode_surat, nik, nama, no_pengajuan,tanggal,status;

    public ModelDiajukan(String id, String kode_surat, String nik, String nama, String no_pengajuan, String tanggal, String status) {
        this.id = id;
        this.kode_surat = kode_surat;
        this.nik = nik;
        this.nama = nama;
        this.no_pengajuan = no_pengajuan;
        this.tanggal = tanggal;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKode_surat() {
        return kode_surat;
    }

    public void setKode_surat(String kode_surat) {
        this.kode_surat = kode_surat;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_pengajuan() {
        return no_pengajuan;
    }

    public void setNo_pengajuan(String no_pengajuan) {
        this.no_pengajuan = no_pengajuan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
