package main.java.com.universidad.model.entities;

public class ConfiguracionEvaluacion {
  public class ConfiguracionEvaluacion {
    private int id;
    private int idMateria;
    private double porcentajeParciales;
    private double porcentajeActividades;
    private double porcentajeProyecto;
    private double calificacionMinima;
    private int faltasMaximas;

    public ConfiguracionEvaluacion() {
    }

     public ConfiguracionEvaluacion(int id, int idMateria, double porcentajeParciales, double porcentajeActividades, double porcentajeProyecto, double calificacionMinima, int faltasMaximas) {
        this.id = id;
        this.idMateria = idMateria;
        this.porcentajeParciales = porcentajeParciales;
        this.porcentajeActividades = porcentajeActividades;
        this.porcentajeProyecto = porcentajeProyecto;
        this.calificacionMinima = calificacionMinima;
        this.faltasMaximas = faltasMaximas;
    }

    public boolean esValida() {
        double suma = this.porcentajeParciales + this.porcentajeActividades + this.porcentajeProyecto;
        return Math.abs(suma - (double)100.0F) < 0.001;
    }
}
