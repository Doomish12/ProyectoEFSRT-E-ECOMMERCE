package Controller;

import datos.AdminDaoJDBC;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelos.Admin;

@WebServlet("/ServletImgEncontrar")
public class ServletAdminImg extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Obtener el identificador del producto de la solicitud (por ejemplo, desde un parámetro)
            int id = Integer.parseInt(request.getParameter("id"));
            
            // Obtener los datos de la imagen del controlador o base de datos utilizando el ID del Admin
            Admin admin = new Admin(id);// Suponiendo que tienes un constructor que acepta el ID
            byte[] foto = new AdminDaoJDBC().buscarFoto(admin);
            
            // Manejar el caso en el que imagen es null
            if (foto != null) {
                // Configurar la respuesta para enviar datos de imagen
                response.setContentType("image/jpeg");
                response.getOutputStream().write(foto);
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
