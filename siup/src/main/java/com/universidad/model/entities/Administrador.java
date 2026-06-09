package main.java.com.universidad.model.entities;

public class Administrador {
  public class Administrador extends Usuario {
    private String numeroEmpleado;

    public Administrador() {
        this.setTipo(TipoUsuario.ADMIN);
    }
    public Administrador(int id, String nombre, String correo, String contrasena, String numeroEmpleado) {
        super(id, nombre, correo, contrasena, TipoUsuario.ADMIN);
        this.numeroEmpleado = numeroEmpleado;
    }
    public String getNumeroEmpleado() {
        return this.numeroEmpleado;
    }
}
