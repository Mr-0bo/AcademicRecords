package com.universidad.util;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NavigationUtil {
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void navigate(String fxmlPath, String titulo) {
        try {
            URL resource = Thread.currentThread().getContextClassLoader().getResource(fxmlPath.substring(fxmlPath.startsWith("/") ? 1 : 0));
            if (resource == null) {
                throw new IllegalStateException("No se encontró el archivo FXML en la ruta: " + fxmlPath);
            }

            FXMLLoader loader = new FXMLLoader(resource);
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            URL cssResource = Thread.currentThread().getContextClassLoader().getResource("com/universidad/css/styles.css");
            if (cssResource != null) {
                scene.getStylesheets().add(cssResource.toExternalForm());
            }

            primaryStage.setTitle("Sistema Universitario — " + titulo);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            AlertUtil.error("Error de navegación", "No se pudo cargar la vista: " + fxmlPath);
        }

    }

    public static void abrirModal(String fxmlPath, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(NavigationUtil.class.getResource(fxmlPath));
            Parent root = (Parent)loader.load();
            Stage modal = new Stage();
            modal.setTitle(titulo);
            modal.initModality(Modality.APPLICATION_MODAL);
            modal.initOwner(primaryStage);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(NavigationUtil.class.getResource("/com/universidad/css/styles.css").toExternalForm());
            modal.setScene(scene);
            modal.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            AlertUtil.error("Error", "No se pudo abrir el formulario.");
        }

    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}
