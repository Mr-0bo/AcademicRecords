package com.universidad.repository;

import com.universidad.config.DatabaseConfig;
import com.universidad.model.entities.Grupo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GrupoRepository implements BaseRepository<Grupo, Integer> {

    private static final String SELECT_BASE = """
            SELECT g.id, g.id_materia, g.id_profesor, g.periodo,
                   m.nombre AS nombre_materia,
                   u.nombre AS nombre_profesor
            FROM grupo g
            JOIN materia m ON g.id_materia = m.id
            JOIN usuario u ON g.id_profesor = u.id
            """;

    @Override
    public List<Grupo> findAll() {
        List<Grupo> lista = new ArrayList<>();
        try (Statement st = DatabaseConfig.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SELECT_BASE)) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public List<Grupo> findByProfesor(int idProfesor) {
        List<Grupo> lista = new ArrayList<>();
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement(SELECT_BASE + " WHERE g.id_profesor=?")) {
            ps.setInt(1, idProfesor);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public Optional<Grupo> findById(Integer id) {
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement(SELECT_BASE + " WHERE g.id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return Optional.empty();
    }

    @Override
    public Grupo save(Grupo g) {
        if (g.getId() == 0) {
            try (PreparedStatement ps = DatabaseConfig.getConnection().prepareStatement(
                    "INSERT INTO grupo(id_materia,id_profesor,periodo) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, g.getIdMateria()); ps.setInt(2, g.getIdProfesor()); ps.setString(3, g.getPeriodo());
                ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) g.setId(keys.getInt(1));
            } catch (SQLException e) { e.printStackTrace(); }
        } else {
            try (PreparedStatement ps = DatabaseConfig.getConnection().prepareStatement(
                    "UPDATE grupo SET id_materia=?,id_profesor=?,periodo=? WHERE id=?")) {
                ps.setInt(1, g.getIdMateria()); ps.setInt(2, g.getIdProfesor());
                ps.setString(3, g.getPeriodo()); ps.setInt(4, g.getId());
                ps.executeUpdate();
            } catch (SQLException e) { e.printStackTrace(); }
        }
        return g;
    }

    @Override
    public boolean delete(Integer id) {
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement("DELETE FROM grupo WHERE id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    private Grupo mapear(ResultSet rs) throws SQLException {
        Grupo g = new Grupo();
        g.setId(rs.getInt("id"));
        g.setIdMateria(rs.getInt("id_materia"));
        g.setIdProfesor(rs.getInt("id_profesor"));
        g.setPeriodo(rs.getString("periodo"));
        g.setNombreMateria(rs.getString("nombre_materia"));
        g.setNombreProfesor(rs.getString("nombre_profesor"));
        return g;
    }
}
