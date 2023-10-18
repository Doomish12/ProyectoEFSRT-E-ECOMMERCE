package Controller;

import com.google.gson.JsonObject;
import datos.ProductoDaoJDBC;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelos.Productos;

@WebServlet("/ServletProducto")
public class ServletProducto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        try {
            if (accion != null) {
                switch (accion) {
                    case "listar":
                        this.listadoProducto(request, response);
                        break;
                    case "eliminar":
                        this.eliminarProducto(request, response);
                        break;
                    case "encontrar":
                        this.buscarProducto(request, response);
                        break;
                    case "listarPro":
                        this.listadoTiendaProducto(request, response);
                        break;
                    default:
                        this.listadoProducto(request, response);
                        break;
                }
            } else {
                this.listadoProducto(request, response);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

    }
      //LISTADO PARA MOSTRAR LOS PRODUCTO EN LA PAGINA productos.jsp del admin panel
    protected void listadoProducto(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        List<Productos> listar = new ProductoDaoJDBC().listar();
        HttpSession session = request.getSession();
        session.setAttribute("listaProducto", listar);
        response.sendRedirect("productos.jsp");
    }
    
    //LISTADO PARA MOSTRAR LOS PRODUCTO EN LA PAGINA tiendaPro.jsp
    protected void listadoTiendaProducto(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        List<Productos> listar = new ProductoDaoJDBC().listar();
        HttpSession session = request.getSession();
        session.setAttribute("tiendaPro", listar);
        response.sendRedirect("tiendaPro.jsp");
    }
     //ELIMINA PRODUCTO DEL ADMIN PANEL
    protected void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Productos eliminar = new Productos(id);
        new ProductoDaoJDBC().eliminar(eliminar);

        this.listadoProducto(request, response);

    }
     //BUSCA PRODUCTO DEL ADMIN PANEL
    protected void buscarProducto(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Productos buscar = new Productos(id);
        new ProductoDaoJDBC().buscar(buscar);

        // Crear un objeto JSON con los datos del producto
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("id", buscar.getId());
        jsonResponse.addProperty("nombre", buscar.getNombre());
        jsonResponse.addProperty("descripcion", buscar.getDescripcion());
        jsonResponse.addProperty("precio", buscar.getPrecio());
        jsonResponse.addProperty("cantidad", buscar.getCantidad());

        // Configurar la respuesta como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Enviar la respuesta JSON al cliente
        response.getWriter().write(jsonResponse.toString());

    }



}
