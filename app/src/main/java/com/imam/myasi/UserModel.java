package com.imam.myasi;

import android.widget.EditText;

public class UserModel {

    private String nama;
    private String ktp;
    private String hp;
    private String email;
    private String password;

    public UserModel(){}

    public UserModel(String nama, String ktp, String hp, String email, String password) {
        this.nama = nama;
        this.ktp = ktp;
        this.hp = hp;
        this.email = email;
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
