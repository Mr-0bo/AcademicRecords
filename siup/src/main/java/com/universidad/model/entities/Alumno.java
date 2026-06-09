package main.java.com.universidad.model.entities;

public class Alumno {
  public class Alumno extends Usuario {
    private String matricula;
    private int idCarrera;
    private String nombreCarrera;

    public Alumno() {
        this.setTipo(TipoUsuario.ALUMNO);
    }
    public Alumno(int id, String nombre, String correo, String contrasena, String matricula, int idCarrera) {
        super(id, nombre, correo, contrasena, TipoUsuario.ALUMNO);
        this.matricula = matricula;
        this.idCarrera = idCarrera;
    }
    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getIdCarrera() {
        return this.idCarrera;
    }
    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNombreCarrera() {
        return this.nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }
    public String toString() {
        String var10000 = this.getNombre();
        return var10000 + " (" + this.matricula + ")";
    }
}
