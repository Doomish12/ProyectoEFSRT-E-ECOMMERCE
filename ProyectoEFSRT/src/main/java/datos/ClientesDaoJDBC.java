package datos;

import static bd.Conexion.*;
import java.sql.*;
import java.util.*;
import modelos.Clientes;
import modelos.DetalleVenta;

public class ClientesDaoJDBC implements ClientesDao {

    private static final String SQL_LISTAR = "SELECT id, nombres, edad, direccion,correo FROM clientes";
    private static final String SQL_INSERTAR = "INSERT INTO clientes (nombres, edad, direccion, usuario, correo, password) VALUES(?, ?, ?, ?, ? ,?)";
    private static final String SQL_USUARIO = "SELECT COUNT(*) as count FROM clientes WHERE usuario = ?";
    private static final String SQL_ELIMINAR = "DELETE FROM clientes WHERE id= ?";
    private static final String SQL_ENCONTRAR = "SELECT id, nombres, edad, direccion, usuario, password, correo  FROM clientes WHERE id= ?";
    private static final String SQL_ACTUALIZAR = "UPDATE clientes SET nombres= ?, edad= ?, direccion= ?, usuario= ? , password= ?, correo= ? WHERE id= ? ";
    private static final String SQL_LOGIN = "SELECT * FROM clientes WHERE usuario= ? AND  password= ?";
    private static final String SQL_ENCONTRAR_USER = "SELECT id, nombres, edad, direccion, usuario, password, correo  FROM clientes WHERE usuario= ?";

    //LISTAR PDF:
    private static final String SQL_LISTAR_PDF = "SELECT hv.idVenta, c.nombres , p.nombre AS nombreProducto, hv.cantidad, hv.precioTotal, hv.fechaVenta \n"
            + "FROM historialVentas hv\n"
            + "INNER JOIN productos p ON hv.idProducto = p.idProducto\n"
            + "INNER JOIN clientes c ON hv.usuario = c.usuario\n"
            + "ORDER BY hv.fechaVenta ASC";

    private Connection conexionTransicional;

    public ClientesDaoJDBC() {
    }

    public ClientesDaoJDBC(Connection conexionTransicional) {
        this.conexionTransicional = conexionTransicional;
    }

    @Override
    public List<Clientes> listar() throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Clientes clientes = null;
        List<Clientes> listar = new ArrayList<>();

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_LISTAR);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombres = rs.getString("nombres");
                int edad = rs.getInt("edad");
                String direccion = rs.getString("direccion");
                String correo = rs.getString("correo");
                clientes = new Clientes(id, nombres, edad, direccion, correo);
                listar.add(clientes);
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
    public Clientes buscar(Clientes clientes) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_ENCONTRAR, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, clientes.getId());
            rs = stmt.executeQuery();
            rs.absolute(1);

            String nombres = rs.getString("nombres");
            int edad = rs.getInt("edad");
            String direccion = rs.getString("direccion");
            String usuario = rs.getString("usuario");
            String password = rs.getString("password");
            String correo = rs.getString("correo");

            clientes.setNombres(nombres);
            clientes.setEdad(edad);
            clientes.setDireccion(direccion);
            clientes.setUsuario(usuario);
            clientes.setPassword(password);
            clientes.setCorreo(correo);
        } finally {
            close(rs);
            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }
        return clientes;
    }

    @Override
    public void insertar(Clientes clientes) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);

            stmt.setString(1, clientes.getNombres());
            stmt.setInt(2, clientes.getEdad());
            stmt.setString(3, clientes.getDireccion());
            stmt.setString(4, clientes.getUsuario());
            stmt.setString(5, clientes.getCorreo());
            stmt.setString(6, clientes.getPassword());
            stmt.executeUpdate();

        } finally {

            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }

    }

    @Override
    public void actualizar(Clientes clientes) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_ACTUALIZAR);

            stmt.setString(1, clientes.getNombres());
            stmt.setInt(2, clientes.getEdad());
            stmt.setString(3, clientes.getDireccion());
            stmt.setString(4, clientes.getUsuario());
            stmt.setString(5, clientes.getPassword());
            stmt.setString(6, clientes.getCorreo());
            stmt.setInt(7, clientes.getId());
            stmt.executeUpdate();

        } finally {

            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }

    }

    @Override
    public void eliminar(Clientes clientes) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_ELIMINAR);

            stmt.setInt(1, clientes.getId());

            stmt.executeUpdate();

        } finally {

            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }
    }

    @Override
    public boolean usuario(Clientes clientes) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_USUARIO);
            stmt.setString(1, clientes.getUsuario());
            rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }

        } finally {

            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }
        return false;
    }

    @Override
    public boolean login(Clientes clientes) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean usuarioValido = false;
        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_LOGIN);

            stmt.setString(1, clientes.getUsuario());
            stmt.setString(2, clientes.getPassword());
            rs = stmt.executeQuery();

            if (rs.next()) {
                usuarioValido = true;
            }

        } finally {
            close(rs);
            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }

        }
        return usuarioValido;
    }

    @Override
    public Clientes buscarUser(Clientes clientes) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_ENCONTRAR_USER, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, clientes.getUsuario());
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Si hay al menos una fila en el ResultSet
                int id = rs.getInt("id");
                String nombres = rs.getString("nombres");
                int edad = rs.getInt("edad");
                String direccion = rs.getString("direccion");
                String usuario = rs.getString("usuario");
                String password = rs.getString("password");
                String correo = rs.getString("correo");

                clientes.setId(id);
                clientes.setNombres(nombres);
                clientes.setEdad(edad);
                clientes.setDireccion(direccion);
                clientes.setUsuario(usuario);
                clientes.setPassword(password);
                clientes.setCorreo(correo);
            } else {
                System.out.println("ERROR SQL CONSULTA USUARIO ENCONTRAR");
            }
        } finally {
            close(rs);
            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }
        return clientes;
    }

    @Override
    public List<DetalleVenta> listarPdf() throws SQLException {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<DetalleVenta> listar = new ArrayList<>();
        
        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_LISTAR_PDF);
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

}
