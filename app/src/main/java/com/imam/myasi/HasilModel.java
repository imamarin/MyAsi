package com.imam.myasi;

public class HasilModel {
    private String iddiagnosa;
    private String idquestion;
    private String hasil;

    public HasilModel(){

    }

    public HasilModel(String iddiagnosa, String idquestion, String hasil) {
        this.iddiagnosa = iddiagnosa;
        this.idquestion = idquestion;
        this.hasil = hasil;
    }

    public String getIddiagnosa() {
        return iddiagnosa;
    }

    public void setIddiagnosa(String iddiagnosa) {
        this.iddiagnosa = iddiagnosa;
    }

    public String getIdquestion() {
        return idquestion;
    }

    public void setIdquestion(String idquestion) {
        this.idquestion = idquestion;
    }

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }
}
