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

    public double getParcial1() {
        return this.parcial1;
    }

    public void setParcial1(double parcial1) {
        this.parcial1 = parcial1;
    }

    public double getParcial2() {
        return this.parcial2;
    }

    public void setParcial2(double parcial2) {
        this.parcial2 = parcial2;
    }

    public double getParcial3() {
        return this.parcial3;
    }

    public void setParcial3(double parcial3) {
        this.parcial3 = parcial3;
    }

        public double getActividades() {
        return this.actividades;
    }

    public void setActividades(double actividades) {
        this.actividades = actividades;
    }

    public double getProyecto() {
        return this.proyecto;
    }

    public void setProyecto(double proyecto) {
        this.proyecto = proyecto;
    }

    public double getPromedioFinal() {
        return this.promedioFinal;
    }

    public void setPromedioFinal(double promedioFinal) {
        this.promedioFinal = promedioFinal;
    }

    public boolean isAprobado() {
        return this.aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public String getNombreAlumno() {
        return this.nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

}
