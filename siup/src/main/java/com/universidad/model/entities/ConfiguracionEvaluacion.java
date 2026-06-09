package com.universidad.model.entities;

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

        public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMateria() {
        return this.idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public double getPorcentajeParciales() {
        return this.porcentajeParciales;
    }

    public void setPorcentajeParciales(double v) {
        this.porcentajeParciales = v;
    }

    public double getPorcentajeActividades() {
        return this.porcentajeActividades;
    }

    public void setPorcentajeActividades(double v) {
        this.porcentajeActividades = v;
    }

    public double getPorcentajeProyecto() {
        return this.porcentajeProyecto;
    }

    public void setPorcentajeProyecto(double v) {
        this.porcentajeProyecto = v;
    }

    public double getCalificacionMinima() {
        return this.calificacionMinima;
    }

    public void setCalificacionMinima(double v) {
        this.calificacionMinima = v;
    }

    public int getFaltasMaximas() {
        return this.faltasMaximas;
    }

    public void setFaltasMaximas(int v) {
        this.faltasMaximas = v;
    }
}
