package com.universidad.repository;

import com.universidad.config.DatabaseConfig;
import com.universidad.model.entities.Usuario;
import com.universidad.model.enums.TipoUsuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository implements BaseRepository<Usuario, Integer> {

    @Override
    public List<Usuario> findAll() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Statement st = DatabaseConfig.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("[UsuarioRepository] Error en findAll: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try (PreparedStatement ps = DatabaseConfig.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapear(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("[UsuarioRepository] Error en findById para ID " + id + ": " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Usuario> findByCorreo(String correo) {
        String sql = "SELECT * FROM usuario WHERE correo = ?";
        try (PreparedStatement ps = DatabaseConfig.getConnection().prepareStatement(sql)) {
            ps.setString(1, correo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("[UsuarioRepository] ¡Fila encontrada en SQLite! Pasando a mapear los datos...");
                    return Optional.of(mapear(rs));
                } else {
                    System.out.println("[UsuarioRepository] SQLite devolvió 0 filas para el correo: " + correo);
                }
            }
        } catch (SQLException e) {
            System.err.println("[UsuarioRepository] Error de SQL en findByCorreo: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("[UsuarioRepository] ¡ERROR DE ENUM! El tipo guardado en SQLite no coincide con tu Enum TipoUsuario. Verifique mayúsculas/minúsculas: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("[UsuarioRepository] Error inesperado en findByCorreo: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Usuario save(Usuario u) {
        if (u.getId() == 0) {
            String sql = "INSERT INTO usuario(nombre,correo,contrasena,tipo) VALUES (?,?,?,?)";
            try (PreparedStatement ps = DatabaseConfig.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, u.getNombre());
                ps.setString(2, u.getCorreo());
                ps.setString(3, u.getContrasena());
                ps.setString(4, u.getTipo().name());
                ps.executeUpdate();

                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        u.setId(keys.getInt(1));
                    }
                }
            } catch (SQLException e) {
                System.err.println("[UsuarioRepository] Error al insertar usuario nuevo: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            String sql = "UPDATE usuario SET nombre=?,correo=?,contrasena=?,tipo=? WHERE id=?";
            try (PreparedStatement ps = DatabaseConfig.getConnection().prepareStatement(sql)) {
                ps.setString(1, u.getNombre());
                ps.setString(2, u.getCorreo());
                ps.setString(3, u.getContrasena());
                ps.setString(4, u.getTipo().name());
                ps.setInt(5, u.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("[UsuarioRepository] Error al actualizar usuario con ID " + u.getId() + ": " + e.getMessage());
                e.printStackTrace();
            }
        }
        return u;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM usuario WHERE id=?";
        try (PreparedStatement ps = DatabaseConfig.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[UsuarioRepository] Error al eliminar usuario con ID " + id + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private Usuario mapear(ResultSet rs) throws SQLException {
        String tipoString = rs.getString("tipo");
        TipoUsuario tipo;
        try {
            tipo = TipoUsuario.valueOf(tipoString);
        } catch (IllegalArgumentException e) {
            System.err.println("[UsuarioRepository] Mapeo fallido: '" + tipoString + "' no es un valor válido en tu Enum TipoUsuario.");
            throw e;
        }

        return new Usuario(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("correo"),
                rs.getString("contrasena"),
                tipo
        );
    }
}
