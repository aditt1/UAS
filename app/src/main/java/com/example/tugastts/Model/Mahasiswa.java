package com.example.tugastts.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mahasiswa {
    @SerializedName("nim")
    @Expose
    private String nim;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("emailMhs")
    @Expose
    private String emailMhs;
    @SerializedName("alamatMhs")
    @Expose
    private String alamatMhs;
    @SerializedName("fotoMhs")
    @Expose
    private int fotoMhs;

    public Mahasiswa(String nim, String nama, String emailMhs, String alamatMhs, int fotoMhs) {
        this.nim = nim;
        this.nama = nama;
        this.emailMhs = emailMhs;
        this.alamatMhs = alamatMhs;
        this.fotoMhs = fotoMhs;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmailMhs() {
        return emailMhs;
    }

    public void setEmailMhs(String emailMhs) {
        this.emailMhs = emailMhs;
    }

    public String getAlamatMhs() {
        return alamatMhs;
    }

    public void setAlamatMhs(String alamatMhs) {
        this.alamatMhs = alamatMhs;
    }

    public int getFotoMhs() {
        return fotoMhs;
    }

    public void setFotoMhs(int fotoMhs) {
        this.fotoMhs = fotoMhs;
    }
}
