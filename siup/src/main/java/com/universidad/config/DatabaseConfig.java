package com.universidad.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Gestiona la conexión a SQLite y crea el esquema al arrancar.
 * Patrón: Singleton para la conexión.
 */
public class DatabaseConfig {

    private static final String DB_URL;
    private static Connection connection;

    // Bloque estático para unificar la ruta absoluta en entornos Mac/IntelliJ tradicional
    static {
        String userDir = System.getProperty("user.dir");
        DB_URL = "jdbc:sqlite:" + userDir + "/universidad.db";
        System.out.println("[DatabaseConfig] URL unificada cargada: " + DB_URL);
    }

    private DatabaseConfig() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL);
            connection.createStatement().execute("PRAGMA foreign_keys = ON");
        }
        return connection;
    }

    /** Crea todas las tablas si no existen e inserta datos de prueba */
    public static void inicializar() {
        try (Statement stmt = getConnection().createStatement()) {

            // ── Tablas principales ──────────────────────────────────────────
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS carrera (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL UNIQUE
                )""");

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS usuario (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    correo TEXT NOT NULL UNIQUE,
                    contrasena TEXT NOT NULL,
                    tipo TEXT NOT NULL CHECK(tipo IN ('ADMIN','PROFESOR','ALUMNO'))
                )""");

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS alumno (
                    id_usuario INTEGER PRIMARY KEY,
                    matricula TEXT NOT NULL UNIQUE,
                    id_carrera INTEGER NOT NULL,
                    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE,
                    FOREIGN KEY (id_carrera) REFERENCES carrera(id)
                )""");

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS profesor (
                    id_usuario INTEGER PRIMARY KEY,
                    numero_empleado TEXT NOT NULL UNIQUE,
                    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE
                )""");

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS administrador (
                    id_usuario INTEGER PRIMARY KEY,
                    numero_empleado TEXT NOT NULL UNIQUE,
                    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE
                )""");

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS materia (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    id_carrera INTEGER NOT NULL,
                    semestre INTEGER NOT NULL,
                    FOREIGN KEY (id_carrera) REFERENCES carrera(id)
                )""");

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS grupo (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_materia INTEGER NOT NULL,
                    id_profesor INTEGER NOT NULL,
                    periodo TEXT NOT NULL,
                    FOREIGN KEY (id_materia) REFERENCES materia(id),
                    FOREIGN KEY (id_profesor) REFERENCES usuario(id)
                )""");

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS inscripcion (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_alumno INTEGER NOT NULL,
                    id_grupo INTEGER NOT NULL,
                    UNIQUE(id_alumno, id_grupo),
                    FOREIGN KEY (id_alumno) REFERENCES usuario(id),
                    FOREIGN KEY (id_grupo) REFERENCES grupo(id)
                )""");

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS configuracion_evaluacion (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_materia INTEGER NOT NULL UNIQUE,
                    porcentaje_parciales REAL NOT NULL DEFAULT 60,
                    porcentaje_actividades REAL NOT NULL DEFAULT 30,
                    porcentaje_proyecto REAL NOT NULL DEFAULT 10,
                    calificacion_minima REAL NOT NULL DEFAULT 6.0,
                    faltas_maximas INTEGER NOT NULL DEFAULT 3,
                    FOREIGN KEY (id_materia) REFERENCES materia(id)
                )""");

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS calificacion (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_inscripcion INTEGER NOT NULL UNIQUE,
                    parcial1 REAL NOT NULL DEFAULT 0,
                    parcial2 REAL NOT NULL DEFAULT 0,
                    parcial3 REAL NOT NULL DEFAULT 0,
                    actividades REAL NOT NULL DEFAULT 0,
                    proyecto REAL NOT NULL DEFAULT 0,
                    promedio_final REAL NOT NULL DEFAULT 0,
                    aprobado INTEGER NOT NULL DEFAULT 0,
                    FOREIGN KEY (id_inscripcion) REFERENCES inscripcion(id)
                )""");

            // ── Datos iniciales ─────────────────────────────────────────────
            insertarDatosPrueba(stmt);

        } catch (SQLException e) {
            System.err.println("Error inicializando BD: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static void insertarDatosPrueba(Statement stmt) throws SQLException {
        // Forzamos la limpieza de las tablas para eliminar registros viejos sin encriptar
        System.out.println("[DatabaseConfig] Limpiando tablas para asegurar datos frescos con BCrypt...");

        stmt.executeUpdate("DELETE FROM calificacion");
        stmt.executeUpdate("DELETE FROM inscripcion");
        stmt.executeUpdate("DELETE FROM grupo");
        stmt.executeUpdate("DELETE FROM configuracion_evaluacion");
        stmt.executeUpdate("DELETE FROM materia");
        stmt.executeUpdate("DELETE FROM alumno");
        stmt.executeUpdate("DELETE FROM profesor");
        stmt.executeUpdate("DELETE FROM administrador");
        stmt.executeUpdate("DELETE FROM usuario");
        stmt.executeUpdate("DELETE FROM carrera");

        // Reiniciar los contadores autoincrementales de SQLite para que inicien en 1
        stmt.executeUpdate("DELETE FROM sqlite_sequence");

        System.out.println("[DatabaseConfig] Insertando registros semilla y credenciales encriptadas dinámicamente...");

        // INSTANCIAMOS TU SERVICIO PARA GENERAR EL HASH EXACTO AQUÍ MISMO
        com.universidad.service.AuthService auth = new com.universidad.service.AuthService();
        String hashAdmin = auth.hashContrasena("admin123");
        String hashProf  = auth.hashContrasena("prof123");
        String hashAlum  = auth.hashContrasena("alum123");

        // Carreras
        stmt.executeUpdate("INSERT INTO carrera(nombre) VALUES ('Ingeniería en Sistemas')");
        stmt.executeUpdate("INSERT INTO carrera(nombre) VALUES ('Ingeniería Industrial')");

        // Admin (ID: 1)
        stmt.executeUpdate("INSERT INTO usuario(nombre,correo,contrasena,tipo) VALUES ('Administrador','admin@uni.edu','" + hashAdmin + "','ADMIN')");
        stmt.executeUpdate("INSERT INTO administrador(id_usuario,numero_empleado) VALUES (1,'ADM-001')");

        // Profesor (ID: 2)
        stmt.executeUpdate("INSERT INTO usuario(nombre,correo,contrasena,tipo) VALUES ('Dr. Carlos López','profesor@uni.edu','" + hashProf + "','PROFESOR')");
        stmt.executeUpdate("INSERT INTO profesor(id_usuario,numero_empleado) VALUES (2,'EMP-001')");

        // Alumno (ID: 3)
        stmt.executeUpdate("INSERT INTO usuario(nombre,correo,contrasena,tipo) VALUES ('Ana García','alumno@uni.edu','" + hashAlum + "','ALUMNO')");
        stmt.executeUpdate("INSERT INTO alumno(id_usuario,matricula,id_carrera) VALUES (3,'2024001',1)");

        // Materia
        stmt.executeUpdate("INSERT INTO materia(nombre,id_carrera,semestre) VALUES ('Programación Orientada a Objetos',1,3)");
        stmt.executeUpdate("INSERT INTO materia(nombre,id_carrera,semestre) VALUES ('Bases de Datos',1,4)");

        // Configuracion evaluacion
        stmt.executeUpdate("INSERT INTO configuracion_evaluacion(id_materia,porcentaje_parciales,porcentaje_actividades,porcentaje_proyecto,calificacion_minima,faltas_maximas) VALUES (1,60,30,10,6.0,3)");
        stmt.executeUpdate("INSERT INTO configuracion_evaluacion(id_materia,porcentaje_parciales,porcentaje_actividades,porcentaje_proyecto,calificacion_minima,faltas_maximas) VALUES (2,50,30,20,6.0,3)");

        // Grupo
        stmt.executeUpdate("INSERT INTO grupo(id_materia,id_profesor,periodo) VALUES (1,2,'2024-A')");

        // Inscripcion
        stmt.executeUpdate("INSERT INTO inscripcion(id_alumno,id_grupo) VALUES (3,1)");

        // Calificacion inicial
        stmt.executeUpdate("INSERT INTO calificacion(id_inscripcion,parcial1,parcial2,parcial3,actividades,proyecto,promedio_final,aprobado) VALUES (1,8.5,9.0,7.5,8.0,9.0,8.5,1)");

        System.out.println("[DatabaseConfig] ¡Estructura y datos semilla creados con éxito de forma absoluta!");
    }
}
