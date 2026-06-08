package com.universidad.service;

import com.universidad.model.entities.*;
import com.universidad.repository.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

/**
 * Centraliza la lógica CRUD del administrador.
 * Orquesta múltiples repositorios cuando una operación los involucra.
 */
public class AdminService {

    private final CarreraRepository carreraRepo = new CarreraRepository();
    private final MateriaRepository materiaRepo = new MateriaRepository();
    private final AlumnoRepository alumnoRepo = new AlumnoRepository();
    private final ProfesorRepository profesorRepo = new ProfesorRepository();
    private final GrupoRepository grupoRepo = new GrupoRepository();
    private final InscripcionRepository inscripcionRepo = new InscripcionRepository();
    private final ConfiguracionEvaluacionRepository cfgRepo = new ConfiguracionEvaluacionRepository();
    private final CalificacionRepository calRepo = new CalificacionRepository();
    private final EvaluacionService evalService = new EvaluacionService();

    // Carreras

    public List<Carrera> listarCarreras() { return carreraRepo.findAll(); }

    public Carrera guardarCarrera(Carrera c) { return carreraRepo.save(c); }

    public boolean eliminarCarrera(int id) { return carreraRepo.delete(id); }

    // Materias

    public List<Materia> listarMaterias() { return materiaRepo.findAll(); }

    public List<Materia> listarMateriasPorCarrera(int idCarrera) { return materiaRepo.findByCarrera(idCarrera); }

    public Materia guardarMateria(Materia m) { return materiaRepo.save(m); }

    public boolean eliminarMateria(int id) { return materiaRepo.delete(id); }

    // Alumnos

    public List<Alumno> listarAlumnos() { return alumnoRepo.findAll(); }

    public Alumno guardarAlumno(Alumno a, String contrasenaPlana) {
        if (a.getId() == 0) {
            a.setContrasena(BCrypt.hashpw(contrasenaPlana, BCrypt.gensalt(10)));
        } else if (contrasenaPlana != null && !contrasenaPlana.isBlank()) {
            a.setContrasena(BCrypt.hashpw(contrasenaPlana, BCrypt.gensalt(10)));
        }
        return alumnoRepo.save(a);
    }

    public boolean eliminarAlumno(int id) { return alumnoRepo.delete(id); }

    // Profesores

    public List<Profesor> listarProfesores() { return profesorRepo.findAll(); }

    public Profesor guardarProfesor(Profesor p, String contrasenaPlana) {
        if (p.getId() == 0) {
            p.setContrasena(BCrypt.hashpw(contrasenaPlana, BCrypt.gensalt(10)));
        } else if (contrasenaPlana != null && !contrasenaPlana.isBlank()) {
            p.setContrasena(BCrypt.hashpw(contrasenaPlana, BCrypt.gensalt(10)));
        }
        return profesorRepo.save(p);
    }

    public boolean eliminarProfesor(int id) { return profesorRepo.delete(id); }

    // Grupos

    public List<Grupo> listarGrupos() { return grupoRepo.findAll(); }

    public List<Grupo> listarGruposPorProfesor(int idProfesor) { return grupoRepo.findByProfesor(idProfesor); }

    public Grupo guardarGrupo(Grupo g) { return grupoRepo.save(g); }

    public boolean eliminarGrupo(int id) { return grupoRepo.delete(id); }

    // Inscripciones

    public List<Inscripcion> listarInscripcionesPorGrupo(int idGrupo) {
        return inscripcionRepo.findByGrupo(idGrupo);
    }

    public Inscripcion inscribirAlumno(int idAlumno, int idGrupo) {
        Inscripcion ins = new Inscripcion(0, idAlumno, idGrupo);
        ins = inscripcionRepo.save(ins);
        // Crear registro de calificación en cero automáticamente
        Calificacion cal = new Calificacion(0, ins.getId());
        calRepo.save(cal);
        return ins;
    }

    // Calificaciones

    public List<Calificacion> listarCalificacionesPorGrupo(int idGrupo) {
        return calRepo.findByGrupo(idGrupo);
    }

    public Calificacion editarCalificacion(Calificacion cal, ConfiguracionEvaluacion cfg) {
        evalService.calcularPromedio(cal, cfg);
        return calRepo.save(cal);
    }

    // Configuración de evaluación

    public ConfiguracionEvaluacion obtenerConfiguracion(int idMateria) {
        return cfgRepo.findByMateria(idMateria).orElse(null);
    }

    public ConfiguracionEvaluacion guardarConfiguracion(ConfiguracionEvaluacion cfg) {
        if (!cfg.esValida()) {
            throw new IllegalArgumentException("Los porcentajes deben sumar 100%.");
        }
        return cfgRepo.save(cfg);
    }
}
