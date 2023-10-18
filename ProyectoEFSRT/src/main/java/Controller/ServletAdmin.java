package Controller;

import com.google.gson.JsonObject;
import datos.AdminDaoJDBC;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelos.Admin;

@WebServlet("/ServletAdmin")
public class ServletAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "listar":
                        this.listarAdmin(request, response);
                        break;
                    case "eliminar":
                        this.eliminarAdmin(request, response);
                        break;
                    case "encontrar":
                        this.buscarAdmin(request, response);
                        break;
                    case "cerrar":
                        this.cerrarSession(request, response);
                        break;
                    case "tienda":
                        this.tienda(request, response);
                        break;
                    default:
                        this.listarAdmin(request, response);
                        break;
                }
            } else {
                response.sendRedirect("adminLogin.jsp");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "login":
                        this.loginAdmin(request, response);
                        break;
                    default:
                        response.sendRedirect("adminLogin.jsp");
                        break;
                }
            } else {
                response.sendRedirect("adminLogin.jsp");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

    }

    protected void listarAdmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        List<Admin> listar = new AdminDaoJDBC().listar();
        HttpSession session = request.getSession();
        session.setAttribute("listaAdmin", listar);
        response.sendRedirect("admin.jsp");
    }

    protected void eliminarAdmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Admin eliminar = new Admin(id);
        new AdminDaoJDBC().eliminar(eliminar);

        this.listarAdmin(request, response);
    }

    protected void buscarAdmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Admin buscar = new Admin(id);
        new AdminDaoJDBC().buscar(buscar);

        // Crear un objeto JSON con los datos del libro
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("id", buscar.getId());
        jsonResponse.addProperty("usuario", buscar.getUsuario());
        jsonResponse.addProperty("correo", buscar.getCorreo());
        jsonResponse.addProperty("password", buscar.getPassword());

        // Configurar la respuesta como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Enviar la respuesta JSON al cliente
        response.getWriter().write(jsonResponse.toString());
    }

    protected void loginAdmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        Admin login = new Admin();
        login.setUsuario(usuario);
        login.setPassword(password);

        AdminDaoJDBC userDao = new AdminDaoJDBC();
        HttpSession session = request.getSession();

        if (userDao.login(login)) {
            // Obtén el ID del administrador usando el objeto Admin
            int adminId = userDao.idAdminObtener(login);
            session.setAttribute("nombreAdmin", usuario);
            session.setAttribute("adminId", adminId);
            response.sendRedirect("ServletClientes");
        } else {
            session.setAttribute("mensajeErroLogin", "Error Usuario/Contraseña incorrecto");
            response.sendRedirect("adminLogin.jsp");
        }

    }

    protected void cerrarSession(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("adminLogin.jsp");

    }
    
    //LO QUE HARA ES CERRAR LA SESSION DEL ADMINISTRADO EN EL ADMIN PANEL Y LLEVARA EL LA TIENDA DE LA PAGINA
    protected void tienda(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("tienda.jsp");

    }

}
