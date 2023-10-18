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
        <link rel="stylesheet" href="Estilos/pago.css" />
        <link rel="stylesheet" href="Estilos/carritoCompras.css" />

        <!-- SweetAlert2 JS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.3/dist/sweetalert2.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.3/dist/sweetalert2.min.js"></script>
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
            <h1>Carrito</h1>
            <div class="row">
                <div class="col-lg-8">
                    <table class="table">
                        <thead class="table-dark">
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Producto</th>
                                <th scope="col">Imagen</th>
                                <th scope="col">Precio</th>
                                <th scope="col">Cantidad</th>
                                <th scope="col">Total</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="c" items="${carrito}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${c.nombreProducto}</td>
                                    <td><img src="ServletCarrito?accion=imagen&idProducto=${c.idProducto}" class="imgtd" alt="Imagen del Producto" name="imagen" width="100px" height="100px"/></td>
                                    <td><f:formatNumber value="${c.precio}" type="currency"/></td>
                                    <td>
                                        <div class="botones-cantidad">

                                            <form action="${pageContext.request.contextPath}/ServletCarrito?accion=disminuir" method="POST">
                                                <input type="hidden" name="user" value="${nombreUsuario}" />
                                                <input type="hidden" name="disminuirPro" value="${c.idProducto}"/>
                                                <input type="hidden" name="disminuirPro" value="${c.idProducto}"/>

                                                <button type="submit" class="btn btn-secondary btn-sm">-</button>
                                            </form>

                                            <input class="form-control input-cantidad " type="text" value="${c.cantidad}" readonly  min="1">

                                            <form action="${pageContext.request.contextPath}/ServletCarrito?accion=cantidad" method="POST">

                                                <input type="hidden" class="cantidad-producto" name="cantidad" value="${c.cantidad}"/>
                                                <input type="hidden" name="usuario" value="${nombreUsuario}" />
                                                <input type="hidden" class="cantidad-producto" name="precioTotal" value="${c.cantidad}"/>
                                                <input type="hidden" name="idProducto" value="${c.idProducto}"/>
                                                <input type="hidden" name="idProducto" value="${c.idProducto}"/>

                                                <button type="submit" class="btn btn-secondary btn-sm" onclick="incrementarCantidad()">+</button>
                                            </form>

                                        </div>
                                    </td>
                                    <td><f:formatNumber value="${c.precioTotal}" type="currency"/></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletCarrito?accion=eliminarCarrito&idCarrito=${c.idCarrito}" class="btn btn-danger btn-sm" onclick="carritoProducto(event)">Eliminar</a>

                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="col-lg-3">
                    <div class="card">
                        <div class="card-header">
                            <h5 class="card-title text-center">Resumen del Carrito</h5>
                        </div>	
                        <div class="card-body">
                            <div class="mb-3">
                                <label class="form-label">Subtotal:</label>
                                <input type="text" class="form-control" value="S/0.0" readonly>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Descuento:</label>
                                <input type="text" class="form-control" value="S/0.0" readonly>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Total a pagar:</label>
                                <input type="text" id="totalCarrito" class="form-control" value="<f:formatNumber value="${totalCarrito}" type="currency"/>" readonly>
                            </div>

                        </div>

                        <div class="card-footer">
                            <c:forEach var="d" items="${carrito}" varStatus="status">
                                <!-- ... (otros campos del carrito) ... -->

                                <form action="${pageContext.request.contextPath}/ServletDetalleVenta?accion=venta" method="POST">
                                    <input type="hidden" name="userVenta" value="${nombreUsuario}"/>
                                    <input type="hidden" name="userVenta" value="${nombreUsuario}"/>
                                    <input type="hidden" name="userVenta" value="${nombreUsuario}"/>
                                    <input type="hidden" name="cantVenta" value="${d.cantidad}"/> <!-- Recupera la cantidad del producto -->
                                    <input type="hidden" name="idProVenta" value="${d.idProducto}"/> <!-- Recupera el id del producto -->
                                    <button type="submit" style="display: none;" id="venta"/>
                                </form>
                            </c:forEach>
                            <button type="button" class="btn btn-warning btn-bloc" data-bs-toggle="modal" style="width: 100%;" data-bs-target="#myModal">
                                           Realizar Pago
                            </button>
                            <br><br>
                            <a href="${pageContext.request.contextPath}/ServletCarrito?accion=borrarCarrito&usuario=${nombreUsuario}" onclick="carritoProducto(event)" class="btn btn-danger btn-bloc" style="width: 100%;">Cancelar Compra</a>
                        </div>
             
                </div>
            </div>
                    <jsp:include page="WEB-INF/Carrito/pago.jsp"/>                             
                        
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@2.10.2/dist/umd/popper.min.js"></script>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


          

<script>
//ELIMINAR PRODUCTOS CODIGO JAVASCRIPT Y AJAX:
    function carritoProducto(event) {
        event.preventDefault(); // Previene el comportamiento predeterminado del enlace

   Swal.fire({
        title: '¿Estás seguro?',
        text: "Esta acción no se puede deshacer",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sí, eliminarlo'
    }).then((result) => {
        if (result.isConfirmed) {
            // Mostrar un mensaje de éxito
            Swal.fire(
                'Eliminado!',
                'El producto ha sido eliminado exitosamente.',
                'success'
            ).then(() => {
                window.location.href = event.target.href;
            });
        }
    });
    
}
    
    
    function incrementarCantidad() {
        // Obtén todos los campos de cantidad de los productos
        var cantidadInputs = document.querySelectorAll('.cantidad-producto');

        // Recorre cada campo de cantidad y aumenta la cantidad
        cantidadInputs.forEach(function (cantidadInput) {
            cantidadInput.value = parseInt(cantidadInput.value) + 1;
        });
    }

</script>


    </body>
</html>
