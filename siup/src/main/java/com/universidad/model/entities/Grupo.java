package main.java.com.universidad.model.entities;

public class Grupo {
      public class Grupo {
    private int id;
    private int idMateria;
    private int idProfesor;
    private String periodo;
    private String nombreMateria;
    private String nombreProfesor;

    public Grupo() {
    }

      public Grupo(int id, int idMateria, int idProfesor, String periodo) {
        this.id = id;
        this.idMateria = idMateria;
        this.idProfesor = idProfesor;
        this.periodo = periodo;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMateria() {
        return this.idMateria;
    }

    public int getIdProfesor() {
        return this.idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getPeriodo() {
        return this.periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
            
}
