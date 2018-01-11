package com.example.windows10.bokingfutsal;

/**
 * Created by WINDOWS 10 on 10 Jan 2018.
 */

public class ListActivity {
    private String nama;
    private String lokasi;
    private String image;

    public ListActivity(){

    }

    public ListActivity(String nama, String lokasi, String image) {
        this.nama = nama;
        this.lokasi = lokasi;
        this.image = image;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {this.nama = nama;}

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
