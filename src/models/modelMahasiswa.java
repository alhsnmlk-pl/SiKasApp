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
 * @author ASUS
 */
public class modelMahasiswa {
    private String nim, namaMahasiswa;

    public modelMahasiswa(String nim, String namaMahasiswa) {
        this.nim = nim;
        this.namaMahasiswa = namaMahasiswa;
    }

    public modelMahasiswa() {
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }
    
    //CRUD
    //Tambah
    public boolean tambah() {
        String sql = "INSERT INTO mahasiswa "
                + "(nim, nama) "
                + "VALUES (?, ?)";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nim);
            ps.setString(2, namaMahasiswa);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {

            return false;
        }
    }

    //ubah
    public boolean ubah() {
        String sql = "UPDATE pengguna SET "
                + "nama = ? "
                + "WHERE nim = ?";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, namaMahasiswa);
            ps.setString(2, nim);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {

            return false;
        }

    }

    //Hapus
    public boolean hapus() {
        String sql = "DELETE FROM mahasiswa WHERE nim = ?";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nim);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {

            return false;
        }

    }

    //untuk menampilkan
    public List<modelMahasiswa> tampilsemua() {
        List<modelMahasiswa> daftar = new ArrayList<>();

        String sql = "SELECT * FROM mahasiswa ORDER BY nama ASC";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                modelMahasiswa mahasiswa = new modelMahasiswa(
                        rs.getString("nim"),
                        rs.getString("nama_mahasiswa")
                );

                daftar.add(mahasiswa);
            }
        }catch (SQLException e) {
            System.out.println( "Gagal menampilkan mahasiswa: " + e.getMessage());
        }

        return daftar;
    }
     
}
