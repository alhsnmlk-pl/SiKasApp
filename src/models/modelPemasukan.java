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
public class modelPemasukan {
    
    private String idPemasukan, tanggal, keterangan, idPengguna;

    public modelPemasukan() {
    }

    public modelPemasukan(String idPemasukan, String tanggal, String keterangan, String idPengguna) {
        this.idPemasukan = idPemasukan;
        this.tanggal = tanggal;
        this.keterangan = keterangan;
        this.idPengguna = idPengguna;
    }

    public String getIdPemasukan() {
        return idPemasukan;
    }

    public void setIdPemasukan() {
        this.idPemasukan = UUID.randomUUID().toString();
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }
    
    
    
}
