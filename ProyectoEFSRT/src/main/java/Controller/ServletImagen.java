package Controller;

import datos.ProductoDaoJDBC;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelos.Productos;

@WebServlet("/imagen")
public class ServletImagen extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Obtener el identificador del producto de la solicitud (por ejemplo, desde un parámetro)
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));
            
            // Obtener los datos de la imagen del controlador o base de datos utilizando el ID del producto
            Productos producto = new Productos(idProducto); // Suponiendo que tienes un constructor que acepta el ID
            byte[] imagen = new ProductoDaoJDBC().buscarImagen(producto);
            
            // Manejar el caso en el que imagen es null
            if (imagen != null) {
                // Configurar la respuesta para enviar datos de imagen
                response.setContentType("image/jpeg");
                response.getOutputStream().write(imagen);
            } else {
                // Si la imagen es null, enviar una imagen de relleno o un mensaje de error
                response.sendError(HttpServletResponse.SC_NOT_FOUND); // Enviar un error 404
            }
        } catch (SQLException ex) {
            // Manejar la excepción si ocurre
            ex.printStackTrace(System.out);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Enviar un error 500
        }
    }
}
