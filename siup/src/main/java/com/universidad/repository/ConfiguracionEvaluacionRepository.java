package com.universidad.repository;

import com.universidad.config.DatabaseConfig;
import com.universidad.model.entities.ConfiguracionEvaluacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConfiguracionEvaluacionRepository implements BaseRepository<ConfiguracionEvaluacion, Integer> {

    @Override
    public List<ConfiguracionEvaluacion> findAll() {
        List<ConfiguracionEvaluacion> lista = new ArrayList<>();
        try (Statement st = DatabaseConfig.getConnection().createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM configuracion_evaluacion")) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public Optional<ConfiguracionEvaluacion> findById(Integer id) {
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement("SELECT * FROM configuracion_evaluacion WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return Optional.empty();
    }

    public Optional<ConfiguracionEvaluacion> findByMateria(int idMateria) {
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement("SELECT * FROM configuracion_evaluacion WHERE id_materia=?")) {
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return Optional.empty();
    }

    @Override
    public ConfiguracionEvaluacion save(ConfiguracionEvaluacion cfg) {
        if (cfg.getId() == 0) {
            try (PreparedStatement ps = DatabaseConfig.getConnection().prepareStatement(
                    "INSERT INTO configuracion_evaluacion(id_materia,porcentaje_parciales,porcentaje_actividades,porcentaje_proyecto,calificacion_minima,faltas_maximas) VALUES(?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, cfg.getIdMateria());
                ps.setDouble(2, cfg.getPorcentajeParciales()); ps.setDouble(3, cfg.getPorcentajeActividades());
                ps.setDouble(4, cfg.getPorcentajeProyecto()); ps.setDouble(5, cfg.getCalificacionMinima());
                ps.setInt(6, cfg.getFaltasMaximas());
                ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) cfg.setId(keys.getInt(1));
            } catch (SQLException e) { e.printStackTrace(); }
        } else {
            try (PreparedStatement ps = DatabaseConfig.getConnection().prepareStatement(
                    "UPDATE configuracion_evaluacion SET porcentaje_parciales=?,porcentaje_actividades=?,porcentaje_proyecto=?,calificacion_minima=?,faltas_maximas=? WHERE id=?")) {
                ps.setDouble(1, cfg.getPorcentajeParciales()); ps.setDouble(2, cfg.getPorcentajeActividades());
                ps.setDouble(3, cfg.getPorcentajeProyecto()); ps.setDouble(4, cfg.getCalificacionMinima());
                ps.setInt(5, cfg.getFaltasMaximas()); ps.setInt(6, cfg.getId());
                ps.executeUpdate();
            } catch (SQLException e) { e.printStackTrace(); }
        }
        return cfg;
    }

    @Override
    public boolean delete(Integer id) {
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement("DELETE FROM configuracion_evaluacion WHERE id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    private ConfiguracionEvaluacion mapear(ResultSet rs) throws SQLException {
        return new ConfiguracionEvaluacion(
                rs.getInt("id"), rs.getInt("id_materia"),
                rs.getDouble("porcentaje_parciales"), rs.getDouble("porcentaje_actividades"),
                rs.getDouble("porcentaje_proyecto"), rs.getDouble("calificacion_minima"),
                rs.getInt("faltas_maximas")
        );
    }
}
