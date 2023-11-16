package com.ELayang.Desa.DataModel;

public class StatusDasboardModel {

    String Proses;
    String Selesai;
    String Masuk;
    String Tolak;

    public String getMasuk() {
        return Masuk;
    }

    public void setMasuk(String masuk) {
        Masuk = masuk;
    }

    public String getTolak() {
        return Tolak;
    }

    public void setTolak(String tolak) {
        Tolak = tolak;
    }

    public String getProses() {
        return Proses;
    }

    public void setProses(String proses) {
        Proses = proses;
    }

    public String getSelesai() {
        return Selesai;
    }

    public void setSelesai(String selesai) {
        Selesai = selesai;
    }
}
