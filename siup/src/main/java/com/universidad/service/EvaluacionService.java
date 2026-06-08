package com.universidad.service;

import com.universidad.model.entities.Calificacion;
import com.universidad.model.entities.ConfiguracionEvaluacion;

/**
 * Patrón Strategy: encapsula el algoritmo de cálculo de promedios.
 * Si en el futuro cambia la fórmula (ej. eliminar proyecto), solo se
 * modifica esta clase sin tocar Calificacion ni los controllers.
 */
public class EvaluacionService {

    /**
     * Calcula el promedio final y establece el estatus Aprobado/Reprobado.
     * Fórmula: (promParciales * %parciales + actividades * %actividades + proyecto * %proyecto) / 100
     *
     * @throws IllegalArgumentException si los porcentajes no suman 100
     */
    public void calcularPromedio(Calificacion cal, ConfiguracionEvaluacion cfg) {
        if (!cfg.esValida()) {
            throw new IllegalArgumentException(
                String.format("Los porcentajes suman %.1f%%, deben sumar 100%%.",
                    cfg.getPorcentajeParciales() + cfg.getPorcentajeActividades() + cfg.getPorcentajeProyecto())
            );
        }

        double promParciales = (cal.getParcial1() + cal.getParcial2() + cal.getParcial3()) / 3.0;

        double promedio = (promParciales * cfg.getPorcentajeParciales()
                + cal.getActividades() * cfg.getPorcentajeActividades()
                + cal.getProyecto() * cfg.getPorcentajeProyecto()) / 100.0;

        // Redondeo a 2 decimales
        promedio = Math.round(promedio * 100.0) / 100.0;

        cal.setPromedioFinal(promedio);
        cal.setAprobado(promedio >= cfg.getCalificacionMinima());
    }

    /**
     * Valida que una calificación individual esté en rango [0, 10].
     */
    public boolean calificacionValida(double valor) {
        return valor >= 0.0 && valor <= 10.0;
    }

    /**
     * Valida que los porcentajes sumen 100.
     */
    public boolean porcentajesValidos(double p1, double p2, double p3) {
        return Math.abs((p1 + p2 + p3) - 100.0) < 0.001;
    }
}
