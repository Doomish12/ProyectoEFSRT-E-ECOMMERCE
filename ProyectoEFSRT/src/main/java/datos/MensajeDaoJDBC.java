package datos;

import java.sql.*;
import java.util.*;
import modelos.Mensajes;
import static bd.Conexion.*;

public class MensajeDaoJDBC implements MensajeDao {

    private static final String SQL_LISTAR = "SELECT id, nombre, correo, mensaje, fecha_envio FROM mensajes";
    private static final String SQL_INSERTAR = "INSERT INTO mensajes (nombre, correo, mensaje) VALUES(?, ?, ?)";
    private static final String SQL_ELIMINAR = "DELETE FROM mensajes WHERE id= ?";

    private Connection conexionTransicional;

    public MensajeDaoJDBC() {
    }

    public MensajeDaoJDBC(Connection conexionTransicional) {
        this.conexionTransicional = conexionTransicional;
    }

    @Override
    public List<Mensajes> listar() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Mensajes mensajes = null;
        List<Mensajes> listar = new ArrayList<>();

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_LISTAR);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo");
                String mensaje = rs.getString("mensaje");
                Timestamp fechaEnvio = rs.getTimestamp("fecha_envio");
                
                mensajes = new Mensajes(id, nombre, correo, mensaje, fechaEnvio);
                listar.add(mensajes);
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
    public void insertar(Mensajes mensajes) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);

            stmt.setString(1, mensajes.getNombre());
            stmt.setString(2, mensajes.getCorreo());
            stmt.setString(3, mensajes.getMensaje());
            stmt.executeUpdate();

        } finally {

            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }
    }

    @Override
    public void eliminar(Mensajes mensajes) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_ELIMINAR);

            stmt.setInt(1, mensajes.getId());

            stmt.executeUpdate();

        } finally {

            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }
    }

}
