CREATE DATABASE sistema_meteorologico_invernadero;
USE Sistema_meteorologico_invernadero;

CREATE TABLE Zonas (
    id_zona INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion TEXT NULL
);

CREATE TABLE Registros_Ambientales (
    id_registro BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_zona INT NOT NULL,
    fecha_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    
    temperatura_interior DECIMAL(4,2) NOT NULL, 
    temperatura_exterior DECIMAL(4,2) NOT NULL, 
    humedad_relativa DECIMAL(5,2) NOT NULL,     
    humedad_suelo DECIMAL(5,2) NOT NULL,         
    radiacion_solar DECIMAL(6,2) NOT NULL,       
    indice_uv DECIMAL(3,1) NOT NULL,            
    estado_ventilacion VARCHAR(20) NOT NULL,     
    
    FOREIGN KEY (id_zona) REFERENCES Zonas(id_zona) ON DELETE CASCADE
);

CREATE TABLE Reglas_Negocio (
    id_regla INT AUTO_INCREMENT PRIMARY KEY,
    nombre_variable VARCHAR(50) NOT NULL UNIQUE,
    valor_limite DECIMAL(6,2) NOT NULL,
    descripcion VARCHAR(255) NOT NULL
);

CREATE TABLE Alertas_Activas (
    id_alerta BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_registro BIGINT NOT NULL,
    tipo_alerta VARCHAR(50) NOT NULL, 
    mensaje TEXT NOT NULL,
    fecha_hora_generada DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atendida BOOLEAN NOT NULL DEFAULT 0,
    
    FOREIGN KEY (id_registro) REFERENCES Registros_Ambientales(id_registro) ON DELETE CASCADE
);

DELIMITER $$

CREATE TRIGGER trg_evaluar_reglas_invernadero
AFTER INSERT ON Registros_Ambientales
FOR EACH ROW
BEGIN

    DECLARE lim_temp_int_max DECIMAL(6,2);
    DECLARE lim_temp_ext_max DECIMAL(6,2);
    DECLARE lim_hum_aire_min DECIMAL(6,2);
    DECLARE lim_hum_suelo_min DECIMAL(6,2);
    DECLARE lim_uv_max DECIMAL(6,2);
    
    SELECT valor_limite INTO lim_temp_int_max FROM Reglas_Negocio WHERE nombre_variable = 'temp_int_max';
    SELECT valor_limite INTO lim_temp_ext_max FROM Reglas_Negocio WHERE nombre_variable = 'temp_ext_max';
    SELECT valor_limite INTO lim_hum_aire_min FROM Reglas_Negocio WHERE nombre_variable = 'hum_aire_min';
    SELECT valor_limite INTO lim_hum_suelo_min FROM Reglas_Negocio WHERE nombre_variable = 'hum_suelo_min';
    SELECT valor_limite INTO lim_uv_max FROM Reglas_Negocio WHERE nombre_variable = 'uv_max';

    IF NEW.temperatura_interior > lim_temp_int_max OR NEW.temperatura_exterior > lim_temp_ext_max THEN
        INSERT INTO Alertas_Activas (id_registro, tipo_alerta, mensaje)
        VALUES (NEW.id_registro, 'Temperatura Excesiva', 
                CONCAT('Alerta: Temperatura excesiva detectada. Interior: ', NEW.temperatura_interior, '°C, Exterior: ', NEW.temperatura_exterior, '°C.'));
    END IF;

    IF NEW.humedad_relativa < lim_hum_aire_min THEN
        INSERT INTO Alertas_Activas (id_registro, tipo_alerta, mensaje)
        VALUES (NEW.id_registro, 'Humedad Insuficiente', 
                CONCAT('Alerta: Humedad relativa del aire insuficiente: ', NEW.humedad_relativa, '%.'));
    END IF;

    IF NEW.indice_uv >= lim_uv_max THEN
        INSERT INTO Alertas_Activas (id_registro, tipo_alerta, mensaje)
        VALUES (NEW.id_registro, 'Condiciones UV Críticas', 
                CONCAT('Alerta: Condiciones UV peligrosas detectadas. Índice UV: ', NEW.indice_uv));
    END IF;

    IF NEW.humedad_suelo < lim_hum_suelo_min THEN
        INSERT INTO Alertas_Activas (id_registro, tipo_alerta, mensaje)
        VALUES (NEW.id_registro, 'Estrés Hídrico', 
                CONCAT('Alerta: Estrés hídrico crítico en la tierra. Humedad del suelo: ', NEW.humedad_suelo, '%.'));
    END IF;

    IF NEW.temperatura_interior > 30.00 AND NEW.estado_ventilacion = 'Apagado' THEN
        INSERT INTO Alertas_Activas (id_registro, tipo_alerta, mensaje)
        VALUES (NEW.id_registro, 'Ventilación Inadecuada', 
                CONCAT('Alerta: Se requiere ventilación adecuada. Temperatura de ', NEW.temperatura_interior, '°C y la ventilación está Apagada.'));
    END IF;

END$$

DELIMITER ;

INSERT INTO Zonas (nombre, descripcion) VALUES
('Zona Norte', 'Sección de hortalizas altas.'),
('Zona Sur', 'Área de plantas jóvenes.');

INSERT INTO Reglas_Negocio (nombre_variable, valor_limite, descripcion) VALUES
('temp_int_max', 35.00, 'Alerta si la temperatura interior supera los 35°C'),
('temp_ext_max', 38.00, 'Alerta si la temperatura exterior supera los 38°C'),
('hum_aire_min', 40.00, 'Alerta si la humedad del aire cae de 40%'),
('hum_suelo_min', 30.00, 'Alerta por estrés hídrico si el suelo cae de 30%'),
('uv_max', 8.00, 'Alerta si las condiciones UV superan el índice 8.0');