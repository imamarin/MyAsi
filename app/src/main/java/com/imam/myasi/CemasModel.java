package com.imam.myasi;

public class CemasModel {

    private String pertanyaan;
    private String idPertanyaan;
    private String nilaiHasil;

    public CemasModel(){}

    public CemasModel(String pertanyaan, String idPertanyaan, String nilaiHasil) {
        this.pertanyaan = pertanyaan;
        this.idPertanyaan = idPertanyaan;
        this.nilaiHasil = nilaiHasil;
    }

    public String getIdPertanyaan() {
        return idPertanyaan;
    }

    public void setIdPertanyaan(String idpPertanyaan) {
        this.idPertanyaan = idpPertanyaan;
    }

    public String getNilaiHasil() {
        return nilaiHasil;
    }

    public void setNilaiHasil(String nilaiHasil) {
        this.nilaiHasil = nilaiHasil;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }
}
