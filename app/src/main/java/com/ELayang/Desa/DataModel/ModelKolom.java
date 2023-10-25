package com.ELayang.Desa.DataModel;

public class ModelKolom {
    private String namaKolom;

    public ModelKolom(String namaKolom, String nilaiKolom) {
        this.namaKolom = namaKolom;
        this.nilaiKolom = nilaiKolom;
    }

    public String getNamaKolom() {
        return namaKolom;
    }

    public void setNamaKolom(String namaKolom) {
        this.namaKolom = namaKolom;
    }

    public String getNilaiKolom() {
        return nilaiKolom;
    }

    public void setNilaiKolom(String nilaiKolom) {
        this.nilaiKolom = nilaiKolom;
    }

    private String nilaiKolom;
}
