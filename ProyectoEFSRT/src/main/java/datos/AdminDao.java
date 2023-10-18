package datos;

import java.sql.SQLException;
import java.util.List;
import modelos.Admin;

public interface AdminDao {
    
    public List<Admin> listar() throws SQLException;
    
    public Admin buscar(Admin admin) throws SQLException;
    
    public void insertar(Admin admin) throws SQLException;
    
    public void actualizar(Admin admin) throws SQLException;
    
    public void eliminar(Admin admin) throws SQLException;
    
    public boolean admin(Admin admin) throws SQLException;
    
    public byte[] buscarFoto(Admin admin) throws  SQLException;
    
    public boolean login(Admin admin) throws SQLException;

    public int idAdminObtener(Admin admin) throws SQLException;
}
