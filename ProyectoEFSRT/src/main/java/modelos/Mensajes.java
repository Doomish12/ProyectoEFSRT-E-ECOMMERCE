package modelos;

import java.sql.Timestamp;

public class Mensajes {
    
    private int id;
    private String nombre;
    private String correo;
    private String mensaje;
    private Timestamp fechaEnvio;

    public Mensajes() {
    }

    public Mensajes(int id) {
        this.id = id;
    }

    public Mensajes(String nombre, String correo, String mensaje) {
        this.nombre = nombre;
        this.correo = correo;
        this.mensaje = mensaje;
    }

    

    public Mensajes(int id, String nombre, String correo, String mensaje, Timestamp fechaEnvio) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.mensaje = mensaje;
        this.fechaEnvio = fechaEnvio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Timestamp getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Timestamp fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
    
    
    
    
}
