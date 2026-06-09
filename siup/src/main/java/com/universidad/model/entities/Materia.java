package main.java.com.universidad.model.entities;

public class Materia {
  public class Materia {
    private int id;
    private String nombre;
    private int idCarrera;
    private int semestre;
    private String nombreCarrera;

    public Materia() {
    }

    public Materia(int id, String nombre, int idCarrera, int semestre) {
        this.id = id;
        this.nombre = nombre;
        this.idCarrera = idCarrera;
        this.semestre = semestre;
    }
}
