package main.java.com.universidad.model.entities;

public class Profesor {
  public class Profesor extends Usuario {
    private String numeroEmpleado;

    public Profesor() {
        this.setTipo(TipoUsuario.PROFESOR);
    }

    public Profesor(int id, String nombre, String correo, String contrasena, String numeroEmpleado) {
        super(id, nombre, correo, contrasena, TipoUsuario.PROFESOR);
        this.numeroEmpleado = numeroEmpleado;
    }
}
