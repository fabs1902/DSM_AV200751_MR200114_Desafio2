package com.example.dsm_av200751_mr200114_desafio2.model;

public class Usuario {
    private String uid;
    private String nombre;
    private String correo;
    private String password;
    private String departamento;
    private String tipoUsuario;

    public Usuario() {
    }

    public String getUid() {
        return uid;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
