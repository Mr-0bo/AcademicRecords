package com.universidad.model.entities;

import com.universidad.model.enums.TipoUsuario;

public class Profesor extends Usuario {
    private String numeroEmpleado;

    public Profesor() {
        setTipo(TipoUsuario.PROFESOR);
    }

    public Profesor(int id, String nombre, String correo, String contrasena, String numeroEmpleado) {
        super(id, nombre, correo, contrasena, TipoUsuario.PROFESOR);
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNumeroEmpleado() { return numeroEmpleado; }
    public void setNumeroEmpleado(String numeroEmpleado) { this.numeroEmpleado = numeroEmpleado; }

    @Override
    public String toString() {
        return getNombre() + " [" + numeroEmpleado + "]";
    }
}
