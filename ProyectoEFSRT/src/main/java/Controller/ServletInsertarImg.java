package Controller;

import datos.AdminDaoJDBC;
import datos.ProductoDaoJDBC;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import modelos.Admin;
import modelos.Productos;

@MultipartConfig
public class ServletInsertarImg extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            if (accion != null) {
                switch (accion) {
                    case "agregar":
                        this.insertarProducto(request, response);
                        break;
                    case "modificar":
                        this.modificarProducto(request, response);
                        break;
                    case "agregarAdmin":
                        this.insertarAdminImg(request, response);
                        break;
                    case "modificarAdmin":
                        this.modificarAdminImg(request, response);
                        break;
                    default:
                        response.sendRedirect("error.jsp");
                        break;
                }
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

    }

    protected void insertarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {

        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        Part archivoPart = request.getPart("imagen");
        byte[] imagen = obtenerBytesDeImagenDesdeFormulario(archivoPart);

        Productos insertar = new Productos(nombre, descripcion, precio, cantidad, imagen);
        new ProductoDaoJDBC().insertar(insertar);

        response.getWriter().write("success"); // Envía una respuesta 
    }

    protected void modificarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {

        int id = Integer.parseInt(request.getParameter("id")); // Obtén el ID del producto a actualizar
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        Part archivoPart = request.getPart("imagen");
        byte[] imagen = obtenerBytesDeImagenDesdeFormulario(archivoPart);

        Productos productoActualizado = new Productos(id, nombre, descripcion, precio, cantidad, imagen);
        new ProductoDaoJDBC().atualizar(productoActualizado);

        response.getWriter().write("success"); // Envía una respuesta 
    }

    protected void insertarAdminImg(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String usuario = request.getParameter("usuario");
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        Part archivoPart = request.getPart("foto");
        byte[] foto = obtenerBytesDeImagenDesdeFormulario(archivoPart);

        Admin existe = new Admin();
        existe.setUsuario(usuario);

        boolean usuarioExiste = new AdminDaoJDBC().admin(existe);

        if (usuarioExiste) {
            response.getWriter().write("Usuario ya registrado");
        } else {
            Admin insertar = new Admin(usuario, correo, password, foto);
            new AdminDaoJDBC().insertar(insertar);

            response.getWriter().write("success");
        }

    }

    protected void modificarAdminImg(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String usuario = request.getParameter("usuario");
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        Part archivoPart = request.getPart("foto");
        byte[] foto = obtenerBytesDeImagenDesdeFormulario(archivoPart);

        Admin modificar = new Admin(id, usuario, correo, password, foto);
        new AdminDaoJDBC().actualizar(modificar);

        response.getWriter().write("success");

    }

    private byte[] obtenerBytesDeImagenDesdeFormulario(Part archivoPart) throws IOException {
        byte[] bytesImagen;
        try (InputStream inputStream = archivoPart.getInputStream()) {
            int tamaño = (int) archivoPart.getSize();
            bytesImagen = new byte[tamaño];
            inputStream.read(bytesImagen, 0, tamaño);
        }
        return bytesImagen;
    }
}
