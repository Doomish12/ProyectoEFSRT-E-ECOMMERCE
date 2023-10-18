package modelos;

public class Clientes {

    private int id;
    private String nombres;
    private int edad;
    private String direccion;
    private String usuario;
    private String correo;
    private String password;
    
    public Clientes() {
    }

    public Clientes(int id) {
        this.id = id;
    }

    public Clientes(String nombres, int edad, String direccion, String usuario, String correo, String password) {
        this.nombres = nombres;
        this.edad = edad;
        this.direccion = direccion;
        this.usuario = usuario;
        this.correo = correo;
        this.password = password;
    }

    
    
    
    public Clientes(int id, String nombres, int edad, String direccion, String correo) {
        this.id = id;
        this.nombres = nombres;
        this.edad = edad;
        this.direccion = direccion;
        this.correo = correo;
    }

    public Clientes(int id, String nombres, int edad, String direccion, String usuario, String correo, String password) {
        this.id = id;
        this.nombres = nombres;
        this.edad = edad;
        this.direccion = direccion;
        this.usuario = usuario;
        this.correo = correo;
        this.password = password;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

   
      
    
  
    
    
    
}
