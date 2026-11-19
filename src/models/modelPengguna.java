/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import config.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author ASUS
 */
public class modelPengguna {

    private String idPengguna, namaPengguna, username, role, password;

    public modelPengguna(String idPengguna, String namaPengguna, String username, String role, String password) {
        this.idPengguna = idPengguna;
        this.namaPengguna = namaPengguna;
        this.username = username;
        this.role = role;
        this.password = password;
    }

    public modelPengguna() {
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna() {
        this.idPengguna = UUID.randomUUID().toString();
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //CRUD
    //Tambah
    public boolean tambah() {
        String sql = "INSERT INTO pengguna "
                + "(id_pengguna, nama_pengguna, username, role, password) "
                + "VALUES (?, ?, ?, ?, md5(?))";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, idPengguna);
            ps.setString(2, namaPengguna);
            ps.setString(3, username);
            ps.setString(4, role);
            ps.setString(5, password);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    //ubah
    public boolean ubah() {
        String sql;
        if (password != null || password.isEmpty()) {
            sql = "UPDATE pengguna SET "
                    + "nama_pengguna = ?, username = ?, role = ? "
                    + "WHERE id_pengguna = ?";
        } else {
            sql = "UPDATE pengguna SET "
                    + "nama_pengguna = ?, username = ?, role = ?, password = md5(?) "
                    + "WHERE id_pengguna = ?";
        }

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, namaPengguna);
            ps.setString(2, username);

            if (password != null || password.isEmpty()) {
                ps.setString(3, role);
                ps.setString(4, idPengguna);
            } else {
                ps.setString(3, role);
                ps.setString(4, password);
                ps.setString(5, idPengguna);
            }

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {

            return false;
        }

    }

    //Hapus
    public boolean hapus() {
        String sql = "DELETE FROM pengguna WHERE id_pengguna = ?";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, idPengguna);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {

            return false;
        }

    }

    //untuk menampilkan
    public List<modelPengguna> tampilsemua() {
        List<modelPengguna> daftar = new ArrayList<>();

        String sql = "SELECT * FROM pengguna ORDER BY nama_pengguna ASC";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                modelPengguna pengguna = new modelPengguna(
                        rs.getString("id_pengguna"),
                        rs.getString("nama_pengguna"),
                        rs.getString("username"),
                        rs.getString("role"),
                        rs.getString("password")
                );

                daftar.add(pengguna);
            }
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan pengguna: " + e.getMessage());
        }

        return daftar;
    }
}
