package datos;

import java.sql.SQLException;
import java.util.List;
import modelos.Carrito;

public interface CarritoDao {

    public List<Carrito> listar(Carrito carrito) throws SQLException;

    public void insertar(Carrito carrito) throws SQLException;

    public void eliminar(Carrito carrito) throws SQLException;

    public byte[] buscarImagen(Carrito carrito) throws SQLException;
    
    public void actualizar(Carrito carrito) throws SQLException;
    
    public void disminuir(Carrito carrito) throws SQLException;
    
    public void eliminarCarrito(Carrito carrito)  throws SQLException;
}
