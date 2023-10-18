package Controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import datos.CarritoDaoJDBC;
import datos.DetalleVentaDaoJDBC;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelos.DetalleVenta;
import java.io.*;
import java.util.List;
import java.util.logging.*;
import modelos.Carrito;

@WebServlet("/ServletDetalleVenta")
public class ServletDetalleVenta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accion = request.getParameter("accion");

        try {
            if (accion != null) {
                switch (accion) {
                    case "imagen":
                        this.imagenBuscar(request, response);
                        break;
                    case "pdf":
                        this.generarPDF(request, response);
                        break;
                    default:
                        response.sendRedirect("error.jsp");
                        break;
                }
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (DocumentException | SQLException ex) {
            Logger.getLogger(ServletDetalleVenta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "venta":
                        this.insertarVenta(request, response);
                        break;
                    case "historial":
                        this.insertarHistorialV(request, response);
                        break;
                    default:
                        response.sendRedirect("error.jsp");
                }
            } else {
                response.sendRedirect("error.jsp");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    protected void insertarVenta(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        String usuario = request.getParameter("userVenta");
        int cantidad = Integer.parseInt(request.getParameter("cantVenta"));
        int idProducto = Integer.parseInt(request.getParameter("idProVenta"));

        DetalleVenta insertar = new DetalleVenta(usuario, idProducto, cantidad);
        new DetalleVentaDaoJDBC().insertarVenta(insertar);
        request.setAttribute("usuario", usuario);
        this.listarVenta(request, response);
    }

    protected void insertarHistorialV(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        String usuario = request.getParameter("userHistorial");

        DetalleVenta insertar = new DetalleVenta(usuario);
        new DetalleVentaDaoJDBC().insertarHistorialV(insertar);

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

    protected void listarVenta(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        String usuario = (String) request.getAttribute("usuario");
        DetalleVenta user = new DetalleVenta(usuario);

        List<DetalleVenta> listar = new DetalleVentaDaoJDBC().listarVenta(user);
        HttpSession session = request.getSession();
        session.setAttribute("detalle", listar);
        session.setAttribute("TotalOrden", calcularTotalPago(listar));
        response.sendRedirect("detalleVenta.jsp");

    }

    public double calcularTotalPago(List<DetalleVenta> detalleVentas) {
        double precio = 0;

        for (DetalleVenta venta : detalleVentas) {
            precio += venta.getPrecioTotal();
        }
        return precio;
    }

    protected void imagenBuscar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Obtener el identificador del producto de la solicitud (por ejemplo, desde un parámetro)
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));

            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setIdProducto(idProducto);
            byte[] imagen = new DetalleVentaDaoJDBC().buscarImagen(detalleVenta);

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

    protected void generarPDF(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException, SQLException {
        String usuarioPdf = request.getParameter("usuarioPdf");
        // Obtener la lista de detalles de venta del usuario
        DetalleVentaDaoJDBC detalleVentaDAO = new DetalleVentaDaoJDBC();
        DetalleVenta detalleVenta = new DetalleVenta(usuarioPdf);
        List<DetalleVenta> detallesVenta = detalleVentaDAO.listarPdf(detalleVenta);

        // Configurar el Content-Disposition y Content-Type para el PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=detalleVenta.pdf");

        // Crear documento PDF
        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Agregar encabezado con logo y descripción del lugar de la tienda
        // Título
        try {

            Font principalT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
            Paragraph principal = new Paragraph("BOLETA DE PAGO", principalT);
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
            Paragraph title = new Paragraph("Detalles de la Compra", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Crear tabla para mostrar los detalles de venta
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Encabezados de la tabla
            String[] headers = {"#", "Cliente", "Producto", "Cantidad", "Precio Total", "Fecha de Compra"};
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
            double totalPago = calcularTotalPago(detallesVenta);
            Font totalFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.ORANGE); // Cambié el color del texto a amarillo
            PdfPCell totalCell = new PdfPCell(new Phrase("TOTAL PAGADO: S/" + String.format("%.2f", totalPago), totalFont)); // Formateé el total con dos decimales
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
