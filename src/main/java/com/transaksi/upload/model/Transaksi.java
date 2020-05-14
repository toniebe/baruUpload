package com.transaksi.upload.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaksi")
public class Transaksi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idtransaksi;
    private String username;
    private String nomer;
    private String paket;
    private String provider;
    private float harga;

    @Temporal(TemporalType.DATE)
    private Date tanggal;

    public Transaksi() {
    }

    public Transaksi(int idtransaksi,String username,String nomer,String paket,String provider,float harga,Date tanggal){
        this.idtransaksi = idtransaksi;
        this.username = username;
        this.nomer = nomer;
        this.paket = paket;
        this.provider = provider;
        this.harga = harga;
        this.tanggal = tanggal;
    }



    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public int getIdtransaksi() {
        return idtransaksi;
    }

    public void setIdtransaksi(int idtransaksi) {
        this.idtransaksi = idtransaksi;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public float getHarga() {
        return harga;
    }

    public void setHarga(float harga) {
        this.harga = harga;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

}
