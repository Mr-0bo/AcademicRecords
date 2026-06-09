package com.universidad.repository;

import com.universidad.config.DatabaseConfig;
import com.universidad.model.entities.Inscripcion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InscripcionRepository implements BaseRepository<Inscripcion, Integer> {

    private static final String SELECT_BASE = """
            SELECT i.id, i.id_alumno, i.id_grupo,
                   u.nombre AS nombre_alumno, a.matricula
            FROM inscripcion i
            JOIN usuario u ON i.id_alumno = u.id
            JOIN alumno a ON i.id_alumno = a.id_usuario
            """;

    @Override
    public List<Inscripcion> findAll() {
        List<Inscripcion> lista = new ArrayList<>();
        try (Statement st = DatabaseConfig.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SELECT_BASE)) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public List<Inscripcion> findByGrupo(int idGrupo) {
        List<Inscripcion> lista = new ArrayList<>();
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement(SELECT_BASE + " WHERE i.id_grupo=?")) {
            ps.setInt(1, idGrupo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public List<Inscripcion> findByAlumno(int idAlumno) {
        List<Inscripcion> lista = new ArrayList<>();
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement(SELECT_BASE + " WHERE i.id_alumno=?")) {
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public Optional<Inscripcion> findById(Integer id) {
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement(SELECT_BASE + " WHERE i.id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return Optional.empty();
    }

    @Override
    public Inscripcion save(Inscripcion ins) {
        if (ins.getId() == 0) {
            try (PreparedStatement ps = DatabaseConfig.getConnection().prepareStatement(
                    "INSERT INTO inscripcion(id_alumno,id_grupo) VALUES(?,?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, ins.getIdAlumno()); ps.setInt(2, ins.getIdGrupo());
                ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) ins.setId(keys.getInt(1));
            } catch (SQLException e) { e.printStackTrace(); }
        }
        return ins;
    }

    @Override
    public boolean delete(Integer id) {
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement("DELETE FROM inscripcion WHERE id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    private Inscripcion mapear(ResultSet rs) throws SQLException {
        Inscripcion i = new Inscripcion();
        i.setId(rs.getInt("id"));
        i.setIdAlumno(rs.getInt("id_alumno"));
        i.setIdGrupo(rs.getInt("id_grupo"));
        i.setNombreAlumno(rs.getString("nombre_alumno"));
        i.setMatricula(rs.getString("matricula"));
        return i;
    }
}
