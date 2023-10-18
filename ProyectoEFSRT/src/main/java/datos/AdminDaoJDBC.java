package datos;

import java.sql.*;
import java.util.*;
import modelos.Admin;
import static bd.Conexion.*;

public class AdminDaoJDBC implements AdminDao {

    private static final String SQL_LISTAR = "SELECT idAdmin, usuario, correo, password FROM admin";
    private static final String SQL_INSERTAR = "INSERT INTO admin (usuario, correo, password, foto) VALUES(?, ?, ?, ?)";
    private static final String SQL_IMAGEN = "SELECT foto FROM admin WHERE idAdmin = ?";
    private static final String SQL_USUARIO = "SELECT COUNT(*) as count FROM admin WHERE usuario = ?";
    private static final String SQL_ELIMINAR = "DELETE FROM admin WHERE idAdmin= ?";
    private static final String SQL_ENCONTRAR = "SELECT idAdmin, usuario, correo, password FROM admin WHERE idAdmin= ?";
    private static final String SQL_ACTUALIZAR = "UPDATE admin SET usuario= ?, correo= ? , password= ?, foto= ? WHERE idAdmin= ? ";
    private static final String SQL_LOGIN = "SELECT * FROM admin WHERE usuario= ? AND  password= ?";
    private static final String SQL_ID_ADMIN ="SELECT idAdmin FROM admin WHERE usuario = ?";
    
    private Connection conexionTransicional;

    public AdminDaoJDBC() {
    }

    public AdminDaoJDBC(Connection conexionTransicional) {
        this.conexionTransicional = conexionTransicional;
    }

    @Override
    public List<Admin> listar() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Admin admin = null;
        List<Admin> listar = new ArrayList<>();

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_LISTAR);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idAdmin = rs.getInt("idAdmin");
                String usuario = rs.getString("usuario");
                String correo = rs.getString("correo");
                String password = rs.getString("password");

                admin = new Admin(idAdmin, usuario, correo, password);
                listar.add(admin);
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
    public Admin buscar(Admin admin) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_ENCONTRAR, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, admin.getId());
            rs = stmt.executeQuery();
            rs.absolute(1);

            String usuario = rs.getString("usuario");
            String correo = rs.getString("correo");
            String password = rs.getString("password");

            admin.setUsuario(usuario);
            admin.setCorreo(correo);
            admin.setPassword(password);
        } finally {
            close(rs);
            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }
        return admin;
    }

    @Override
    public void insertar(Admin admin) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);

            stmt.setString(1, admin.getUsuario());
            stmt.setString(2, admin.getCorreo());
            stmt.setString(3, admin.getPassword());
            stmt.setBytes(4, admin.getFoto());
            stmt.executeUpdate();

        } finally {

            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }
    }

    @Override
    public void actualizar(Admin admin) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_ACTUALIZAR);

            stmt.setString(1, admin.getUsuario());
            stmt.setString(2, admin.getCorreo());
            stmt.setString(3, admin.getPassword());
            stmt.setBytes(4, admin.getFoto());
            stmt.setInt(5, admin.getId());
            stmt.executeUpdate();

        } finally {

            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }
    }

    @Override
    public void eliminar(Admin admin) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_ELIMINAR);

            stmt.setInt(1, admin.getId());

            stmt.executeUpdate();

        } finally {

            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }
    }

    @Override
    public boolean admin(Admin admin) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_USUARIO);
            stmt.setString(1, admin.getUsuario());
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
    public byte[] buscarFoto(Admin admin) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        byte[] foto = null;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_IMAGEN);
            stmt.setInt(1, admin.getId());
            rs = stmt.executeQuery();

            if (rs.next()) {
                foto = rs.getBytes("foto");
            }

        } finally {
            close(rs);
            close(stmt);
            if (this.conexionTransicional == null) {
                close(conn);
            }
        }
        return foto;
    }

    @Override
    public boolean login(Admin admin) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean usuarioValido = false;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_LOGIN);

            stmt.setString(1, admin.getUsuario());
            stmt.setString(2, admin.getPassword());

            rs = stmt.executeQuery();

            if (rs.next()) {
                // Si hay al menos una fila en el resultado, el usuario es válido              
                usuarioValido = true;
            }
            
        } finally {
            close(rs);
            close(stmt);
            if (conexionTransicional == null) {
                close(conn);
            }
        }
        return usuarioValido;
    }

    @Override
    public int idAdminObtener(Admin admin) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int idAdmin= -1;

        try {
            conn = this.conexionTransicional != null ? this.conexionTransicional : getConnection();
            stmt = conn.prepareStatement(SQL_ID_ADMIN);

            stmt.setString(1, admin.getUsuario());
            rs = stmt.executeQuery();

            if (rs.next()) {
                idAdmin = rs.getInt("idAdmin");
                
            }
            
        } finally {
            close(rs);
            close(stmt);
            if (conexionTransicional == null) {
                close(conn);
            }
        }
        return idAdmin;
    }
    
}


