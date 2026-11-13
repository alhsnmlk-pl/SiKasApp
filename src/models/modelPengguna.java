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
 * @author Al
 */
public class modelPengguna {

    private String idPengguna, namaPengguna, password, role;

    public modelPengguna() {
    }

    public modelPengguna(String idPengguna, String namaPengguna, String password, String role) {
        this.idPengguna = idPengguna;
        this.namaPengguna = namaPengguna;
        this.password = password;
        this.role = role;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna() {
        this.idPengguna = UUID.randomUUID().toString(); //genarate id unik dengan otomatis
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
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

    public boolean tambah() {
        String sql = "INSERT INTO pengguna "
                + "( id_pengguna, nama_pengguna, password, role) "
                + "VALUES (?, ?, ?, ?)";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, idPengguna);
            ps.setString(2, namaPengguna);
            ps.setString(3, password);
            ps.setString(4, role);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            return false;
        }
    }

    public boolean ubah() {
        String sql = "UPDATE pengguna SET "
                + "nama_pengguna = ?, password = ?, role = ? "
                + "WHERE id_pengguna = ?";

        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, namaPengguna);
            ps.setString(2, password);
            ps.setString(3, role);
            ps.setString(4, idPengguna);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            return false;
        }
    }

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

    public List<modelPengguna> tampilSemua() {
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
                        rs.getString("password"),
                        rs.getString("role")
                );

                daftar.add(pengguna);
            }
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan pengguna: " + e.getMessage());
        }
        return daftar;

    }

}
