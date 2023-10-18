
package datos;

import java.sql.SQLException;
import java.util.List;
import modelos.Clientes;
import modelos.DetalleVenta;


public interface ClientesDao {
      
      public List<Clientes> listar() throws  SQLException;
      
      public Clientes buscar(Clientes clientes) throws  SQLException;
      
      public Clientes buscarUser(Clientes clientes) throws SQLException;
      
      public void insertar(Clientes clientes) throws  SQLException;
      
      public void actualizar(Clientes clientes) throws  SQLException;
      
      public void eliminar(Clientes clientes) throws  SQLException;
      
      public boolean usuario(Clientes clientes)  throws  SQLException;
      
      public boolean login(Clientes clientes) throws SQLException;
      
      public List<DetalleVenta> listarPdf() throws SQLException;
 }
