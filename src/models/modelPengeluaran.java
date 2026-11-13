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
    
    
    public boolean tambah() {
        String sql = "INSERT INTO pengeluaran "
                + "( id_pengeluaran, tanggal, keterangan, nominal, id_pengguna) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, idPengeluaran);
            ps.setString(2, tanggal);
            ps.setString(3, keterangan);
            ps.setInt(4, nominal);
            ps.setString(5, idPengguna);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            return false;
        }
    }

    public boolean ubah() {
        String sql = "UPDATE pengeluaran SET "
                + "tanggal = ?, keterangan = ?, nominal = ?, id_pengguna = ? "
                + "WHERE id_pengeluaran = ?";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, tanggal);
            ps.setString(2, keterangan);
            ps.setInt(3, nominal);
            ps.setString(4, idPengguna);
            ps.setString(5, idPengeluaran);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            return false;
        }
    }

    public boolean hapus() {
        String sql = "DELETE FROM pengeluaran WHERE id_pengeluaran = ?";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, idPengeluaran);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            return false;
        }
    }

    public List<modelPengeluaran> tampilSemua() {
        List<modelPengeluaran> daftar = new ArrayList<>();

        String sql = "SELECT "
                + "p.id_pengeluaran, "
                + "p.tanggal, "
                + "u.nama_pengguna, "
                + "p.nominal, "
                + "p.keterangan "
                + "FROM pengeluaran p "
                + "JOIN pengguna u "
                + "ON p.id_pengguna = u.id_pengguna "
                + "ORDER BY p.tanggal DESC";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                modelPengeluaran pengeluaran = new modelPengeluaran(
                        rs.getString("id_pengeluaran"),
                        rs.getString("tanggal"),
                        rs.getString("keterangan"),
                        rs.getString("id_pengguna"),
                        rs.getInt("nominal")
                );

                daftar.add(pengeluaran);
            }
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan pengguna: " + e.getMessage());
        }
        return daftar;

    }
    
    
    
}
