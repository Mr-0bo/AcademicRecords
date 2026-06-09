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

    public int getIdCarrera() {
        return this.idCarrera;
    }

     public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public int getSemestre() {
        return this.semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getNombreCarrera() {
        return this.nombreCarrera;
    }
}
