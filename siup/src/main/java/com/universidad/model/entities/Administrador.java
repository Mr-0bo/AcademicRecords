package main.java.com.universidad.model.entities;

public class Administrador {
  public class Administrador extends Usuario {
    private String numeroEmpleado;

    public Administrador() {
        this.setTipo(TipoUsuario.ADMIN);
    }
}
