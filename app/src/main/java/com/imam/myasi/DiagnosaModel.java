package com.imam.myasi;


import java.sql.Date;

public class DiagnosaModel {

    private String judul;
    private String tanggal;
    private int id;


    public DiagnosaModel(){}

    public DiagnosaModel(String judul, int id) {
        this.judul = judul;
        this.tanggal = tanggal;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
