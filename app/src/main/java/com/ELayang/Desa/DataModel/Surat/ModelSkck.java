package com.ELayang.Desa.DataModel.Surat;

public class ModelSkck {

    private String nama;
    private String nik;
    private String tempat_tanggal_lahir;
    private String kebangsaan;
    private String agama;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getTempat_tanggal_lahir() {
        return tempat_tanggal_lahir;
    }

    public void setTempat_tanggal_lahir(String tempat_tanggal_lahir) {
        this.tempat_tanggal_lahir = tempat_tanggal_lahir;
    }

    public String getKebangsaan() {
        return kebangsaan;
    }

    public void setKebangsaan(String kebangsaan) {
        this.kebangsaan = kebangsaan;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getStatus_perkawinan() {
        return status_perkawinan;
    }

    public void setStatus_perkawinan(String status_perkawinan) {
        this.status_perkawinan = status_perkawinan;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getTempat_tinggal() {
        return tempat_tinggal;
    }

    public void setTempat_tinggal(String tempat_tinggal) {
        this.tempat_tinggal = tempat_tinggal;
    }

    private String status_perkawinan;
    private String pekerjaan;
    private String tempat_tinggal;
}
