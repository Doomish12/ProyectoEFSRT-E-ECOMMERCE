package datos;

import static bd.Conexion.*;
import java.sql.*;
import java.util.*;
import modelos.Carrito;

public class CarritoDaoJDBC implements CarritoDao {

    private static final String SQL_LISTAR = "SELECT cc.idCarrito, p.nombre AS nombreProducto, p.precio, cc.cantidad, p.idProducto, cc.precioTotal\n"
            + "FROM carritoCompras cc\n"
            + "JOIN productos p ON cc.idProducto = p.idProducto\n"
            + "JOIN clientes c ON cc.usuario = c.usuario\n"
            + "WHERE cc.usuario = ?";

    private static final String SQL_INSERTAR = "INSERT INTO carritoCompras (usuario, idProducto, cantidad, precioTotal)\n"
            + "VALUES (?, ?, ?, \n"
            + " (SELECT precio FROM productos WHERE idProducto = ?) * 1)";

    private static final String SQL_IMAGEN = "SELECT p.imagen\n"
            + "FROM carritoCompras cc\n"
            + "JOIN productos p ON cc.idProducto = p.idProducto\n"
            + "WHERE p.idProducto = ?";
    private static final String SQL_ELIMINAR = "DELETE FROM carritoCompras WHERE idCarrito = ?";

    private static final String SQL_ACTUALIZAR_CANT = "UPDATE carritoCompras\n"
            + "SET cantidad = ?,\n"
            + "precioTotal = (? * (SELECT precio FROM productos WHERE idProducto = ?))\n"
            + "WHERE usuario = ? AND idProducto = ?";
    
    private static final String SQL_DISMINUIR_CANT ="UPDATE carritoCompras SET cantidad = cantidad - 1, precioTotal = cantidad * (SELECT precio FROM productos WHERE idProducto = ?) WHERE usuario = ? AND idProducto = ?";
    
    private static final String SQL_BORRAR_CARRITO = "DELETE FROM carritoCompras WHERE usuario = ?";
    
    
    private Connection conexionTransicional;

    public CarritoDaoJDBC() {
    }

    public CarritoDaoJDBC(Connection conexionTransicional) {
        this.conexionTransicional = conexionTransicional;
    }

    @Override
    public List<Carrito> listar(Carrito carrito) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Carrito> listar = new ArrayList<>();

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_LISTAR);
            stmt.setString(1, carrito.getUsuario());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idCarrito = rs.getInt("idCarrito");
                String nombreProducto = rs.getString("nombreProducto");
                double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                int idProducto = rs.getInt("idProducto");
                double precioTotal = rs.getDouble("precioTotal");
                
                
                
                Carrito resultadoCarrito = new Carrito(idCarrito, nombreProducto, precio, cantidad, idProducto, precioTotal);
                listar.add(resultadoCarrito);
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
    public void insertar(Carrito carrito) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);

            stmt.setString(1, carrito.getUsuario());
            stmt.setInt(2, carrito.getIdProducto());
            stmt.setInt(3, carrito.getCantidad());
            stmt.setInt(4, carrito.getIdProducto());
            stmt.executeUpdate();

        } finally {

            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }
    }

    @Override
    public void eliminar(Carrito carrito) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_ELIMINAR);

            stmt.setInt(1, carrito.getIdCarrito());
            stmt.executeUpdate();

        } finally {

            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }
    }

    @Override
    public byte[] buscarImagen(Carrito carrito) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        byte[] imagen = null;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_IMAGEN);
            stmt.setInt(1, carrito.getIdProducto());
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
    public void actualizar(Carrito carrito) throws SQLException {
        
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_ACTUALIZAR_CANT);

            stmt.setInt(1, carrito.getCantidad());
            stmt.setDouble(2, carrito.getPrecioTotal());
            stmt.setInt(3, carrito.getIdProducto());
            stmt.setString(4, carrito.getUsuario());
            stmt.setInt(5, carrito.getIdProducto());
            stmt.executeUpdate();

        } finally {

            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }
    }

    @Override
    public void disminuir(Carrito carrito) throws SQLException {
                
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_DISMINUIR_CANT);

            stmt.setInt(1, carrito.getIdProducto());
            stmt.setString(2, carrito.getUsuario());
            stmt.setInt(3, carrito.getIdProducto());
            stmt.executeUpdate();

        } finally {

            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }
    }

    @Override
    public void eliminarCarrito(Carrito carrito) throws SQLException {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
         try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_BORRAR_CARRITO);

            stmt.setString(1, carrito.getUsuario());
            stmt.executeUpdate();

        } finally {

            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }
    }

}
