package com.universidad.model.entities;

import com.universidad.model.enums.TipoUsuario;

public class Alumno extends Usuario {
    private String matricula;
    private int idCarrera;
    private String nombreCarrera; // campo auxiliar para mostrar en UI

    public Alumno() {
        setTipo(TipoUsuario.ALUMNO);
    }

    public Alumno(int id, String nombre, String correo, String contrasena,
                  String matricula, int idCarrera) {
        super(id, nombre, correo, contrasena, TipoUsuario.ALUMNO);
        this.matricula = matricula;
        this.idCarrera = idCarrera;
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public int getIdCarrera() { return idCarrera; }
    public void setIdCarrera(int idCarrera) { this.idCarrera = idCarrera; }

    public String getNombreCarrera() { return nombreCarrera; }
    public void setNombreCarrera(String nombreCarrera) { this.nombreCarrera = nombreCarrera; }

    @Override
    public String toString() {
        return getNombre() + " (" + matricula + ")";
    }
}
