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
public class modelPemasukan {
    
    private String idPemasukan, tanggal, keterangan, idPengguna;
    private int nominal;

    public modelPemasukan() {
    }

    public modelPemasukan(String idPemasukan, String tanggal, String keterangan, String idPengguna, int nominal) {
        this.idPemasukan = idPemasukan;
        this.tanggal = tanggal;
        this.keterangan = keterangan;
        this.idPengguna = idPengguna;
        this.nominal = nominal;
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

    public int getNominal() {
        return nominal;
    }

    public void setJumlah(int jumlah) {
        this.nominal = jumlah;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }
    
    
    public boolean tambah() {
        String sql = "INSERT INTO pemasukan "
                + "(id_pemasukan, tanggal, nominal, keterangan, id_pengguna) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idPemasukan);
            ps.setString(2, tanggal);
            ps.setInt(3, nominal);
            ps.setString(4, keterangan);
            ps.setString(5, idPengguna);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menambah pemasukan: " + e.getMessage());
            return false;
        }
    }

    public boolean ubah() {
        String sql = "UPDATE pemasukan SET "
                + "tanggal = ?, nominal = ?, keterangan = ?, id_pengguna = ? "
                + "WHERE id_pemasukan = ?";

        try (Connection conn = koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tanggal);
            ps.setInt(2, nominal);
            ps.setString(3, keterangan);
            ps.setString(4, idPengguna);
            ps.setString(5, idPemasukan);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal mengubah pemasukan: " + e.getMessage());
            return false;
        }

    }

    public boolean hapus() {
        String sql = "DELETE FROM pemasukan WHERE id_pemasukan = ?";

        try (Connection conn = koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idPemasukan);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menghapus pemasukan: " + e.getMessage());
            return false;
        }
    }

    public List<modelPemasukan> tampilSemua() {
        List<modelPemasukan> daftar = new ArrayList<>();

        String sql = "SELECT id_pemasukan, tanggal, keterangan, id_pengguna, nominal "
                + "FROM pemasukan "
                + "ORDER BY tanggal DESC";

        try (Connection conn = koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                modelPemasukan pemasukan = new modelPemasukan(
                        rs.getString("id_pemasukan"),
                        rs.getString("tanggal"),
                        rs.getString("keterangan"),
                        rs.getString("id_pengguna"),
                        rs.getInt("nominal")
                );

                daftar.add(pemasukan);
            }
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan pemasukan: " + e.getMessage());
        }
        return daftar;

    }
    
    
}
