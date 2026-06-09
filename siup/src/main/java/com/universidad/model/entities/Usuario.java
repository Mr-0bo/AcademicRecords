package com.universidad.model.entities;

public class Usuario {
    private int id;
    private String nombre;
    private String correo;
    private String contrasena;
    private TipoUsuario tipo;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String correo, String contrasena, TipoUsuario tipo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.tipo = tipo;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return this.contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public TipoUsuario getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public String toString() {
        int var10000 = this.id;
        return "Usuario{id=" + var10000 + ", nombre='" + this.nombre + "', correo='" + this.correo + "', tipo=" + String.valueOf(this.tipo) + "}";
    }
}
