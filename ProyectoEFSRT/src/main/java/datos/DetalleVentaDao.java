package datos;

import java.sql.SQLException;
import java.util.List;
import modelos.DetalleVenta;

public interface DetalleVentaDao {

    public void insertarVenta(DetalleVenta detalleVenta) throws SQLException;

    public List<DetalleVenta> listarVenta(DetalleVenta detalleVenta) throws SQLException;

    public byte[] buscarImagen(DetalleVenta detalleVenta) throws SQLException;

    public List<DetalleVenta> listarPdf(DetalleVenta detalleVenta) throws SQLException;

    public void insertarHistorialV(DetalleVenta detalleVenta) throws SQLException;
    
    public List<DetalleVenta> listarTotalVentas() throws SQLException;
}
