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
public class modelPengeluaran {
    
    private String idPengeluaran, tanggal, keterangan, idPengguna;
    private int nominal;

    public modelPengeluaran() {
    }
    
    public modelPengeluaran(String idPengeluaran, String tanggal, String keterangan, String idPengguna, int nominal) {
        this.idPengeluaran = idPengeluaran;
        this.tanggal = tanggal;
        this.keterangan = keterangan;
        this.idPengguna = idPengguna;
        this.nominal = nominal;
    }
    
    public String getIdPengeluaran() {
        return idPengeluaran;
    }

    public void setIdPengeluaran() {
        this.idPengeluaran = UUID.randomUUID().toString();
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

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }
    
    
    
}
