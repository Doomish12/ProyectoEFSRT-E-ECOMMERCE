package modelos;


public class Admin {
    
    private int id;
    private String usuario;
    private String correo;
    private String password;
    private byte[] foto;
    
    public Admin() {
    }

    public Admin(int id) {
        this.id = id;
    }

    public Admin(String usuario, String correo, String password, byte[] foto) {
        this.usuario = usuario;
        this.correo = correo;
        this.password = password;
        this.foto = foto;
    }

    public Admin(int id, String usuario, String correo, String password) {
        this.id = id;
        this.usuario = usuario;
        this.correo = correo;
        this.password = password;
    }

    
    
    public Admin(int id, String usuario, String correo, String password, byte[] foto) {
        this.id = id;
        this.usuario = usuario;
        this.correo = correo;
        this.password = password;
        this.foto = foto;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    
    
    
}
