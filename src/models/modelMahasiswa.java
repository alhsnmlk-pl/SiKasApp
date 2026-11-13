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

/**
 *
 * @author Al
 */
public class modelMahasiswa {
    
    private String NIM, nama;

    public modelMahasiswa() {
    }

    public modelMahasiswa(String NIM, String nama) {
        this.NIM = NIM;
        this.nama = nama;
    }

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    
    public boolean tambah() {
        String sql = "INSERT INTO mahasiswa "
                + "(NIM, nama) "
                + "VALUES (?, ?)";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, NIM);
            ps.setString(2, nama);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            return false;
        }
    }

    public boolean ubah() {
        String sql = "UPDATE mahasiswa SET "
                + "nama = ? "
                + "WHERE NIM = ?";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nama);
            ps.setString(2, NIM);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            return false;
        }
    }

    public boolean hapus() {
        String sql = "DELETE FROM mahasiswa WHERE NIM = ?";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, NIM);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            return false;
        }
    }

    public List<modelMahasiswa> tampilSemua() {
        List<modelMahasiswa> daftar = new ArrayList<>();

        String sql = "SELECT * FROM mahasiswa ORDER BY nama ASC";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                modelMahasiswa mhs = new modelMahasiswa(
                        rs.getString("NIM"),
                        rs.getString("nama")
                );

                daftar.add(mhs);
            }
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan mahasiswa: " + e.getMessage());
        }
        return daftar;

    }
}
