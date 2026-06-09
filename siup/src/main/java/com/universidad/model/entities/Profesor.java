package com.universidad.model.entities;

public class Profesor extends Usuario {
    private String numeroEmpleado;

    public Profesor() {
        this.setTipo(TipoUsuario.PROFESOR);
    }

    public Profesor(int id, String nombre, String correo, String contrasena, String numeroEmpleado) {
        super(id, nombre, correo, contrasena, TipoUsuario.PROFESOR);
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNumeroEmpleado() {
        return this.numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String toString() {
        String var10000 = this.getNombre();
        return var10000 + " [" + this.numeroEmpleado + "]";
    }
}
