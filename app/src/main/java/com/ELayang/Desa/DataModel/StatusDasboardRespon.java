package com.ELayang.Desa.DataModel;

import java.util.ArrayList;

public class StatusDasboardRespon {
    boolean kode;
    String pesan;

    ArrayList <StatusDasboardModel> data;

    public ArrayList<StatusDasboardModel> getData() {
        return data;
    }

    public void setData(ArrayList<StatusDasboardModel> data) {
        this.data = data;
    }

    public boolean isKode() {
        return kode;
    }

    public void setKode(boolean kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}
