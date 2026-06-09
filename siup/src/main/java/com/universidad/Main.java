package com.universidad;

import com.universidad.config.DatabaseConfig;

public class Main {
    public static void main(String[] args) {
        // 1. Prepara SQLite y crea tablas/usuarios si no existen
        DatabaseConfig.inicializar();

        // 2. Arranca la interfaz gráfica de JavaFX
        App.main(args);
    }
}
