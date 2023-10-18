package modelos;

import java.sql.Timestamp;

public class DetalleVenta {

    private int idVenta;
    private String usuario;
    private int idProducto;
    private int cantidad;
    private double precioTotal;
    private Timestamp fechaVenta;
    private String nombreProducto;
    private String cliente;
    private byte[] imagen;
    
    public DetalleVenta() {
    }

    public DetalleVenta(String usuario, int idProducto, int cantidad) {
        this.usuario = usuario;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public DetalleVenta(int idVenta, int idProducto, int cantidad, double precioTotal, Timestamp fechaVenta, String nombreProducto, String cliente) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
        this.fechaVenta = fechaVenta;
        this.nombreProducto = nombreProducto;
        this.cliente = cliente;
    }


    public DetalleVenta(int idVenta, String usuario, int idProducto, int cantidad, double precioTotal, Timestamp fechaVenta) {
        this.idVenta = idVenta;
        this.usuario = usuario;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
        this.fechaVenta = fechaVenta;
    }
    
    //PDF

    public DetalleVenta(int idVenta, int cantidad, double precioTotal, Timestamp fechaVenta, String nombreProducto, String cliente) {
        this.idVenta = idVenta;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
        this.fechaVenta = fechaVenta;
        this.nombreProducto = nombreProducto;
        this.cliente = cliente;
    }

    
    
    
    
    
    public DetalleVenta(String usuario) {
        this.usuario = usuario;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Timestamp getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Timestamp fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }


    
    
    
}   
