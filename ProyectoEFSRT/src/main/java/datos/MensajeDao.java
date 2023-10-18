package datos;

import java.sql.SQLException;
import java.util.List;
import modelos.Mensajes;

public interface MensajeDao {
    
    public List<Mensajes> listar() throws SQLException;
    
    public void insertar(Mensajes mensajes) throws SQLException;
    
    public void eliminar(Mensajes mensajes) throws SQLException;
    
    
    
}
