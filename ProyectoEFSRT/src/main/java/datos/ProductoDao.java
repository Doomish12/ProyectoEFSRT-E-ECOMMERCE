package datos;

import java.sql.SQLException;
import java.util.List;
import modelos.Productos;

public interface ProductoDao {
    
    public List<Productos> listar() throws SQLException;
           
    public byte[] buscarImagen(Productos productos) throws SQLException;
    
    public Productos buscar(Productos productos) throws SQLException;
    
    public void insertar(Productos productos) throws SQLException;
    
    public void atualizar(Productos productos) throws SQLException;
    
    public void eliminar(Productos productos) throws SQLException;
}
