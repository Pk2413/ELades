package com.ELayang.Desa.DataModel.Akun;

public class    ModelLogin {

    private String username;
    private String password;
    private String no_tlp;
    private String email;
    private String nama;
    private String kode_otp;

    public String getUsername() {
        return username;
    }

    public String setUsername(String username) {
        this.username = username;
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String setPassword(String password) {
        this.password = password;

        return password;
    }
    public String getNo_tlp() {
        return no_tlp;
    }

    public void setNo_tlp(String no_tlp) {
        this.no_tlp = no_tlp;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKode_otp() {
        return kode_otp;
    }

    public void setKode_otp(String kode_otp) {
        this.kode_otp = kode_otp;
    }
}
