/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import config.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author ASUS
 */
public class modelPembayaranKas {
    private String idPembayaran, tanggal, status, idPengguna, nim;
    private int jumlahBayar;

    public modelPembayaranKas(String idPembayaran, String tanggal, String nim, int jumlahBayar, String status, String idPengguna) {
        this.idPembayaran = idPembayaran;
        this.tanggal = tanggal;
        this.status = status;
        this.idPengguna = idPengguna;
        this.nim = nim;
        this.jumlahBayar = jumlahBayar;
    }

    public modelPembayaranKas() {
    }

    public String getIdPembayaran() {
        return idPembayaran;
    }

    public void setIdPembayaran() {
        this.idPembayaran = UUID.randomUUID().toString();
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

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public int getJumlahBayar() {
        return jumlahBayar;
    }

    public void setJumlahBayar(int jumlahBayar) {
        this.jumlahBayar = jumlahBayar;
    }
    
    //CRUD
    //Tambah
    public boolean tambah() {
        String sql = "INSERT INTO pembayaran_kas "
                + "(id_pembayaran, tanggal, jumlah_bayar, status, id_pengguna, nim) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, idPembayaran);
            ps.setString(2, tanggal);
            ps.setInt(3, jumlahBayar);
            ps.setString(4, status);
            ps.setString(5, idPengguna);
            ps.setString(6, nim);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {

            return false;
        }
    }

    //ubah
    public boolean ubah() {
        String sql = "UPDATE pembayaran_kas SET "
                + "tanggal = ?, jumlah_bayar = ?, status = ?, id_pengguna = ?, nim = ? "
                + "WHERE id_pembayaran = ?";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, tanggal);
            ps.setInt(2, jumlahBayar);
            ps.setString(3, status);
            ps.setString(4, idPengguna);
            ps.setString(5, nim);
            ps.setString(6, idPembayaran);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {

            return false;
        }

    }

    //Hapus
    public boolean hapus() {
        String sql = "DELETE FROM pembayaran_kas WHERE id_pembayaran = ?";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, idPembayaran);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {

            return false;
        }

    }

    //untuk menampilkan
    public List<modelPembayaranKas> tampilsemua() {
        List<modelPembayaranKas> daftar = new ArrayList<>();

        String sql = "SELECT "
                + "p.id_pembayaran, "
                + "p.tanggal, "
                + "m.nama, "
                + "p.jumlah_bayar, "
                + "p.status, "
                + "u.nama_pengguna "
                + "FROM pembayaran_kas p "
                + "JOIN pengguna u "
                + "ON p.id_pengguna = u.id_pengguna "
                + "JOIN mahasiswa m "
                + "ON p.nim = m.nim "
                + "ORDER BY p.tanggal DESC";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                modelPembayaranKas pembayaran = new modelPembayaranKas(
                        rs.getString("id_pembayaran"),
                        rs.getString("tanggal"),
                        rs.getString("nama"),
                        rs.getInt("jumlah_bayar"),
                        rs.getString("status"),
                        rs.getString("nama_pengguna")
                );

                daftar.add(pembayaran);
            }
        }catch (SQLException e) {
            System.out.println( "Gagal menampilkan data pembayaran kas: " + e.getMessage());
        }

        return daftar;
    }
     
}
