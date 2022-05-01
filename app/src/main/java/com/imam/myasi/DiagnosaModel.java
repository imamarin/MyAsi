package com.imam.myasi;


import java.sql.Date;

public class DiagnosaModel {

    private String judul;
    private String tanggal;
    private int id;
    private String status;


    public DiagnosaModel(){}

    public DiagnosaModel(String judul, int id, String status) {
        this.judul = judul;
        this.tanggal = tanggal;
        this.id = id;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
