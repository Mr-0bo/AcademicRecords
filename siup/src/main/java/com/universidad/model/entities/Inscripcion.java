package main.java.com.universidad.model.entities;

public class Inscripcion {
  public class Inscripcion {
    private int id;
    private int idAlumno;
    private int idGrupo;
    private String nombreAlumno;
    private String matricula;

    public Inscripcion() {
    }

    public Inscripcion(int id, int idAlumno, int idGrupo) {
        this.id = id;
        this.idAlumno = idAlumno;
        this.idGrupo = idGrupo;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAlumno() {
        return this.idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdGrupo() {
        return this.idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombreAlumno() {
        return this.nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getMatricula() {
        return this.matricula;
    }
}
