package com.universidad.repository;

import com.universidad.config.DatabaseConfig;
import com.universidad.model.entities.Carrera;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarreraRepository implements BaseRepository<Carrera, Integer> {

    @Override
    public List<Carrera> findAll() {
        List<Carrera> lista = new ArrayList<>();
        try (Statement st = DatabaseConfig.getConnection().createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM carrera ORDER BY nombre")) {
            while (rs.next()) lista.add(new Carrera(rs.getInt("id"), rs.getString("nombre")));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public Optional<Carrera> findById(Integer id) {
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement("SELECT * FROM carrera WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(new Carrera(rs.getInt("id"), rs.getString("nombre")));
        } catch (SQLException e) { e.printStackTrace(); }
        return Optional.empty();
    }

    @Override
    public Carrera save(Carrera c) {
        if (c.getId() == 0) {
            try (PreparedStatement ps = DatabaseConfig.getConnection()
                    .prepareStatement("INSERT INTO carrera(nombre) VALUES(?)", Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, c.getNombre());
                ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) c.setId(keys.getInt(1));
            } catch (SQLException e) { e.printStackTrace(); }
        } else {
            try (PreparedStatement ps = DatabaseConfig.getConnection()
                    .prepareStatement("UPDATE carrera SET nombre=? WHERE id=?")) {
                ps.setString(1, c.getNombre()); ps.setInt(2, c.getId());
                ps.executeUpdate();
            } catch (SQLException e) { e.printStackTrace(); }
        }
        return c;
    }

    @Override
    public boolean delete(Integer id) {
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement("DELETE FROM carrera WHERE id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
}
