package com.universidad.repository;

import com.universidad.config.DatabaseConfig;
import com.universidad.model.entities.Alumno;
import com.universidad.model.enums.TipoUsuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlumnoRepository implements BaseRepository<Alumno, Integer> {

    private static final String SELECT_BASE = """
            SELECT u.id, u.nombre, u.correo, u.contrasena,
                   a.matricula, a.id_carrera, c.nombre AS nombre_carrera
            FROM usuario u
            JOIN alumno a ON u.id = a.id_usuario
            LEFT JOIN carrera c ON a.id_carrera = c.id
            """;

    @Override
    public List<Alumno> findAll() {
        List<Alumno> lista = new ArrayList<>();
        try (Statement st = DatabaseConfig.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SELECT_BASE)) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public Optional<Alumno> findById(Integer id) {
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement(SELECT_BASE + " WHERE u.id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return Optional.empty();
    }

    public List<Alumno> findByCarrera(int idCarrera) {
        List<Alumno> lista = new ArrayList<>();
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement(SELECT_BASE + " WHERE a.id_carrera = ?")) {
            ps.setInt(1, idCarrera);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public Alumno save(Alumno a) {
        Connection conn;
        try {
            conn = DatabaseConfig.getConnection();
            conn.setAutoCommit(false);

            if (a.getId() == 0) {
                // INSERT usuario
                PreparedStatement psU = conn.prepareStatement(
                        "INSERT INTO usuario(nombre,correo,contrasena,tipo) VALUES(?,?,?,'ALUMNO')",
                        Statement.RETURN_GENERATED_KEYS);
                psU.setString(1, a.getNombre());
                psU.setString(2, a.getCorreo());
                psU.setString(3, a.getContrasena());
                psU.executeUpdate();
                ResultSet keys = psU.getGeneratedKeys();
                if (keys.next()) a.setId(keys.getInt(1));
                psU.close();

                // INSERT alumno
                PreparedStatement psA = conn.prepareStatement(
                        "INSERT INTO alumno(id_usuario,matricula,id_carrera) VALUES(?,?,?)");
                psA.setInt(1, a.getId());
                psA.setString(2, a.getMatricula());
                psA.setInt(3, a.getIdCarrera());
                psA.executeUpdate();
                psA.close();
            } else {
                PreparedStatement psU = conn.prepareStatement(
                        "UPDATE usuario SET nombre=?,correo=?,contrasena=? WHERE id=?");
                psU.setString(1, a.getNombre());
                psU.setString(2, a.getCorreo());
                psU.setString(3, a.getContrasena());
                psU.setInt(4, a.getId());
                psU.executeUpdate();
                psU.close();

                PreparedStatement psA = conn.prepareStatement(
                        "UPDATE alumno SET matricula=?,id_carrera=? WHERE id_usuario=?");
                psA.setString(1, a.getMatricula());
                psA.setInt(2, a.getIdCarrera());
                psA.setInt(3, a.getId());
                psA.executeUpdate();
                psA.close();
            }
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            try { DatabaseConfig.getConnection().rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
        return a;
    }

    @Override
    public boolean delete(Integer id) {
        try (PreparedStatement ps = DatabaseConfig.getConnection()
                .prepareStatement("DELETE FROM usuario WHERE id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    private Alumno mapear(ResultSet rs) throws SQLException {
        Alumno a = new Alumno();
        a.setId(rs.getInt("id"));
        a.setNombre(rs.getString("nombre"));
        a.setCorreo(rs.getString("correo"));
        a.setContrasena(rs.getString("contrasena"));
        a.setTipo(TipoUsuario.ALUMNO);
        a.setMatricula(rs.getString("matricula"));
        a.setIdCarrera(rs.getInt("id_carrera"));
        a.setNombreCarrera(rs.getString("nombre_carrera"));
        return a;
    }
}
