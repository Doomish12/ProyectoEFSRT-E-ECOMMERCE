package datos;

import static bd.Conexion.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelos.DetalleVenta;

public class DetalleVentaDaoJDBC implements DetalleVentaDao {
    
    private static final String SQL_INSERTAR_VENTA = "INSERT INTO detalleVenta (usuario, idProducto, cantidad, precioTotal) "
            + "SELECT ?, idProducto, cantidad, precioTotal FROM carritoCompras WHERE usuario = ?;";
    
    private static final String SQL_ELIMINAR_DE_CARRITO = "DELETE FROM carritoCompras WHERE usuario = ?";
    private static final String SQL_ACTUALIZAR_CANTIDAD = "UPDATE productos SET cantidad = cantidad - ? WHERE idProducto = ?";
    private static final String SQL_LISTAR_VENTA = "SELECT dv.idVenta, c.nombres,p.idProducto,p.nombre AS nombreProducto, dv.cantidad, dv.precioTotal, dv.fechaVenta \n"
            + "FROM detalleVenta dv\n"
            + "INNER JOIN productos p ON dv.idProducto = p.idProducto\n"
            + "INNER JOIN clientes c ON dv.usuario = c.usuario\n"
            + "WHERE c.usuario = ?";
    
    private static final String SQL_IMAGEN = "SELECT p.imagen\n"
            + "FROM detalleVenta dv\n"
            + "JOIN productos p ON dv.idProducto = p.idProducto\n"
            + "WHERE p.idProducto = ?";
    
    private static final String SQL_LISTAR_PDF = "SELECT dv.idVenta, c.nombres,p.nombre AS nombreProducto, dv.cantidad, dv.precioTotal, dv.fechaVenta \n"
            + "FROM detalleVenta dv\n"
            + "INNER JOIN productos p ON dv.idProducto = p.idProducto\n"
            + "INNER JOIN clientes c ON dv.usuario = c.usuario\n"
            + "WHERE c.usuario = ?";

    //PARA EL HISTORIAL DE VENTAS
    private static final String SQL_INSERTAR_HISTORIAL_VENTA = "INSERT INTO historialVentas (usuario, idProducto, cantidad, precioTotal) "
            + "SELECT ?, idProducto, cantidad, precioTotal FROM detalleVenta WHERE usuario = ?";
    
    private static final String SQL_ELIMINAR_DE_DETALLE_VENTA = "DELETE FROM detalleVenta WHERE usuario = ?";
    
    private static final String SQL_VENTAS_TOTALES = "SELECT precioTotal FROM historialVentas";
    
    private Connection conexionTransicional;
    
    public DetalleVentaDaoJDBC() {
    }
    
    public DetalleVentaDaoJDBC(Connection conexionTransicional) {
        this.conexionTransicional = conexionTransicional;
    }
    
    @Override
    public void insertarVenta(DetalleVenta detalleVenta) throws SQLException {
        
        Connection conn = null;
        PreparedStatement stmtInsertra = null;
        PreparedStatement stmtEliminar = null;
        PreparedStatement stmtCantidad = null;
        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            conn.setAutoCommit(false);  // Deshabilitar la confirmación automática para manejar transacciones

            //INSERTAR VENTA
            stmtInsertra = conn.prepareStatement(SQL_INSERTAR_VENTA);
            stmtInsertra.setString(1, detalleVenta.getUsuario());
            stmtInsertra.setString(2, detalleVenta.getUsuario());
            
            stmtInsertra.executeUpdate();
            System.out.println("INSERTAR REALIZADO VENTA");
            // Eliminar de carritoCompras
            stmtEliminar = conn.prepareStatement(SQL_ELIMINAR_DE_CARRITO);
            stmtEliminar.setString(1, detalleVenta.getUsuario());
            stmtEliminar.executeUpdate();
            System.out.println("SE ELIMINO DEL CARRTIO");

            //ACTUALIZAR CANTIDAD
            stmtCantidad = conn.prepareStatement(SQL_ACTUALIZAR_CANTIDAD);
            stmtCantidad.setInt(1, detalleVenta.getCantidad());
            stmtCantidad.setInt(2, detalleVenta.getIdProducto());
            stmtCantidad.executeUpdate();
            System.out.println("CANTIDAD ACTUALIZADA EN LA TABLA PRODUCTOS");

            // Confirmar la transacción
            conn.commit();
            
        } catch (SQLException e) {
            // Manejar cualquier excepción
            if (conn != null) {
                try {
                    // Revertir la transacción en caso de error
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace(System.out);
                }
            }
            e.printStackTrace(System.out);
        } finally {
            close(stmtInsertra);
            close(stmtEliminar);
            if (conexionTransicional == null) {
                close(conn);
            }
        }
    }
    
    @Override
    public List<DetalleVenta> listarVenta(DetalleVenta detalleVenta) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<DetalleVenta> listar = new ArrayList<>();
        
        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_LISTAR_VENTA);
            stmt.setString(1, detalleVenta.getUsuario());
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                int idVenta = rs.getInt("idVenta");
                String cliente = rs.getString("nombres");
                int idProducto = rs.getInt("idProducto");
                String nombPro = rs.getString("nombreProducto");
                int cantidad = rs.getInt("cantidad");
                double precioTotal = rs.getDouble("precioTotal");
                Timestamp fechaVenta = rs.getTimestamp("fechaVenta");
                
                DetalleVenta resultado = new DetalleVenta(idVenta, idProducto, cantidad, precioTotal, fechaVenta, nombPro, cliente);
                listar.add(resultado);
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
    public byte[] buscarImagen(DetalleVenta detalleVenta) throws SQLException {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        byte[] imagen = null;
        
        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_IMAGEN);
            stmt.setInt(1, detalleVenta.getIdProducto());
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
    public List<DetalleVenta> listarPdf(DetalleVenta detalleVenta) throws SQLException {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<DetalleVenta> listar = new ArrayList<>();
        
        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_LISTAR_PDF);
            stmt.setString(1, detalleVenta.getUsuario());
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                int idVenta = rs.getInt("idVenta");
                String cliente = rs.getString("nombres");
                String nombPro = rs.getString("nombreProducto");
                int cantidad = rs.getInt("cantidad");
                double precioTotal = rs.getDouble("precioTotal");
                Timestamp fechaVenta = rs.getTimestamp("fechaVenta");
                
                DetalleVenta resultado = new DetalleVenta(idVenta, cantidad, precioTotal, fechaVenta, nombPro, cliente);
                listar.add(resultado);
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
    public void insertarHistorialV(DetalleVenta detalleVenta) throws SQLException {
        
        Connection conn = null;
        PreparedStatement stmtInsertra = null;
        PreparedStatement stmtEliminar = null;
        
        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            conn.setAutoCommit(false);  // Deshabilitar la confirmación automática para manejar transacciones

            //INSERTAR VENTA
            stmtInsertra = conn.prepareStatement(SQL_INSERTAR_HISTORIAL_VENTA);
            stmtInsertra.setString(1, detalleVenta.getUsuario());
            stmtInsertra.setString(2, detalleVenta.getUsuario());
            
            stmtInsertra.executeUpdate();
            System.out.println("INSERTAR REALIZADO VENTA");
            // Eliminar de carritoCompras
            stmtEliminar = conn.prepareStatement(SQL_ELIMINAR_DE_DETALLE_VENTA);
            stmtEliminar.setString(1, detalleVenta.getUsuario());
            stmtEliminar.executeUpdate();
            System.out.println("SE ELIMINO DEL CARRTIO");

            // Confirmar la transacción
            conn.commit();
            
        } catch (SQLException e) {
            // Manejar cualquier excepción
            if (conn != null) {
                try {
                    // Revertir la transacción en caso de error
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace(System.out);
                }
            }
            e.printStackTrace(System.out);
        } finally {
            close(stmtInsertra);
            close(stmtEliminar);
            if (conexionTransicional == null) {
                close(conn);
            }
        }
    }
    
    @Override
    public List<DetalleVenta> listarTotalVentas() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<DetalleVenta> listar = new ArrayList<>();
        
        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_VENTAS_TOTALES);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                double precioTotal = rs.getDouble("precioTotal");
                
                DetalleVenta resultado = new DetalleVenta();
                resultado.setPrecioTotal(precioTotal);
                listar.add(resultado);
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
    
}
