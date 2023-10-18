package datos;

import java.util.*;
import modelos.Productos;
import static bd.Conexion.*;
import java.sql.*;

public class ProductoDaoJDBC implements ProductoDao {

    private static final String SQL_LISTAR = "SELECT idProducto, nombre, descripcion, precio, cantidad FROM productos";
    private static final String SQL_IMAGEN = "SELECT imagen FROM productos WHERE idProducto = ?";
    private static final String SQL_INSERTAR = "INSERT INTO productos (nombre, descripcion, precio, cantidad, imagen) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_ELIMINAR = "DELETE FROM productos WHERE idProducto = ?";
    private static final String SQL_ENCONTRAR = "SELECT idProducto, nombre, descripcion, precio, cantidad FROM productos WHERE idProducto= ?";
    private static final String SQL_ACTUALIZAR = "UPDATE productos SET nombre= ?, descripcion =?, precio= ?, cantidad=?, imagen= ? WHERE idProducto = ?";

    private Connection conexionTransicional;

    public ProductoDaoJDBC() {
    }

    public ProductoDaoJDBC(Connection conexionTransicional) {
        this.conexionTransicional = conexionTransicional;
    }

    @Override
    public List<Productos> listar() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Productos productos = null;
        List<Productos> listar = new ArrayList<>();

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_LISTAR);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");

                productos = new Productos(idProducto, nombre, descripcion, precio, cantidad);
                listar.add(productos);
            }
        } finally {
            close(rs);
            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }
        return listar;
    }

    @Override
    public byte[] buscarImagen(Productos productos) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        byte[] imagen = null;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_IMAGEN);
            stmt.setInt(1, productos.getId());
            rs = stmt.executeQuery();

            if (rs.next()) {
                imagen = rs.getBytes("imagen");
            }

        } finally {
            close(rs);
            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }
        return imagen;
    }

    @Override
    public Productos buscar(Productos productos) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_ENCONTRAR, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, productos.getId());
            rs = stmt.executeQuery();
            rs.absolute(1); //La primera posicion

            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            double precio = rs.getDouble("precio");
            int cantidad = rs.getInt("cantidad");
            
            productos.setNombre(nombre);
            productos.setDescripcion(descripcion);
            productos.setPrecio(precio);
            productos.setCantidad(cantidad);

        } finally {
            close(rs);
            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }
        return productos;
    }

    @Override
    public void insertar(Productos productos) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);

            stmt.setString(1, productos.getNombre());
            stmt.setString(2, productos.getDescripcion());
            stmt.setDouble(3, productos.getPrecio());
            stmt.setInt(4, productos.getCantidad());
            stmt.setBytes(5, productos.getImagen());
            stmt.executeUpdate();

        } finally {

            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }
    }

    @Override
    public void atualizar(Productos productos) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_ACTUALIZAR);

            stmt.setString(1, productos.getNombre());
            stmt.setString(2, productos.getDescripcion());
            stmt.setDouble(3, productos.getPrecio());
            stmt.setInt(4, productos.getCantidad());
            stmt.setBytes(5, productos.getImagen());
            stmt.setInt(6, productos.getId());
            stmt.executeUpdate();

        } finally {

            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }
    }

    @Override
    public void eliminar(Productos productos) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_ELIMINAR);

            stmt.setInt(1, productos.getId());
            stmt.executeUpdate();

        } finally {

            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }
    }

}
