package com.universidad;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        com.universidad.util.NavigationUtil.setPrimaryStage(primaryStage);
        URL fxmlLocation = getClass().getResource("/com/universidad/fxml/login.fxml");

        if (fxmlLocation == null) {
            System.out.println("¡Error! No se encontró el archivo FXML.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();

        Scene scene = new Scene(root);

        String css = getClass().getResource("/com/universidad/css/styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        primaryStage.setTitle("Sistema Universitario");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
