package Controller;

import datos.MensajeDaoJDBC;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelos.Mensajes;

@WebServlet("/ServletMensaje")
public class ServletMensaje extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String accion = request.getParameter("accion");
        
        try {
            if(accion != null){
            
                switch (accion) {
                    case "listar":
                        this.listarMensaje(request, response);
                        break;
                    default:
                       this.listarMensaje(request, response);
                       break;
                }
            }else{
            this.listarMensaje(request, response);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String accion = request.getParameter("accion");
        
        try {
            if(accion != null){
            
                switch (accion) {
                    case "eliminar":
                        this.eliminarMensaje(request, response);
                        break;
                    case "insertar":
                        this.insertarMensaje(request, response);
                        break;
                    default:
                       this.listarMensaje(request, response);
                       break;
                }
            }else{
            this.listarMensaje(request, response);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    
    
    protected void listarMensaje(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        List<Mensajes> listar = new MensajeDaoJDBC().listar();
       //PERMITE QUE COMIENZE EN REVERSA
        Collections.reverse(listar);
        HttpSession session = request.getSession();
        session.setAttribute("listaMensaje", listar);
        response.sendRedirect("mensajes.jsp");
    }
    
    protected void insertarMensaje(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String mensaje = request.getParameter("mensaje");
        
        Mensajes insertar = new Mensajes(nombre, correo, mensaje);
        new MensajeDaoJDBC().insertar(insertar);
        
        response.sendRedirect("contacto.jsp");
    }
    
    
    protected void eliminarMensaje(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
    
         int id = Integer.parseInt(request.getParameter("id"));
         Mensajes eliminar = new Mensajes(id);
         new MensajeDaoJDBC().eliminar(eliminar);
         
         this.listarMensaje(request, response);
    }
    

}
