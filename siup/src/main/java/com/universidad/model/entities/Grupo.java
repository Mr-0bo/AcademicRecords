package com.universidad.model.entities;

public class Grupo {
    private int id;
    private int idMateria;
    private int idProfesor;
    private String periodo;
    private String nombreMateria;   // auxiliar
    private String nombreProfesor;  // auxiliar

    public Grupo() {}

    public Grupo(int id, int idMateria, int idProfesor, String periodo) {
        this.id = id;
        this.idMateria = idMateria;
        this.idProfesor = idProfesor;
        this.periodo = periodo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdMateria() { return idMateria; }
    public void setIdMateria(int idMateria) { this.idMateria = idMateria; }

    public int getIdProfesor() { return idProfesor; }
    public void setIdProfesor(int idProfesor) { this.idProfesor = idProfesor; }

    public String getPeriodo() { return periodo; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }

    public String getNombreMateria() { return nombreMateria; }
    public void setNombreMateria(String nombreMateria) { this.nombreMateria = nombreMateria; }

    public String getNombreProfesor() { return nombreProfesor; }
    public void setNombreProfesor(String nombreProfesor) { this.nombreProfesor = nombreProfesor; }

    @Override
    public String toString() {
        return (nombreMateria != null ? nombreMateria : "Grupo " + id) + " — " + periodo;
    }
}
