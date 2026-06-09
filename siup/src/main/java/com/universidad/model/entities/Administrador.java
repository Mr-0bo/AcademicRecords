package com.universidad.model.entities;

import com.universidad.model.enums.TipoUsuario;

public class Administrador extends Usuario {
    private String numeroEmpleado;

    public Administrador() {
        setTipo(TipoUsuario.ADMIN);
    }

    public Administrador(int id, String nombre, String correo, String contrasena, String numeroEmpleado) {
        super(id, nombre, correo, contrasena, TipoUsuario.ADMIN);
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNumeroEmpleado() { return numeroEmpleado; }
    public void setNumeroEmpleado(String numeroEmpleado) { this.numeroEmpleado = numeroEmpleado; }

    @Override
    public String toString() {
        return "Admin: " + getNombre() + " [" + numeroEmpleado + "]";
    }
}
