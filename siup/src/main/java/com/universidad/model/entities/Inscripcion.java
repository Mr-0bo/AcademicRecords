package com.universidad.model.entities;

public class Inscripcion {
    private int id;
    private int idAlumno;
    private int idGrupo;
    private String nombreAlumno;  // auxiliar
    private String matricula;     // auxiliar

    public Inscripcion() {}

    public Inscripcion(int id, int idAlumno, int idGrupo) {
        this.id = id;
        this.idAlumno = idAlumno;
        this.idGrupo = idGrupo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdAlumno() { return idAlumno; }
    public void setIdAlumno(int idAlumno) { this.idAlumno = idAlumno; }

    public int getIdGrupo() { return idGrupo; }
    public void setIdGrupo(int idGrupo) { this.idGrupo = idGrupo; }

    public String getNombreAlumno() { return nombreAlumno; }
    public void setNombreAlumno(String nombreAlumno) { this.nombreAlumno = nombreAlumno; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    @Override
    public String toString() {
        return nombreAlumno != null ? nombreAlumno : "Inscripción #" + id;
    }
}
