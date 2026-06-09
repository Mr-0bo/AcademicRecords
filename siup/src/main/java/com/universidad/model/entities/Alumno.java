package main.java.com.universidad.model.entities;

public class Alumno {
  public class Alumno extends Usuario {
    private String matricula;
    private int idCarrera;
    private String nombreCarrera;

    public Alumno() {
        this.setTipo(TipoUsuario.ALUMNO);
    }
}
