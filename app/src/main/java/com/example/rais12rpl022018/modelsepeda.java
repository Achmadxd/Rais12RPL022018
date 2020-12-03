package com.example.rais12rpl022018;

public class modelsepeda {
    int id;
    String kode;
    String merk;
    String jenis;
    String warna;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int hargasewa;

    public modelsepeda() {
        this.id = id;
        this.kode = kode;
        this.merk = merk;
        this.jenis = jenis;
        this.warna = warna;
        this.hargasewa = hargasewa;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public int getHargasewa() {
        return hargasewa;
    }

    public void setHargasewa(int hargasewa) {
        this.hargasewa = hargasewa;
    }
}
