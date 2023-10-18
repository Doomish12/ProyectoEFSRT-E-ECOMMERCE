package Controller;

import com.google.gson.JsonObject;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import datos.CarritoDaoJDBC;
import datos.ClientesDaoJDBC;
import datos.DetalleVentaDaoJDBC;
import datos.MensajeDaoJDBC;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelos.Carrito;
import modelos.Clientes;
import modelos.DetalleVenta;
import modelos.Mensajes;

@WebServlet("/ServletClientes")
public class ServletClientes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accion = request.getParameter("accion");

        try {
            if (accion != null) {
                switch (accion) {
                    case "listar":
                        this.listadoClientes(request, response);
                        break;
                    case "eliminar":
                        this.eliminarCliente(request, response);
                        break;
                    case "encontrar":
                        this.encontrarCliente(request, response);
                        break;
                    case "cerrar":
                        this.cerrarSesion(request, response);
                        break;
                    case "pdfHistorial":
                        this.generarPDF(request, response);
                        break;
                    default:
                        this.listadoClientes(request, response);
                        break;
                }

            } else {
                this.listadoClientes(request, response);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (DocumentException ex) {
            Logger.getLogger(ServletClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accion = request.getParameter("accion");
        try {

            if (accion != null) {
                switch (accion) {
                    case "insertar":
                        this.insertarCliente(request, response);
                        break;
                    case "editar":
                        this.actualizarCliente(request, response);
                        break;
                    case "editarPerfil":
                        this.actualizarPerfil(request, response);
                        break;
                    case "login":
                        this.loginUsuario(request, response);
                        break;
                    case "encontrarPerfil":
                        this.encontrarDatos(request, response);
                        break;
                    default:
                        this.listadoClientes(request, response);
                        break;
                }

            } else {
                this.listadoClientes(request, response);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    protected void listadoClientes(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        List<Clientes> listar = new ClientesDaoJDBC().listar();
        List<Mensajes> mensajes = new MensajeDaoJDBC().listar();
        List<DetalleVenta> ventas = new DetalleVentaDaoJDBC().listarTotalVentas();
        HttpSession session = request.getSession();
        session.setAttribute("lista", listar);
        session.setAttribute("contador", contadorClientes(listar));
        session.setAttribute("contadorMensaje", contadorMensaje(mensajes));
        session.setAttribute("historialVentas", calcularVentasTotales(ventas));
        response.sendRedirect("clientes.jsp");

    }

    protected void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        String nombres = request.getParameter("nombres");
        int edad = Integer.parseInt(request.getParameter("edad"));
        String direccion = request.getParameter("direccion");
        String usuario = request.getParameter("usuario");
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        Clientes cliente = new Clientes();
        cliente.setUsuario(usuario);

        boolean usuarioExiste = new ClientesDaoJDBC().usuario(cliente);
        if (usuarioExiste) {

            response.getWriter().write("Usuario ya registrado");

        } else {
            Clientes insertar = new Clientes(nombres, edad, direccion, usuario, correo, password);
            new ClientesDaoJDBC().insertar(insertar);
            response.getWriter().write("success");
        }

    }

    protected void encontrarCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        int id = Integer.parseInt(request.getParameter("id"));
        Clientes buscar = new Clientes(id);
        new ClientesDaoJDBC().buscar(buscar);

        // Crear un objeto JSON con los datos del libro
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("id", buscar.getId());
        jsonResponse.addProperty("nombres", buscar.getNombres());
        jsonResponse.addProperty("edad", buscar.getEdad());
        jsonResponse.addProperty("direccion", buscar.getDireccion());
        jsonResponse.addProperty("usuario", buscar.getUsuario());
        jsonResponse.addProperty("password", buscar.getPassword());
        jsonResponse.addProperty("correo", buscar.getCorreo());
        // Configurar la respuesta como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Enviar la respuesta JSON al cliente
        response.getWriter().write(jsonResponse.toString());
    }

    protected void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {

        int id = Integer.parseInt(request.getParameter("id"));
        Clientes eliminar = new Clientes(id);
        new ClientesDaoJDBC().eliminar(eliminar);
        this.listadoClientes(request, response);

    }

    protected void actualizarCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        String nombres = request.getParameter("nombres");
        int edad = Integer.parseInt(request.getParameter("edad"));
        String direccion = request.getParameter("direccion");
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String correo = request.getParameter("correo");
        int id = Integer.parseInt(request.getParameter("id"));

        Clientes modificar = new Clientes(id, nombres, edad, direccion, usuario, correo, password);
        new ClientesDaoJDBC().actualizar(modificar);

        this.listadoClientes(request, response);

    }

    protected void actualizarPerfil(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        String nombres = request.getParameter("nombres");
        int edad = Integer.parseInt(request.getParameter("edad"));
        String direccion = request.getParameter("direccion");
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String correo = request.getParameter("correo");
        int id = Integer.parseInt(request.getParameter("id"));

        Clientes modificar = new Clientes(id, nombres, edad, direccion, usuario, correo, password);
        new ClientesDaoJDBC().actualizar(modificar);

        response.sendRedirect("tienda.jsp");

    }

    protected void loginUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        String usuario = request.getParameter("user");
        String password = request.getParameter("contrasenia");

        Clientes login = new Clientes();
        login.setUsuario(usuario);
        login.setPassword(password);

        ClientesDaoJDBC userDao = new ClientesDaoJDBC();
        HttpSession session = request.getSession();

        if (userDao.login(login)) {

            //ACORDE AL NOMBRE DEL USUARIO SE MUESTRA LA CANTIDAD DE PRODUCTOS AGREGAODS EN EL CONTADOR DE CARRITO
            Carrito userL = new Carrito();
            userL.setUsuario(usuario);
            List<Carrito> listar = new CarritoDaoJDBC().listar(userL);
            int contadorCarrito = contadorProducto(listar);
            session.setAttribute("contadorCarrito", contadorCarrito);
            //AQUI ACABO EL METODO CONTADOR PARA EL LOGIN

            session.setAttribute("totalCarrito", totalCarrito(listar));
            session.setAttribute("nombreUsuario", usuario);
            response.sendRedirect("tienda.jsp");
        } else {
            session.setAttribute("mensajeErroLogin", "Error Usuario/Contraseña incorrecto");
            response.sendRedirect("login.jsp");
        }

    }

    protected void encontrarDatos(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        String usuario = request.getParameter("usuario");
        Clientes buscar = new Clientes();
        buscar.setUsuario(usuario);
        new ClientesDaoJDBC().buscarUser(buscar);

        //Enviar respuesta
        request.setAttribute("datos", buscar);
        String jspEditar = "perfil.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);

    }

    protected void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("login.jsp");
    }

    public int contadorClientes(List<Clientes> clientes) {
        int contador = 0;
        int cant = 0;
        for (Clientes cliente : clientes) {
            cant += cliente.getId();
            contador++;
        }
        return contador;
    }

    public int contadorMensaje(List<Mensajes> mensajes) {
        int contador = 0;
        int cantidad = 0;

        for (Mensajes mensaje : mensajes) {
            cantidad += mensaje.getId();
            contador++;
        }
        return contador;
    }

    public double calcularVentasTotales(List<DetalleVenta> detalleVentas) {
        double totalVentas = 0;

        for (DetalleVenta total : detalleVentas) {
            totalVentas += total.getPrecioTotal();

        }
        return totalVentas;
    }

    //METODO PARA CALCACULR TOTAL DEL CARRITO
    public double totalCarrito(List<Carrito> carrito) {

        double precio = 0;

        for (Carrito usuario : carrito) {
            precio += usuario.getPrecioTotal();
        }
        return precio;
    }

    //METODO PARA CACLULAR LOS PRODUCTO AÑADIDOS AL CARRTIO;
    public int contadorProducto(List<Carrito> carrito) {
        String cantidad;
        int contador = 0;

        for (Carrito lista : carrito) {
            cantidad = lista.getUsuario();
            contador++;
        }
        return contador;
    }

    protected void generarPDF(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException, SQLException {
      
        // Obtener la lista del historial de Ventas
        List<DetalleVenta> detallesVenta = new ClientesDaoJDBC().listarPdf();

        // Configurar el Content-Disposition y Content-Type para el PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=historialVenta.pdf");

        // Crear documento PDF
        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Agregar encabezado con logo y descripción del lugar de la tienda
        // Título
        try {

            Font principalT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
            Paragraph principal = new Paragraph("HISTORIAL DE VENTAS", principalT);
            principal.setAlignment(Element.ALIGN_CENTER);
            document.add(principal);
            document.add(Chunk.NEWLINE);

            PdfPTable headerTable = new PdfPTable(2);
            headerTable.setWidthPercentage(100);

// Columna 1: Celda para la imagen del logo
            PdfPCell imageCell = new PdfPCell();
            imageCell.setBorder(Rectangle.NO_BORDER); // Sin bordes para la celda de la imagen
            Image logo = Image.getInstance(request.getServletContext().getRealPath("Imagenes/EmpresaLogo.png"));
            logo.scaleToFit(100, 100); // Ajustar tamaño del logo
            imageCell.addElement(logo);
            headerTable.addCell(imageCell);

// Columna 2: Celda para el nombre y dirección de la tienda
            PdfPCell infoCell = new PdfPCell();
            infoCell.setBorder(Rectangle.BOX); // Borde sólido
            infoCell.setBorderColor(BaseColor.BLACK); // Color del borde (en este caso, negro)
            infoCell.setBorderWidth(1f); // Ancho del borde
            infoCell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Alinear verticalmente al centro

            Paragraph empresaInfo = new Paragraph();
            empresaInfo.setAlignment(Element.ALIGN_CENTER); // Alinear el texto del párrafo al centro
            empresaInfo.add(new Phrase("InnovatechX", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
            empresaInfo.add(Chunk.NEWLINE); // Nueva línea
            empresaInfo.add(new Phrase("Av. Petit Thouars 5273, Miraflores.", FontFactory.getFont(FontFactory.HELVETICA, 12)));
            empresaInfo.add(Chunk.NEWLINE); // Nueva línea
            empresaInfo.add(new Phrase("Telefono: 940-023-042", FontFactory.getFont(FontFactory.HELVETICA, 12)));
            infoCell.addElement(empresaInfo);
            headerTable.addCell(infoCell);

// Agregar la tabla de encabezado al documento
            document.add(headerTable);

            document.add(Chunk.NEWLINE);

            /*para el espacio en blanco entre el titulo y detalle compra*/
            Font blancos = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
            Paragraph blanco = new Paragraph("", blancos);
            blanco.setAlignment(Element.ALIGN_CENTER);
            document.add(blanco);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            /*para el espacio en blanco entre el titulo y detalle compra*/

            //TABLA APARTIR DE AQUI
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
            Paragraph title = new Paragraph("Detalles de la Venta", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Crear tabla para mostrar los detalles de venta
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Encabezados de la tabla
            String[] headers = {"#", "Cliente", "Producto", "Cantidad", "Precio Total", "Fecha de Venta"};
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE)));
                cell.setBackgroundColor(BaseColor.DARK_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            // Agregar detalles de la venta a la tabla
            int contador = 0;
            for (DetalleVenta detalle : detallesVenta) {
                contador++;
                table.addCell(String.valueOf(contador));
                table.addCell(detalle.getCliente());
                table.addCell(detalle.getNombreProducto());
                table.addCell(String.valueOf(detalle.getCantidad()));
                table.addCell("S/" + String.valueOf(detalle.getPrecioTotal())); // Formatear precio
                table.addCell(detalle.getFechaVenta().toString()); // Formatear fecha según necesidades
            }

// Calcular y agregar el total de pago al PDF
            double totalPago = calcularVentasTotales(detallesVenta);
            Font totalFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.ORANGE); // Cambié el color del texto a amarillo
            PdfPCell totalCell = new PdfPCell(new Phrase("TOTAL VENTAS: S/" + String.format("%.2f", totalPago), totalFont)); // Formateé el total con dos decimales
            totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalCell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Alineación vertical al centro
            totalCell.setBackgroundColor(BaseColor.DARK_GRAY);
            totalCell.setBorderColor(BaseColor.BLACK); // Color del borde de la celda
            totalCell.setBorderWidth(1.5f); // Ancho del borde de la celda (aumenté el grosor del borde)
            totalCell.setPadding(10f); // Espaciado interno de la celda para que el texto no esté pegado a los bordes
            totalCell.setColspan(6);
            table.addCell(totalCell);

            // Agregar tabla al documento
            document.add(table);

            // Cerrar el documento
            document.close();
        } catch (DocumentException | IOException e) {
            // Manejar cualquier excepción que pueda ocurrir al cargar el logo
            e.printStackTrace(System.out);
        }
    }

}
