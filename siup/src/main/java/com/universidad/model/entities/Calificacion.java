package com.universidad.model.entities;

/**
 * Almacena las calificaciones de un alumno en una inscripción.
 * El promedio final se calcula con EvaluacionService (patrón Strategy).
 */
public class Calificacion {
    private int id;
    private int idInscripcion;

    // Parciales (hasta 3)
    private double parcial1;
    private double parcial2;
    private double parcial3;

    // Actividades y proyecto
    private double actividades;
    private double proyecto;

    // Calculados
    private double promedioFinal;
    private boolean aprobado;

    // Auxiliares para UI
    private String nombreAlumno;
    private String matricula;

    public Calificacion() {}

    public Calificacion(int id, int idInscripcion) {
        this.id = id;
        this.idInscripcion = idInscripcion;
    }

    // ── Getters y Setters ────────────────────────────────────────────────────

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdInscripcion() { return idInscripcion; }
    public void setIdInscripcion(int idInscripcion) { this.idInscripcion = idInscripcion; }

    public double getParcial1() { return parcial1; }
    public void setParcial1(double parcial1) { this.parcial1 = parcial1; }

    public double getParcial2() { return parcial2; }
    public void setParcial2(double parcial2) { this.parcial2 = parcial2; }

    public double getParcial3() { return parcial3; }
    public void setParcial3(double parcial3) { this.parcial3 = parcial3; }

    public double getActividades() { return actividades; }
    public void setActividades(double actividades) { this.actividades = actividades; }

    public double getProyecto() { return proyecto; }
    public void setProyecto(double proyecto) { this.proyecto = proyecto; }

    public double getPromedioFinal() { return promedioFinal; }
    public void setPromedioFinal(double promedioFinal) { this.promedioFinal = promedioFinal; }

    public boolean isAprobado() { return aprobado; }
    public void setAprobado(boolean aprobado) { this.aprobado = aprobado; }

    public String getNombreAlumno() { return nombreAlumno; }
    public void setNombreAlumno(String nombreAlumno) { this.nombreAlumno = nombreAlumno; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    /** Promedio de los tres parciales */
    public double getPromedioParciales() {
        return (parcial1 + parcial2 + parcial3) / 3.0;
    }

    public String getEstatusTexto() {
        return aprobado ? "Aprobado" : "Reprobado";
    }
}
