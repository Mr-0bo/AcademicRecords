package com.universidad.controller;

import com.universidad.model.entities.Calificacion;
import com.universidad.model.entities.Inscripcion;
import com.universidad.model.entities.Usuario;
import com.universidad.repository.CalificacionRepository;
import com.universidad.repository.InscripcionRepository;
import com.universidad.service.AuthService;
import com.universidad.util.NavigationUtil;
import com.universidad.util.SessionManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AlumnoDashboardController implements Initializable {

    @FXML private Label lblBienvenida;
    @FXML private TableView<FilaCalificacion> tablaCalificaciones;
    @FXML private TableColumn<FilaCalificacion, String> colMateria;
    @FXML private TableColumn<FilaCalificacion, String> colPeriodo;
    @FXML private TableColumn<FilaCalificacion, String> colP1;
    @FXML private TableColumn<FilaCalificacion, String> colP2;
    @FXML private TableColumn<FilaCalificacion, String> colP3;
    @FXML private TableColumn<FilaCalificacion, String> colActividades;
    @FXML private TableColumn<FilaCalificacion, String> colProyecto;
    @FXML private TableColumn<FilaCalificacion, String> colFinal;
    @FXML private TableColumn<FilaCalificacion, String> colEstatus;

    private final InscripcionRepository insRepo = new InscripcionRepository();
    private final CalificacionRepository calRepo = new CalificacionRepository();
    private final AuthService authService        = new AuthService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Usuario usuario = SessionManager.getInstance().getUsuarioActual();
        lblBienvenida.setText("Hola, " + usuario.getNombre());

        configurarColumnas();
        cargarCalificaciones(usuario.getId());
    }

    private void configurarColumnas() {
        colMateria.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().materia));
        colPeriodo.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().periodo));
        colP1.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().p1));
        colP2.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().p2));
        colP3.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().p3));
        colActividades.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().actividades));
        colProyecto.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().proyecto));
        colFinal.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().promedio));
        colEstatus.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().estatus));

        colEstatus.setCellFactory(col -> new TableCell<>() {
            @Override protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) { setText(null); setStyle(""); }
                else {
                    setText(item);
                    setStyle(item.equals("Aprobado")
                            ? "-fx-text-fill: #27ae60; -fx-font-weight: bold;"
                            : "-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
                }
            }
        });
    }

    private void cargarCalificaciones(int idAlumno) {
        List<Inscripcion> inscripciones = insRepo.findByAlumno(idAlumno);
        List<FilaCalificacion> filas = new ArrayList<>();

        for (Inscripcion ins : inscripciones) {
            Optional<Calificacion> calOpt = calRepo.findByInscripcion(ins.getId());
            calOpt.ifPresent(cal -> {
                FilaCalificacion fila = new FilaCalificacion();
                // Nota: en un sistema completo se unirían los datos del grupo/materia
                fila.materia     = "Materia (Grupo #" + ins.getIdGrupo() + ")";
                fila.periodo     = "—";
                fila.p1          = String.format("%.1f", cal.getParcial1());
                fila.p2          = String.format("%.1f", cal.getParcial2());
                fila.p3          = String.format("%.1f", cal.getParcial3());
                fila.actividades = String.format("%.1f", cal.getActividades());
                fila.proyecto    = String.format("%.1f", cal.getProyecto());
                fila.promedio    = String.format("%.2f", cal.getPromedioFinal());
                fila.estatus     = cal.getEstatusTexto();
                filas.add(fila);
            });
        }
        tablaCalificaciones.setItems(FXCollections.observableArrayList(filas));
    }

    @FXML
    public void onCerrarSesion(ActionEvent e) {
        authService.logout();
        NavigationUtil.navigate("/com/universidad/fxml/login.fxml", "Inicio de Sesión");
    }

    /** Clase auxiliar para las filas de la tabla del alumno */
    public static class FilaCalificacion {
        String materia, periodo, p1, p2, p3, actividades, proyecto, promedio, estatus;
    }
}
