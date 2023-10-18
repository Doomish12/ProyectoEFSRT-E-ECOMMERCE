<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@taglib  prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="es_PE"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrito | InnovatechX</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css2?family=Epilogue&family=VT323&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Anton&family=Bebas+Neue&family=Merriweather:ital,wght@1,900&family=Quicksand:wght@700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="Estilos/carritoCompras.css" />

        <!-- SweetAlert2 JS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.3/dist/sweetalert2.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.3/dist/sweetalert2.min.js"></script>
        
<style>
.total-pago{
display: flex;
justify-content: end;

gap: 20px;
}
.input-total{
background-color: rgb(206, 206, 206);
font-weight: bold;
text-align: center;
font-size: 1.1rem;
}
</style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg">
            <div class="container">
                <a class="navbar-brand" href="#">InnovatechX</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="tienda.jsp">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="tiendaPro.jsp">Productos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Ofertas</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="contacto.jsp">Contacto</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

    <div class="container mt-5">
        <div class="card">
            <div class="card-header d-flex align-items-center">
               <h1>Detalle de Orden</h1>  
          
                    <form action="${pageContext.request.contextPath}/ServletDetalleVenta?accion=historial" method="POST">
                        <input type="hidden" name="userHistorial" value="${nombreUsuario}"/>
                        <input type="hidden" name="userHistorial" value="${nombreUsuario}"/>
                        <input type="hidden" name="userHistorial" value="${nombreUsuario}"/>
                        <button type="submit" class="btn btn-primary ms-5">Regresar</button>
                    </form>
             
            </div>
            <div class="card-body">
                <table class="table">
                    <thead class="table-secondary">
                        <tr> 
                            <th scope="col">#</th>
                            <th scope="col">Cliente</th>
                            <th scope="col">Imagen</th>
                            <th scope="col">Producto</th>
                            <th scope="col">Cantidad</th>
                            <th scope="col">Precio Total</th>
                            <th scope="col">Fecha de Compra</th>
                        </tr>
                    </thead>
                        <tbody>
                            <c:forEach var="c" items="${detalle}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${c.cliente}</td>
                                    <td><img src="ServletDetalleVenta?accion=imagen&idProducto=${c.idProducto}" class="imgtd" alt="Imagen del Producto" name="imagen" width="100px" height="100px"/></td>
                                    <td>${c.nombreProducto}</td>
                                    <td>${c.cantidad}</td>
                                    <td><f:formatNumber value="${c.precioTotal}" type="currency"/></td>
                                    <td>${c.fechaVenta}</td>

                                </tr>
                            </c:forEach>
                        </tbody>
                </table>

                <div class="card-footer">
                    <div class="total-pago">
                        <section>
                         
                            <a href="${pageContext.request.contextPath}/ServletDetalleVenta?accion=pdf&usuarioPdf=${nombreUsuario}" class="btn btn-warning" target="_blank">Generar PDF</a>
                                  
                        </section>

                        <section>
                            <p>Monto Total de la Compra</p>
                        </section>
                        <section>
                            <!-- Input mejorado con Bootstrap y CSS personalizado -->
                            <input type="text" value="<f:formatNumber value="${TotalOrden}" type="currency"/>" readonly class="form-control input-total">
                        </section>
                    </div>

                </div>
            </div>
        </div>
                        
                        
                        
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


    </body>
</html>
