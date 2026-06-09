package main.java.com.universidad.model.entities;

public class Calificacion {
  public class Calificacion {
    private int id;
    private int idInscripcion;
    private double parcial1;
    private double parcial2;
    private double parcial3;
    private double actividades;
    private double proyecto;
    private double promedioFinal;
    private boolean aprobado;
    private String nombreAlumno;
    private String matricula;

    public Calificacion() {
    }

    public Calificacion(int id, int idInscripcion) {
        this.id = id;
        this.idInscripcion = idInscripcion;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdInscripcion() {
        return this.idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }
    
}
