package com.universidad.controller;

import com.universidad.model.entities.*;
import com.universidad.repository.CalificacionRepository;
import com.universidad.repository.ConfiguracionEvaluacionRepository;
import com.universidad.repository.InscripcionRepository;
import com.universidad.service.AdminService;
import com.universidad.service.AuthService;
import com.universidad.service.EvaluacionService;
import com.universidad.util.AlertUtil;
import com.universidad.util.NavigationUtil;
import com.universidad.util.SessionManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProfesorDashboardController implements Initializable {

    @FXML private Label lblBienvenida;
    @FXML private ComboBox<Grupo> cmbGrupos;

    // Tabla de calificaciones
    @FXML private TableView<Calificacion> tablaCalificaciones;
    @FXML private TableColumn<Calificacion, String> colNombre;
    @FXML private TableColumn<Calificacion, String> colMatricula;
    @FXML private TableColumn<Calificacion, String> colP1;
    @FXML private TableColumn<Calificacion, String> colP2;
    @FXML private TableColumn<Calificacion, String> colP3;
    @FXML private TableColumn<Calificacion, String> colAct;
    @FXML private TableColumn<Calificacion, String> colProy;
    @FXML private TableColumn<Calificacion, String> colFinal;
    @FXML private TableColumn<Calificacion, String> colEstatus;

    // Formulario de edición
    @FXML private TextField txtP1;
    @FXML private TextField txtP2;
    @FXML private TextField txtP3;
    @FXML private TextField txtActividades;
    @FXML private TextField txtProyecto;
    @FXML private Label lblPromedioPreview;

    private final AdminService adminService   = new AdminService();
    private final CalificacionRepository calRepo = new CalificacionRepository();
    private final InscripcionRepository insRepo  = new InscripcionRepository();
    private final ConfiguracionEvaluacionRepository cfgRepo = new ConfiguracionEvaluacionRepository();
    private final EvaluacionService evalService  = new EvaluacionService();
    private final AuthService authService        = new AuthService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Usuario usuario = SessionManager.getInstance().getUsuarioActual();
        lblBienvenida.setText("Bienvenido, " + usuario.getNombre());

        configurarColumnas();
        cargarGrupos(usuario.getId());

        cmbGrupos.getSelectionModel().selectedItemProperty().addListener(
                (obs, old, sel) -> { if (sel != null) cargarCalificaciones(sel.getId()); }
        );

        tablaCalificaciones.getSelectionModel().selectedItemProperty().addListener(
                (obs, old, sel) -> { if (sel != null) llenarFormulario(sel); }
        );
    }

    private void configurarColumnas() {
        colNombre.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombreAlumno()));
        colMatricula.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getMatricula()));
        colP1.setCellValueFactory(d -> new SimpleStringProperty(String.format("%.1f", d.getValue().getParcial1())));
        colP2.setCellValueFactory(d -> new SimpleStringProperty(String.format("%.1f", d.getValue().getParcial2())));
        colP3.setCellValueFactory(d -> new SimpleStringProperty(String.format("%.1f", d.getValue().getParcial3())));
        colAct.setCellValueFactory(d -> new SimpleStringProperty(String.format("%.1f", d.getValue().getActividades())));
        colProy.setCellValueFactory(d -> new SimpleStringProperty(String.format("%.1f", d.getValue().getProyecto())));
        colFinal.setCellValueFactory(d -> new SimpleStringProperty(String.format("%.2f", d.getValue().getPromedioFinal())));
        colEstatus.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getEstatusTexto()));
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

    private void cargarGrupos(int idProfesor) {
        List<Grupo> grupos = adminService.listarGruposPorProfesor(idProfesor);
        cmbGrupos.setItems(FXCollections.observableArrayList(grupos));
        if (!grupos.isEmpty()) cmbGrupos.getSelectionModel().selectFirst();
    }

    private void cargarCalificaciones(int idGrupo) {
        tablaCalificaciones.setItems(FXCollections.observableArrayList(
                calRepo.findByGrupo(idGrupo)
        ));
        limpiarFormulario();
    }

    private void llenarFormulario(Calificacion cal) {
        txtP1.setText(String.valueOf(cal.getParcial1()));
        txtP2.setText(String.valueOf(cal.getParcial2()));
        txtP3.setText(String.valueOf(cal.getParcial3()));
        txtActividades.setText(String.valueOf(cal.getActividades()));
        txtProyecto.setText(String.valueOf(cal.getProyecto()));
        actualizarPreviewPromedio();
    }

    @FXML
    public void onCalcularPreview(ActionEvent e) {
        actualizarPreviewPromedio();
    }

    private void actualizarPreviewPromedio() {
        try {
            Grupo grupoSel = cmbGrupos.getValue();
            if (grupoSel == null) return;

            Optional<ConfiguracionEvaluacion> cfgOpt = cfgRepo.findByMateria(grupoSel.getIdMateria());
            if (cfgOpt.isEmpty()) {
                lblPromedioPreview.setText("Sin configuración");
                return;
            }

            Calificacion temp = new Calificacion();
            temp.setParcial1(Double.parseDouble(txtP1.getText()));
            temp.setParcial2(Double.parseDouble(txtP2.getText()));
            temp.setParcial3(Double.parseDouble(txtP3.getText()));
            temp.setActividades(Double.parseDouble(txtActividades.getText()));
            temp.setProyecto(Double.parseDouble(txtProyecto.getText()));

            evalService.calcularPromedio(temp, cfgOpt.get());
            lblPromedioPreview.setText(String.format("%.2f — %s", temp.getPromedioFinal(), temp.getEstatusTexto()));
        } catch (NumberFormatException ex) {
            lblPromedioPreview.setText("Valores inválidos");
        }
    }

    @FXML
    public void onGuardarCalificacion(ActionEvent e) {
        Calificacion sel = tablaCalificaciones.getSelectionModel().getSelectedItem();
        if (sel == null) { AlertUtil.advertencia("Selección", "Elige un alumno de la tabla."); return; }

        try {
            double p1   = Double.parseDouble(txtP1.getText());
            double p2   = Double.parseDouble(txtP2.getText());
            double p3   = Double.parseDouble(txtP3.getText());
            double act  = Double.parseDouble(txtActividades.getText());
            double proy = Double.parseDouble(txtProyecto.getText());

            if (!evalService.calificacionValida(p1) || !evalService.calificacionValida(p2)
                    || !evalService.calificacionValida(p3) || !evalService.calificacionValida(act)
                    || !evalService.calificacionValida(proy)) {
                AlertUtil.error("Valor inválido", "Las calificaciones deben estar entre 0 y 10.");
                return;
            }

            Grupo grupoSel = cmbGrupos.getValue();
            Optional<ConfiguracionEvaluacion> cfgOpt = cfgRepo.findByMateria(grupoSel.getIdMateria());
            if (cfgOpt.isEmpty()) {
                AlertUtil.error("Sin configuración", "Esta materia no tiene configuración de evaluación.");
                return;
            }

            sel.setParcial1(p1); sel.setParcial2(p2); sel.setParcial3(p3);
            sel.setActividades(act); sel.setProyecto(proy);
            evalService.calcularPromedio(sel, cfgOpt.get());
            calRepo.save(sel);
            cargarCalificaciones(grupoSel.getId());
            AlertUtil.info("Guardado", "Calificación actualizada correctamente.");
        } catch (NumberFormatException ex) {
            AlertUtil.error("Formato inválido", "Ingresa solo números en los campos de calificación.");
        } catch (IllegalArgumentException ex) {
            AlertUtil.error("Configuración inválida", ex.getMessage());
        }
    }

    private void limpiarFormulario() {
        txtP1.clear(); txtP2.clear(); txtP3.clear();
        txtActividades.clear(); txtProyecto.clear();
        lblPromedioPreview.setText("—");
    }

    @FXML
    public void onCerrarSesion(ActionEvent e) {
        authService.logout();
        NavigationUtil.navigate("/com/universidad/fxml/login.fxml", "Inicio de Sesión");
    }
}
