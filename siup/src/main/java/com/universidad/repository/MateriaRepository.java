package com.universidad.repository;

import com.universidad.config.DatabaseConfig;
import com.universidad.model.entities.Materia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MateriaRepository implements BaseRepository<Materia, Integer> {

    private static final String SELECT_BASE = """
            SELECT m.id, m.nombre, m.id_carrera, m.semestre, c.nombre AS nombre_carrera
            FROM materia m LEFT JOIN carrera c ON m.id_carrera = c.id
            """;

    @Override
    public List<Materia> findAll() {
        List<Materia> lista = new ArrayList<>();
        try (Statement st = DatabaseConfig.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SELECT_BASE + " ORDER BY m.semestre, m.nombre")) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public List<Materia> findByCarrera(int idCarrera) {
        List<Materia> lista = new ArrayList<>();
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement(SELECT_BASE + " WHERE m.id_carrera=? ORDER BY m.semestre")) {
            ps.setInt(1, idCarrera);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public Optional<Materia> findById(Integer id) {
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement(SELECT_BASE + " WHERE m.id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return Optional.empty();
    }

    @Override
    public Materia save(Materia m) {
        if (m.getId() == 0) {
            try (PreparedStatement ps = DatabaseConfig.getConnection()
                    .prepareStatement("INSERT INTO materia(nombre,id_carrera,semestre) VALUES(?,?,?)",
                            Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, m.getNombre()); ps.setInt(2, m.getIdCarrera()); ps.setInt(3, m.getSemestre());
                ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) m.setId(keys.getInt(1));
            } catch (SQLException e) { e.printStackTrace(); }
        } else {
            try (PreparedStatement ps = DatabaseConfig.getConnection()
                    .prepareStatement("UPDATE materia SET nombre=?,id_carrera=?,semestre=? WHERE id=?")) {
                ps.setString(1, m.getNombre()); ps.setInt(2, m.getIdCarrera());
                ps.setInt(3, m.getSemestre()); ps.setInt(4, m.getId());
                ps.executeUpdate();
            } catch (SQLException e) { e.printStackTrace(); }
        }
        return m;
    }

    @Override
    public boolean delete(Integer id) {
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement("DELETE FROM materia WHERE id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    private Materia mapear(ResultSet rs) throws SQLException {
        Materia m = new Materia();
        m.setId(rs.getInt("id"));
        m.setNombre(rs.getString("nombre"));
        m.setIdCarrera(rs.getInt("id_carrera"));
        m.setSemestre(rs.getInt("semestre"));
        m.setNombreCarrera(rs.getString("nombre_carrera"));
        return m;
    }
}

