package com.universidad.controller;

import com.universidad.model.entities.*;
import com.universidad.service.AdminService;
import com.universidad.service.AuthService;
import com.universidad.util.AlertUtil;
import com.universidad.util.NavigationUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class

AdminDashboardController implements Initializable {

    // ── Alumnos ──────────────────────────────────────────────────────────────
    @FXML private TableView<Alumno> tablaAlumnos;
    @FXML private TableColumn<Alumno, String> colAlNombre;
    @FXML private TableColumn<Alumno, String> colAlMatricula;
    @FXML private TableColumn<Alumno, String> colAlCarrera;
    @FXML private TableColumn<Alumno, String> colAlCorreo;
    @FXML private TextField txtAlNombre;
    @FXML private TextField txtAlCorreo;
    @FXML private TextField txtAlMatricula;
    @FXML private PasswordField txtAlPass;
    @FXML private ComboBox<Carrera> cmbAlCarrera;

    // ── Profesores ───────────────────────────────────────────────────────────
    @FXML private TableView<Profesor> tablaProfesores;
    @FXML private TableColumn<Profesor, String> colPrNombre;
    @FXML private TableColumn<Profesor, String> colPrEmpleado;
    @FXML private TableColumn<Profesor, String> colPrCorreo;
    @FXML private TextField txtPrNombre;
    @FXML private TextField txtPrCorreo;
    @FXML private TextField txtPrEmpleado;
    @FXML private PasswordField txtPrPass;

    // ── Carreras ─────────────────────────────────────────────────────────────
    @FXML private TableView<Carrera> tablaCarreras;
    @FXML private TableColumn<Carrera, String> colCarNombre;
    @FXML private TextField txtCarNombre;

    // ── Materias ─────────────────────────────────────────────────────────────
    @FXML private TableView<Materia> tablaMaterias;
    @FXML private TableColumn<Materia, String> colMatNombre;
    @FXML private TableColumn<Materia, String> colMatCarrera;
    @FXML private TableColumn<Materia, String> colMatSemestre;
    @FXML private TextField txtMatNombre;
    @FXML private ComboBox<Carrera> cmbMatCarrera;
    @FXML private TextField txtMatSemestre;

    private final AdminService adminService = new AdminService();
    private final AuthService authService  = new AuthService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarColumnasAlumno();
        configurarColumnasProfesor();
        configurarColumnasCarrera();
        configurarColumnasMateria();
        cargarTodo();
        configurarSeleccionAlumno();
        configurarSeleccionProfesor();
        configurarSeleccionCarrera();
        configurarSeleccionMateria();
    }

    // ── Configuración de columnas ─────────────────────────────────────────────

    private void configurarColumnasAlumno() {
        colAlNombre.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombre()));
        colAlMatricula.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getMatricula()));
        colAlCarrera.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombreCarrera()));
        colAlCorreo.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCorreo()));
    }

    private void configurarColumnasProfesor() {
        colPrNombre.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombre()));
        colPrEmpleado.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNumeroEmpleado()));
        colPrCorreo.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCorreo()));
    }

    private void configurarColumnasCarrera() {
        colCarNombre.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombre()));
    }

    private void configurarColumnasMateria() {
        colMatNombre.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombre()));
        colMatCarrera.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombreCarrera()));
        colMatSemestre.setCellValueFactory(d -> new SimpleStringProperty(String.valueOf(d.getValue().getSemestre())));
    }

    // ── Carga de datos ────────────────────────────────────────────────────────

    private void cargarTodo() {
        tablaAlumnos.setItems(FXCollections.observableArrayList(adminService.listarAlumnos()));
        tablaProfesores.setItems(FXCollections.observableArrayList(adminService.listarProfesores()));
        tablaCarreras.setItems(FXCollections.observableArrayList(adminService.listarCarreras()));
        tablaMaterias.setItems(FXCollections.observableArrayList(adminService.listarMaterias()));
        cmbAlCarrera.setItems(FXCollections.observableArrayList(adminService.listarCarreras()));
        cmbMatCarrera.setItems(FXCollections.observableArrayList(adminService.listarCarreras()));
    }

    // ── Selección en tabla → llena el formulario ──────────────────────────────

    private void configurarSeleccionAlumno() {
        tablaAlumnos.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                txtAlNombre.setText(sel.getNombre());
                txtAlCorreo.setText(sel.getCorreo());
                txtAlMatricula.setText(sel.getMatricula());
                txtAlPass.clear();
                cmbAlCarrera.getItems().stream()
                        .filter(c -> c.getId() == sel.getIdCarrera())
                        .findFirst().ifPresent(cmbAlCarrera::setValue);
            }
        });
    }

    private void configurarSeleccionProfesor() {
        tablaProfesores.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                txtPrNombre.setText(sel.getNombre());
                txtPrCorreo.setText(sel.getCorreo());
                txtPrEmpleado.setText(sel.getNumeroEmpleado());
                txtPrPass.clear();
            }
        });
    }

    private void configurarSeleccionCarrera() {
        tablaCarreras.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) txtCarNombre.setText(sel.getNombre());
        });
    }

    private void configurarSeleccionMateria() {
        tablaMaterias.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                txtMatNombre.setText(sel.getNombre());
                txtMatSemestre.setText(String.valueOf(sel.getSemestre()));
                cmbMatCarrera.getItems().stream()
                        .filter(c -> c.getId() == sel.getIdCarrera())
                        .findFirst().ifPresent(cmbMatCarrera::setValue);
            }
        });
    }

    // ── CRUD Alumnos ──────────────────────────────────────────────────────────

    @FXML
    public void onGuardarAlumno(ActionEvent e) {
        if (txtAlNombre.getText().isBlank() || txtAlCorreo.getText().isBlank()
                || txtAlMatricula.getText().isBlank() || cmbAlCarrera.getValue() == null) {
            AlertUtil.advertencia("Datos incompletos", "Completa todos los campos del alumno.");
            return;
        }
        Alumno seleccionado = tablaAlumnos.getSelectionModel().getSelectedItem();
        Alumno alumno = seleccionado != null ? seleccionado : new Alumno();

        alumno.setNombre(txtAlNombre.getText().trim());
        alumno.setCorreo(txtAlCorreo.getText().trim());
        alumno.setMatricula(txtAlMatricula.getText().trim());
        alumno.setIdCarrera(cmbAlCarrera.getValue().getId());

        String pass = txtAlPass.getText();
        if (alumno.getId() == 0 && pass.isBlank()) {
            AlertUtil.advertencia("Contraseña requerida", "Ingresa una contraseña para el nuevo alumno.");
            return;
        }

        adminService.guardarAlumno(alumno, pass.isBlank() ? null : pass);
        limpiarFormAlumno();
        cargarTodo();
        AlertUtil.info("Éxito", "Alumno guardado correctamente.");
    }

    @FXML
    public void onEliminarAlumno(ActionEvent e) {
        Alumno sel = tablaAlumnos.getSelectionModel().getSelectedItem();
        if (sel == null) { AlertUtil.advertencia("Selección", "Elige un alumno."); return; }
        if (AlertUtil.confirmar("Eliminar", "¿Eliminar al alumno " + sel.getNombre() + "?")) {
            adminService.eliminarAlumno(sel.getId());
            limpiarFormAlumno();
            cargarTodo();
        }
    }

    @FXML public void onNuevoAlumno(ActionEvent e) {
        tablaAlumnos.getSelectionModel().clearSelection();
        limpiarFormAlumno();
    }

    private void limpiarFormAlumno() {
        txtAlNombre.clear(); txtAlCorreo.clear(); txtAlMatricula.clear(); txtAlPass.clear();
        cmbAlCarrera.setValue(null);
    }

    // ── CRUD Profesores ───────────────────────────────────────────────────────

    @FXML
    public void onGuardarProfesor(ActionEvent e) {
        if (txtPrNombre.getText().isBlank() || txtPrCorreo.getText().isBlank() || txtPrEmpleado.getText().isBlank()) {
            AlertUtil.advertencia("Datos incompletos", "Completa todos los campos del profesor.");
            return;
        }
        Profesor seleccionado = tablaProfesores.getSelectionModel().getSelectedItem();
        Profesor prof = seleccionado != null ? seleccionado : new Profesor();

        prof.setNombre(txtPrNombre.getText().trim());
        prof.setCorreo(txtPrCorreo.getText().trim());
        prof.setNumeroEmpleado(txtPrEmpleado.getText().trim());

        String pass = txtPrPass.getText();
        if (prof.getId() == 0 && pass.isBlank()) {
            AlertUtil.advertencia("Contraseña requerida", "Ingresa una contraseña para el nuevo profesor.");
            return;
        }

        adminService.guardarProfesor(prof, pass.isBlank() ? null : pass);
        limpiarFormProfesor();
        cargarTodo();
        AlertUtil.info("Éxito", "Profesor guardado correctamente.");
    }

    @FXML
    public void onEliminarProfesor(ActionEvent e) {
        Profesor sel = tablaProfesores.getSelectionModel().getSelectedItem();
        if (sel == null) { AlertUtil.advertencia("Selección", "Elige un profesor."); return; }
        if (AlertUtil.confirmar("Eliminar", "¿Eliminar al profesor " + sel.getNombre() + "?")) {
            adminService.eliminarProfesor(sel.getId());
            limpiarFormProfesor();
            cargarTodo();
        }
    }

    @FXML public void onNuevoProfesor(ActionEvent e) {
        tablaProfesores.getSelectionModel().clearSelection();
        limpiarFormProfesor();
    }

    private void limpiarFormProfesor() {
        txtPrNombre.clear(); txtPrCorreo.clear(); txtPrEmpleado.clear(); txtPrPass.clear();
    }

    // ── CRUD Carreras ─────────────────────────────────────────────────────────

    @FXML
    public void onGuardarCarrera(ActionEvent e) {
        if (txtCarNombre.getText().isBlank()) {
            AlertUtil.advertencia("Campo vacío", "Ingresa el nombre de la carrera.");
            return;
        }
        Carrera sel = tablaCarreras.getSelectionModel().getSelectedItem();
        Carrera carrera = sel != null ? sel : new Carrera();
        carrera.setNombre(txtCarNombre.getText().trim());
        adminService.guardarCarrera(carrera);
        txtCarNombre.clear();
        cargarTodo();
        AlertUtil.info("Éxito", "Carrera guardada.");
    }

    @FXML
    public void onEliminarCarrera(ActionEvent e) {
        Carrera sel = tablaCarreras.getSelectionModel().getSelectedItem();
        if (sel == null) { AlertUtil.advertencia("Selección", "Elige una carrera."); return; }
        if (AlertUtil.confirmar("Eliminar", "¿Eliminar la carrera " + sel.getNombre() + "?")) {
            adminService.eliminarCarrera(sel.getId());
            txtCarNombre.clear();
            cargarTodo();
        }
    }

    // ── CRUD Materias ─────────────────────────────────────────────────────────

    @FXML
    public void onGuardarMateria(ActionEvent e) {
        if (txtMatNombre.getText().isBlank() || cmbMatCarrera.getValue() == null || txtMatSemestre.getText().isBlank()) {
            AlertUtil.advertencia("Datos incompletos", "Completa todos los campos de la materia.");
            return;
        }
        try {
            int semestre = Integer.parseInt(txtMatSemestre.getText().trim());
            Materia sel = tablaMaterias.getSelectionModel().getSelectedItem();
            Materia mat = sel != null ? sel : new Materia();
            mat.setNombre(txtMatNombre.getText().trim());
            mat.setIdCarrera(cmbMatCarrera.getValue().getId());
            mat.setSemestre(semestre);
            adminService.guardarMateria(mat);
            limpiarFormMateria();
            cargarTodo();
            AlertUtil.info("Éxito", "Materia guardada.");
        } catch (NumberFormatException ex) {
            AlertUtil.error("Formato inválido", "El semestre debe ser un número entero.");
        }
    }

    @FXML
    public void onEliminarMateria(ActionEvent e) {
        Materia sel = tablaMaterias.getSelectionModel().getSelectedItem();
        if (sel == null) { AlertUtil.advertencia("Selección", "Elige una materia."); return; }
        if (AlertUtil.confirmar("Eliminar", "¿Eliminar la materia " + sel.getNombre() + "?")) {
            adminService.eliminarMateria(sel.getId());
            limpiarFormMateria();
            cargarTodo();
        }
    }

    @FXML public void onNuevaMateria(ActionEvent e) {
        tablaMaterias.getSelectionModel().clearSelection();
        limpiarFormMateria();
    }

    private void limpiarFormMateria() {
        txtMatNombre.clear(); txtMatSemestre.clear(); cmbMatCarrera.setValue(null);
    }

    // ── Cerrar sesión ─────────────────────────────────────────────────────────

    @FXML
    public void onCerrarSesion(ActionEvent e) {
        authService.logout();
        NavigationUtil.navigate("/com/universidad/fxml/login.fxml", "Inicio de Sesión");
    }
}
