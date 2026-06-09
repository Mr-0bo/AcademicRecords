package com.universidad.controller;

import com.universidad.model.entities.Usuario;
import com.universidad.service.AuthService;
import com.universidad.util.AlertUtil;
import com.universidad.util.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Optional;

public class LoginController {

    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtContrasena;
    @FXML private Button btnIngresar;

    private final AuthService authService = new AuthService();

    @FXML
    public void onIngresar(ActionEvent event) {
        String correo = txtCorreo.getText().trim();
        String pass   = txtContrasena.getText();

        // 1. Validación de campos locales
        if (correo.isEmpty() || pass.isEmpty()) {
            AlertUtil.advertencia("Campos vacíos", "Por favor, ingresa tu correo y contraseña.");
            return;
        }

        System.out.println("[Login] Intentando iniciar sesión para: " + correo);

        try {
            // 2. Intentar autenticar a través del servicio
            Optional<Usuario> resultado = authService.login(correo, pass);

            if (resultado.isEmpty()) {
                System.out.println("[Login] RECHAZADO: El servicio devolvió un Optional vacío (Usuario no encontrado o contraseña incorrecta).");
                AlertUtil.error("Acceso denegado", "Correo o contraseña incorrectos.");
                txtContrasena.clear();
                return;
            }

            // 3. Usuario autenticado con éxito
            Usuario usuario = resultado.get();
            System.out.println("[Login] ÉXITO: Usuario autenticado. Nombre: " + usuario.getNombre() + " | Rol: " + usuario.getTipo());

            // 4. Redirección según el rol utilizando rutas relativas seguras
            switch (usuario.getTipo()) {
                case ADMIN -> {
                    System.out.println("[Login] Redirigiendo a Dashboard de Administrador...");
                    NavigationUtil.navigate("com/universidad/fxml/admin_dashboard.fxml", "Administración");
                }
                case PROFESOR -> {
                    System.out.println("[Login] Redirigiendo a Panel de Profesor...");
                    NavigationUtil.navigate("com/universidad/fxml/profesor_dashboard.fxml", "Panel Profesor");
                }
                case ALUMNO -> {
                    System.out.println("[Login] Redirigiendo a Panel de Alumno...");
                    NavigationUtil.navigate("com/universidad/fxml/alumno_dashboard.fxml", "Mis Calificaciones");
                }
                default -> {
                    System.out.println("[Login] ERROR: Tipo de usuario desconocido.");
                    AlertUtil.error("Error de Rol", "El tipo de usuario no es válido.");
                }
            }

        } catch (Exception e) {
            System.err.println("[Login] EXCEPCIÓN CRÍTICA durante el proceso de login:");
            e.printStackTrace();
            AlertUtil.error("Error del Sistema", "Ocurrió un fallo inesperado: " + e.getMessage());
        }
    }
}
