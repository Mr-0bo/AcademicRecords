package com.universidad.service;

import com.universidad.model.entities.Usuario;
import com.universidad.repository.UsuarioRepository;
import com.universidad.util.SessionManager;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

/**
 * Gestiona autenticación y manejo de contraseñas con BCrypt.
 */
public class AuthService {

    private final UsuarioRepository usuarioRepository = new UsuarioRepository();

    /**
     * Intenta autenticar al usuario con correo y contraseña.
     * Si es exitoso, guarda la sesión en SessionManager.
     *
     * @return el usuario autenticado, o vacío si las credenciales son inválidas
     */
    public Optional<Usuario> login(String correo, String contrasena) {
        Optional<Usuario> opt = usuarioRepository.findByCorreo(correo);

        if (opt.isEmpty()) return Optional.empty();

        Usuario u = opt.get();

        // Verificar hash
        boolean match;
        try {
            match = BCrypt.checkpw(contrasena, u.getContrasena());
        } catch (Exception e) {
            // Si la contraseña de prueba no está hasheada (seed inicial), comparar directo
            match = contrasena.equals(u.getContrasena());
        }

        if (!match) return Optional.empty();

        SessionManager.getInstance().setUsuarioActual(u);
        return Optional.of(u);
    }

    /**
     * Genera un hash para almacenar una contraseña de forma segura.
     */
    public String hashContrasena(String contrasena) {
        return BCrypt.hashpw(contrasena, BCrypt.gensalt(10));
    }

    public void logout() {
        SessionManager.getInstance().cerrarSesion();
    }
}
