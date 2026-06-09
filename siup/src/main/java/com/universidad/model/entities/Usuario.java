package com.universidad.model.entities;

import com.universidad.model.enums.TipoUsuario;

public class Usuario {
    private int id;
    private String nombre;
    private String correo;
    private String contrasena;  // almacenada como hash BCrypt
    private TipoUsuario tipo;

    public Usuario() {}

    public Usuario(int id, String nombre, String correo, String contrasena, TipoUsuario tipo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.tipo = tipo;
    }

    // ── Getters y Setters ────────────────────────────────────────────────────

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public TipoUsuario getTipo() { return tipo; }
    public void setTipo(TipoUsuario tipo) { this.tipo = tipo; }

    @Override
    public String toString() {
        return "Usuario{id=" + id + ", nombre='" + nombre + "', correo='" + correo + "', tipo=" + tipo + "}";
    }
}
