package main.java.com.universidad.model.entities;

public class Usuario {
  public class Usuario {
    private int id;
    private String nombre;
    private String correo;
    private String contrasena;
    private TipoUsuario tipo;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String correo, String contrasena, TipoUsuario tipo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.tipo = tipo;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return this.correo;
    }
    
}
