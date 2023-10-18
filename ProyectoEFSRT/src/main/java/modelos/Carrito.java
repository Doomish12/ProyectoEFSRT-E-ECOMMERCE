package modelos;

public class Carrito {

    private int idCarrito;
    private String nombreProducto;
    private double precio;
    private int cantidad;
    private double precioTotal;
    private String usuario;
    private int idProducto;
    private byte[] imagen;

    public Carrito() {
    }

    public Carrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Carrito(String usuario, int idProducto, int cantidad) {
        this.usuario = usuario;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public Carrito(int idCarrito, String nombreProducto, double precio, int cantidad, int idProducto, double precioTotal) {
        this.idCarrito = idCarrito;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.idProducto = idProducto;
        this.precioTotal = precioTotal;
    }
    //ACTUALIZAR CANTIDAD
    public Carrito(int cantidad, double precioTotal, String usuario, int idProducto) {
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
        this.usuario = usuario;
        this.idProducto = idProducto;
    }

    

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    
   
    
}
