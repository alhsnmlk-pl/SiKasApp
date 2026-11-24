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
public class modelPemasukan {
    private String idPemasukan, tanggal, keterangan, idPengguna;
    private int nominal;

    public modelPemasukan(String idPemasukan, String tanggal, String idPengguna, int nominal, String keterangan) {
        this.idPemasukan = idPemasukan;
        this.tanggal = tanggal;
        this.keterangan = keterangan;
        this.idPengguna = idPengguna;
        this.nominal = nominal;
    }

    public modelPemasukan() {
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

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }
    
    //CRUD
    //Tambah
    public boolean tambah() {
        String sql = "INSERT INTO pemasukan "
                + "(id_pemasukan, tanggal, id_pengguna, nominal, keterangan) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, idPemasukan);
            ps.setString(2, tanggal);
            ps.setString(3, idPengguna);
            ps.setInt(4, nominal);
            ps.setString(5, keterangan);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {

            return false;
        }
    }

    //ubah
    public boolean ubah() {
        String sql = "UPDATE pemasukan SET "
                + "tanggal = ?, id_pengguna = ?, nominal = ?, keterangan = ? "
                + "WHERE id_pemasukan = ?";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, tanggal);
            ps.setString(2, idPengguna);
            ps.setInt(3, nominal);
            ps.setString(4, keterangan);
            ps.setString(5, idPemasukan);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {

            return false;
        }

    }

    //Hapus
    public boolean hapus() {
        String sql = "DELETE FROM pemasukan WHERE id_pemasukan = ?";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, idPemasukan);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {

            return false;
        }

    }

    //untuk menampilkan
    public List<modelPemasukan> tampilsemua() {
        List<modelPemasukan> daftar = new ArrayList<>();

        String sql = "SELECT "
                + "p.id_pemasukan, "
                + "p.tanggal, "
                + "u.nama_pengguna, "
                + "p.nominal, "
                + "p.keterangan "
                + "FROM pemasukan p "
                + "JOIN pengguna u "
                + "ON p.id_pengguna = u.id_pengguna "
                + "ORDER BY p.tanggal DESC";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                modelPemasukan pemasukan = new modelPemasukan(
                        rs.getString("id_pemasukan"),
                        rs.getString("tanggal"),
                        rs.getString("nama_pengguna"),
                        rs.getInt("nominal"),
                        rs.getString("keterangan")
                );

                daftar.add(pemasukan);
            }
        }catch (SQLException e) {
            System.out.println( "Gagal menampilkan data pemasukan: " + e.getMessage());
        }

        return daftar;
    }
}
