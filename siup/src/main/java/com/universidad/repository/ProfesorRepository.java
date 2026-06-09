package com.universidad.repository;

import com.universidad.config.DatabaseConfig;
import com.universidad.model.entities.Profesor;
import com.universidad.model.enums.TipoUsuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfesorRepository implements BaseRepository<Profesor, Integer> {

    private static final String SELECT_BASE = """
            SELECT u.id, u.nombre, u.correo, u.contrasena, p.numero_empleado
            FROM usuario u
            JOIN profesor p ON u.id = p.id_usuario
            """;

    @Override
    public List<Profesor> findAll() {
        List<Profesor> lista = new ArrayList<>();
        try (Statement st = DatabaseConfig.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SELECT_BASE)) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public Optional<Profesor> findById(Integer id) {
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement(SELECT_BASE + " WHERE u.id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return Optional.empty();
    }

    @Override
    public Profesor save(Profesor p) {
        Connection conn;
        try {
            conn = DatabaseConfig.getConnection();
            conn.setAutoCommit(false);
            if (p.getId() == 0) {
                PreparedStatement psU = conn.prepareStatement(
                        "INSERT INTO usuario(nombre,correo,contrasena,tipo) VALUES(?,?,?,'PROFESOR')",
                        Statement.RETURN_GENERATED_KEYS);
                psU.setString(1, p.getNombre());
                psU.setString(2, p.getCorreo());
                psU.setString(3, p.getContrasena());
                psU.executeUpdate();
                ResultSet keys = psU.getGeneratedKeys();
                if (keys.next()) p.setId(keys.getInt(1));
                psU.close();

                PreparedStatement psP = conn.prepareStatement(
                        "INSERT INTO profesor(id_usuario,numero_empleado) VALUES(?,?)");
                psP.setInt(1, p.getId());
                psP.setString(2, p.getNumeroEmpleado());
                psP.executeUpdate();
                psP.close();
            } else {
                PreparedStatement psU = conn.prepareStatement(
                        "UPDATE usuario SET nombre=?,correo=?,contrasena=? WHERE id=?");
                psU.setString(1, p.getNombre()); psU.setString(2, p.getCorreo());
                psU.setString(3, p.getContrasena()); psU.setInt(4, p.getId());
                psU.executeUpdate(); psU.close();

                PreparedStatement psP = conn.prepareStatement(
                        "UPDATE profesor SET numero_empleado=? WHERE id_usuario=?");
                psP.setString(1, p.getNumeroEmpleado()); psP.setInt(2, p.getId());
                psP.executeUpdate(); psP.close();
            }
            conn.commit(); conn.setAutoCommit(true);
        } catch (SQLException e) { e.printStackTrace(); }
        return p;
    }

    @Override
    public boolean delete(Integer id) {
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement("DELETE FROM usuario WHERE id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    private Profesor mapear(ResultSet rs) throws SQLException {
        Profesor p = new Profesor();
        p.setId(rs.getInt("id"));
        p.setNombre(rs.getString("nombre"));
        p.setCorreo(rs.getString("correo"));
        p.setContrasena(rs.getString("contrasena"));
        p.setTipo(TipoUsuario.PROFESOR);
        p.setNumeroEmpleado(rs.getString("numero_empleado"));
        return p;
    }
}
