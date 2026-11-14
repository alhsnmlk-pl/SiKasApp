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
        this.idPembayaranKas = UUID.randomUUID().toString();
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
    
    public boolean tambah() {
        String sql = "INSERT INTO pembayaran_kas "
                + "(id_pembayaran, tanggal, status, id_pengguna, NIM, jumlah_bayar) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idPembayaranKas);
            ps.setString(2, tanggal);
            ps.setString(3, status);
            ps.setString(4, idPengguna);
            ps.setString(5, NIM);
            ps.setInt(6, jumlahBayar);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menambah pembayaran kas: " + e.getMessage());
            return false;
        }
    }

    public boolean ubah() {
        String sql = "UPDATE pembayaran_kas SET "
                + "tanggal = ?, status = ?, id_pengguna = ?, NIM = ?, jumlah_bayar = ? "
                + "WHERE id_pembayaran = ?";

        try (Connection conn = koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tanggal);
            ps.setString(2, status);
            ps.setString(3, idPengguna);
            ps.setString(4, NIM);
            ps.setInt(5, jumlahBayar);
            ps.setString(6, idPembayaranKas);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal mengubah pembayaran kas: " + e.getMessage());
            return false;
        }
    }

    public boolean hapus() {
        String sql = "DELETE FROM pembayaran_kas WHERE id_pembayaran = ?";

        try (Connection conn = koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idPembayaranKas);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menghapus pembayaran kas: " + e.getMessage());
            return false;
        }
    }

    public List<modelPembayaranKas> tampilSemua() {
        List<modelPembayaranKas> daftar = new ArrayList<>();

        String sql = "SELECT id_pembayaran, tanggal, status, id_pengguna, NIM, jumlah_bayar "
                + "FROM pembayaran_kas "
                + "ORDER BY tanggal DESC";

        try (Connection conn = koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                modelPembayaranKas pembayaran = new modelPembayaranKas(
                        rs.getString("id_pembayaran"),
                        rs.getString("tanggal"),
                        rs.getString("status"),
                        rs.getString("id_pengguna"),
                        rs.getString("NIM"),
                        rs.getInt("jumlah_bayar")
                );

                daftar.add(pembayaran);
            }
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan pembayaran kas: " + e.getMessage());
        }
        return daftar;

    }
    
    
    
}
