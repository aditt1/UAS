package com.example.tugastts.Model;

public class Kelas {
    private String hari;
    private String sesi;
    private String dosen;
    private String jumMhs;

    public Kelas(String hari, String sesi, String dosen, String jumMhs){
        this.hari = hari;
        this.sesi = sesi;
        this.dosen = dosen;
        this.jumMhs = jumMhs;
    }
    public String getHari(){
        return hari;
    }
    public void setHari(String hari){
        this.hari = hari;
    }
    public String getSesi(){
        return sesi;
    }
    public void setSesi(String sesi){
        this.sesi = sesi;
    }
    public String getDosen(){
        return dosen;
    }
    public void setDosen(String dosen){ this.dosen = dosen; }
    public String getJumMhs(){
        return jumMhs;
    }
    public void setJumMhs(String jumMhs){
        this.jumMhs = jumMhs;
    }
}
