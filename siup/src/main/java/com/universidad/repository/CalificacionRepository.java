package com.universidad.repository;

import com.universidad.config.DatabaseConfig;
import com.universidad.model.entities.Calificacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CalificacionRepository implements BaseRepository<Calificacion, Integer> {

    private static final String SELECT_BASE = """
            SELECT c.id, c.id_inscripcion,
                   c.parcial1, c.parcial2, c.parcial3,
                   c.actividades, c.proyecto,
                   c.promedio_final, c.aprobado,
                   u.nombre AS nombre_alumno, a.matricula
            FROM calificacion c
            JOIN inscripcion i ON c.id_inscripcion = i.id
            JOIN usuario u ON i.id_alumno = u.id
            JOIN alumno a ON i.id_alumno = a.id_usuario
            """;

    @Override
    public List<Calificacion> findAll() {
        List<Calificacion> lista = new ArrayList<>();
        try (Statement st = DatabaseConfig.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SELECT_BASE)) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public Optional<Calificacion> findById(Integer id) {
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement(SELECT_BASE + " WHERE c.id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return Optional.empty();
    }

    public Optional<Calificacion> findByInscripcion(int idInscripcion) {
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement(SELECT_BASE + " WHERE c.id_inscripcion=?")) {
            ps.setInt(1, idInscripcion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return Optional.empty();
    }

    /** Todas las calificaciones de un grupo */
    public List<Calificacion> findByGrupo(int idGrupo) {
        List<Calificacion> lista = new ArrayList<>();
        String sql = SELECT_BASE + " JOIN inscripcion ins2 ON c.id_inscripcion=ins2.id WHERE ins2.id_grupo=?";
        try (PreparedStatement ps = DatabaseConfig.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idGrupo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    /** Calificaciones del alumno en todos sus grupos */
    public List<Calificacion> findByAlumno(int idAlumno) {
        List<Calificacion> lista = new ArrayList<>();
        String sql = SELECT_BASE + " JOIN inscripcion ins2 ON c.id_inscripcion=ins2.id WHERE ins2.id_alumno=?";
        try (PreparedStatement ps = DatabaseConfig.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public Calificacion save(Calificacion c) {
        if (c.getId() == 0) {
            try (PreparedStatement ps = DatabaseConfig.getConnection().prepareStatement(
                    "INSERT INTO calificacion(id_inscripcion,parcial1,parcial2,parcial3,actividades,proyecto,promedio_final,aprobado) VALUES(?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, c.getIdInscripcion());
                ps.setDouble(2, c.getParcial1()); ps.setDouble(3, c.getParcial2()); ps.setDouble(4, c.getParcial3());
                ps.setDouble(5, c.getActividades()); ps.setDouble(6, c.getProyecto());
                ps.setDouble(7, c.getPromedioFinal()); ps.setInt(8, c.isAprobado() ? 1 : 0);
                ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) c.setId(keys.getInt(1));
            } catch (SQLException e) { e.printStackTrace(); }
        } else {
            try (PreparedStatement ps = DatabaseConfig.getConnection().prepareStatement(
                    "UPDATE calificacion SET parcial1=?,parcial2=?,parcial3=?,actividades=?,proyecto=?,promedio_final=?,aprobado=? WHERE id=?")) {
                ps.setDouble(1, c.getParcial1()); ps.setDouble(2, c.getParcial2()); ps.setDouble(3, c.getParcial3());
                ps.setDouble(4, c.getActividades()); ps.setDouble(5, c.getProyecto());
                ps.setDouble(6, c.getPromedioFinal()); ps.setInt(7, c.isAprobado() ? 1 : 0);
                ps.setInt(8, c.getId());
                ps.executeUpdate();
            } catch (SQLException e) { e.printStackTrace(); }
        }
        return c;
    }

    @Override
    public boolean delete(Integer id) {
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement("DELETE FROM calificacion WHERE id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    private Calificacion mapear(ResultSet rs) throws SQLException {
        Calificacion c = new Calificacion();
        c.setId(rs.getInt("id"));
        c.setIdInscripcion(rs.getInt("id_inscripcion"));
        c.setParcial1(rs.getDouble("parcial1"));
        c.setParcial2(rs.getDouble("parcial2"));
        c.setParcial3(rs.getDouble("parcial3"));
        c.setActividades(rs.getDouble("actividades"));
        c.setProyecto(rs.getDouble("proyecto"));
        c.setPromedioFinal(rs.getDouble("promedio_final"));
        c.setAprobado(rs.getInt("aprobado") == 1);
        c.setNombreAlumno(rs.getString("nombre_alumno"));
        c.setMatricula(rs.getString("matricula"));
        return c;
    }
}
