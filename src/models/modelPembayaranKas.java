/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.UUID;

/**
 *
 * @author Al
 */
public class modelPembayaranKas {
    
    private String idPembayaranKas, tanggal, status, idPengguna, NIM;
    private int jumlahBayar;

    public modelPembayaranKas() {
    }
    
    public modelPembayaranKas(String idPembayaranKas, String tanggal, String status, String idPengguna, String NIM, int jumlahBayar) {
        this.idPembayaranKas = idPembayaranKas;
        this.tanggal = tanggal;
        this.status = status;
        this.idPengguna = idPengguna;
        this.NIM = NIM;
        this.jumlahBayar = jumlahBayar;
    }

    public String getIdPembayaranKas() {
        return idPembayaranKas;
    }

    public void setIdPembayaranKas() {
        this.idPembayaranKas = UUID.randomUUID().toString();;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public int getJumlahBayar() {
        return jumlahBayar;
    }

    public void setJumlahBayar(int jumlahBayar) {
        this.jumlahBayar = jumlahBayar;
    }
    
    
    
}
