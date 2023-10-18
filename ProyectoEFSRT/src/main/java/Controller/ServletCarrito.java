package Controller;

import datos.CarritoDaoJDBC;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelos.Carrito;

@WebServlet("/ServletCarrito")
public class ServletCarrito extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        try {
            if (accion != null) {
                switch (accion) {
                    case "listarCarrito":
                        this.listarCarrito(request, response);
                        break;
                    case "eliminarCarrito":
                        this.eliminar(request, response);
                        break;
                    case "imagen":
                        this.imagenBuscar(request, response);
                        break;
                    case "borrarCarrito":
                        this.borrarCarrito(request, response);
                        break;
                    default:
                        this.listarCarrito(request, response);
                        break;
                }
            } else {
                this.listarCarrito(request, response);
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
                    case "insertarCarrito":
                        this.insertarCarrito(request, response);
                        break;
                    case "cantidad":
                        this.atualizarCantidad(request, response);
                        break;
                    case "disminuir":
                        this.disminuirCantidad(request, response);
                        break;
                    default:
                        response.sendRedirect("tiendaPro.jsp");
                        break;
                }
            } else {
                response.sendRedirect("tiendaPro.jsp");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    protected void insertarCarrito(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {

        String usuario = request.getParameter("usuario");
        String idProductoParam = request.getParameter("idProducto");
        String cantidadParam = request.getParameter("cantidad");

        int idProducto = Integer.parseInt(idProductoParam);
        int cantidad = Integer.parseInt(cantidadParam);

        //SE INSERTAR LOS DATOS
        Carrito insertar = new Carrito(usuario, idProducto, cantidad);
        new CarritoDaoJDBC().insertar(insertar);

        // Obtiene el contador actual de la sesión
        HttpSession session = request.getSession();
        int contadorCarrito = (int) session.getAttribute("contadorCarrito");
        // Incrementa el contador en 1
        contadorCarrito++;

        // Actualiza el contador en la sesión
        session.setAttribute("contadorCarrito", contadorCarrito);

        //Atualiza el total a pagar cada vez que se insertar el producto acorde al nombre usuario
        // Recupera la lista actualizada de productos en el carrito
        Carrito userL = new Carrito();
        userL.setUsuario(usuario);
        List<Carrito> listar = new CarritoDaoJDBC().listar(userL);

        // Calcula el nuevo total del carrito después de la eliminación
        double nuevoTotalCarrito = totalCarrito(listar);

        // Actualiza el total del carrito en la sesión
        session.setAttribute("totalCarrito", nuevoTotalCarrito);

        // Actualiza el contador en la sesión y el totalPagar
        session.setAttribute("contadorCarrito", contadorCarrito);

        response.sendRedirect("tiendaPro.jsp");
    }

    protected void listarCarrito(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        String usuario = request.getParameter("usuario");
        Carrito user = new Carrito();
        user.setUsuario(usuario);

        List<Carrito> listar = new CarritoDaoJDBC().listar(user);
        HttpSession session = request.getSession();
        session.setAttribute("carrito", listar);
        response.sendRedirect("carrito.jsp");

    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int idCarrito = Integer.parseInt(request.getParameter("idCarrito"));
        Carrito eliminar = new Carrito(idCarrito);

        new CarritoDaoJDBC().eliminar(eliminar);
        // La eliminación fue exitosa     

        HttpSession session = request.getSession();
        int contadorCarrito = (int) session.getAttribute("contadorCarrito");

        // Reduce el contador en 1
        contadorCarrito--;

        // Actualiza el contador en la sesión
        session.setAttribute("contadorCarrito", contadorCarrito);

        //Atualiza el total a pagar cada vez que se elimina le producot acorde al nombre usuairo
        String usuario = (String) session.getAttribute("nombreUsuario");
        System.out.println("Nombre de usuario recuperado: " + usuario);
        // Recupera la lista actualizada de productos en el carrito
        Carrito userL = new Carrito();
        userL.setUsuario(usuario);
        List<Carrito> listar = new CarritoDaoJDBC().listar(userL);

        // Calcula el nuevo total del carrito después de la eliminación
        double nuevoTotalCarrito = totalCarrito(listar);

        // Actualiza el total del carrito en la sesión
        session.setAttribute("totalCarrito", nuevoTotalCarrito);
        session.setAttribute("carrito", listar);
        response.sendRedirect("carrito.jsp");

    }

    protected void imagenBuscar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Obtener el identificador del producto de la solicitud (por ejemplo, desde un parámetro)
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));

            Carrito carrito = new Carrito();
            carrito.setIdProducto(idProducto);
            byte[] imagen = new CarritoDaoJDBC().buscarImagen(carrito);

            if (imagen != null) {

                response.setContentType("image/jpeg");
                response.getOutputStream().write(imagen);
            } else {

                response.sendError(HttpServletResponse.SC_NOT_FOUND); // Enviar un error 404
            }
        } catch (SQLException ex) {
            // Manejar la excepción si ocurre
            ex.printStackTrace(System.out);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Enviar un error 500
        }
    }

    //ACTUALIZAR CANTIDAD PRODUCTO
    protected void atualizarCantidad(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        double precioTotal = Double.parseDouble(request.getParameter("precioTotal"));
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        String usuario = request.getParameter("usuario");

        Carrito actualizar = new Carrito(cantidad, precioTotal, usuario, idProducto);
        new CarritoDaoJDBC().actualizar(actualizar);

        // Recupera la lista actualizada de productos en el carrito
        Carrito userL = new Carrito();
        userL.setUsuario(usuario);
        List<Carrito> listar = new CarritoDaoJDBC().listar(userL);

        // Calcula el nuevo total del carrito después de aumentar la cantidad
        double nuevoTotalCarrito = totalCarrito(listar);

        HttpSession session = request.getSession();
        session.setAttribute("totalCarrito", nuevoTotalCarrito);
        session.setAttribute("carrito", listar);
        response.sendRedirect("carrito.jsp");
    }

    //DISMINUIR CANTIDAD PRODUCTO
    protected void disminuirCantidad(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

        int idProducto = Integer.parseInt(request.getParameter("disminuirPro"));
        String usuario = request.getParameter("user");

        Carrito disminuir = new Carrito();
        disminuir.setIdProducto(idProducto);
        disminuir.setUsuario(usuario);
        new CarritoDaoJDBC().disminuir(disminuir);

        // Recupera la lista actualizada de productos en el carrito
        Carrito userL = new Carrito();
        userL.setUsuario(usuario);
        List<Carrito> listar = new CarritoDaoJDBC().listar(userL);

        // Calcula el nuevo total del carrito después de aumentar la cantidad
        double nuevoTotalCarrito = totalCarrito(listar);

        HttpSession session = request.getSession();
        session.setAttribute("totalCarrito", nuevoTotalCarrito);
        session.setAttribute("carrito", listar);
        response.sendRedirect("carrito.jsp");
    }

    protected void borrarCarrito(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

        String usuario = request.getParameter("usuario");

        Carrito borrarCarrito = new Carrito();
        borrarCarrito.setUsuario(usuario);
        new CarritoDaoJDBC().eliminarCarrito(borrarCarrito);

           // Recupera la lista actualizada de productos en el carrito
        Carrito userL = new Carrito();
        userL.setUsuario(usuario);
        List<Carrito> listar = new CarritoDaoJDBC().listar(userL);
        double nuevoTotalCarrito = totalCarrito(listar);

        
        HttpSession session = request.getSession();
        //Regresame el contador 0  porque ya no hay productos
        int contadorCarrito = 0;
        session.setAttribute("totalCarrito", nuevoTotalCarrito);
        session.setAttribute("contadorCarrito", contadorCarrito);
        response.sendRedirect("tienda.jsp");

    }

    public double totalCarrito(List<Carrito> carrito) {

        double precio = 0;

        for (Carrito usuario : carrito) {
            precio += usuario.getPrecioTotal();
        }
        return precio;
    }

}
