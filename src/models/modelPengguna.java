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
    
}
