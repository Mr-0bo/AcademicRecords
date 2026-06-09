package com.universidad.model.entities;

/**
 * Define los porcentajes de evaluación de una materia.
 * Los tres porcentajes DEBEN sumar 100.
 */
public class ConfiguracionEvaluacion {
    private int id;
    private int idMateria;
    private double porcentajeParciales;   // ej. 60.0
    private double porcentajeActividades; // ej. 30.0
    private double porcentajeProyecto;    // ej. 10.0
    private double calificacionMinima;    // ej. 6.0
    private int faltasMaximas;            // ej. 3

    public ConfiguracionEvaluacion() {}

    public ConfiguracionEvaluacion(int id, int idMateria,
                                   double porcentajeParciales,
                                   double porcentajeActividades,
                                   double porcentajeProyecto,
                                   double calificacionMinima,
                                   int faltasMaximas) {
        this.id = id;
        this.idMateria = idMateria;
        this.porcentajeParciales = porcentajeParciales;
        this.porcentajeActividades = porcentajeActividades;
        this.porcentajeProyecto = porcentajeProyecto;
        this.calificacionMinima = calificacionMinima;
        this.faltasMaximas = faltasMaximas;
    }

    /** Valida que los porcentajes sumen exactamente 100 */
    public boolean esValida() {
        double suma = porcentajeParciales + porcentajeActividades + porcentajeProyecto;
        return Math.abs(suma - 100.0) < 0.001;
    }

    // ── Getters y Setters ────────────────────────────────────────────────────

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdMateria() { return idMateria; }
    public void setIdMateria(int idMateria) { this.idMateria = idMateria; }

    public double getPorcentajeParciales() { return porcentajeParciales; }
    public void setPorcentajeParciales(double v) { this.porcentajeParciales = v; }

    public double getPorcentajeActividades() { return porcentajeActividades; }
    public void setPorcentajeActividades(double v) { this.porcentajeActividades = v; }

    public double getPorcentajeProyecto() { return porcentajeProyecto; }
    public void setPorcentajeProyecto(double v) { this.porcentajeProyecto = v; }

    public double getCalificacionMinima() { return calificacionMinima; }
    public void setCalificacionMinima(double v) { this.calificacionMinima = v; }

    public int getFaltasMaximas() { return faltasMaximas; }
    public void setFaltasMaximas(int v) { this.faltasMaximas = v; }
}
