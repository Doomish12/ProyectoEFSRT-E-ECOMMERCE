<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="es_PE"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>InnovatechX | Productos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <!-- FUENTES LETRAS -->
        <link href="https://fonts.googleapis.com/css2?family=Epilogue&family=VT323&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Anton&family=Bebas+Neue&family=Merriweather:ital,wght@1,900&family=Quicksand:wght@700&display=swap" rel="stylesheet">
        <!-- FUENTES LETRAS -->   
        <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css">
        <link rel="stylesheet" href="Estilos/footerTienda.css" />
        <link rel="stylesheet" href="Estilos/headerTienda.css" />
        <link rel="stylesheet" href="Estilos/tiendaProductos.css" />
    </head>
    <body>
        <jsp:include page="WEB-INF/Vista/headerTienda.jsp"/>

        <main class="cotenedor-productos">
            <div class="buscador">
                <input type="text" id="buscador-input" placeholder="Buscar Producto..." />
            </div>
            <body>

                <div class="container-items">
                    <c:forEach var="c" items="${tiendaPro}" varStatus="status">
                        <div class="productos-item">
                            <figure>
                                <img class="img" src="imagen?idProducto=${c.id}" class="imgtd" alt="Imagen del Producto" name="imagen"/>
                            </figure>
                            <div class="info-product">
                                <h2>${c.nombre}</h2>
                                <p>${c.descripcion}</p>
                                <p class="price" ><f:formatNumber value="${c.precio}" type="currency"/></p>

                                <form action="${pageContext.request.contextPath}/ServletCarrito?accion=insertarCarrito" id="productoForm"
                                      method="POST" >
                                    <input type="hidden" name="idProducto" value="${c.id}"/>
                                    <input type="hidden" name="usuario" value="${nombreUsuario}"/>
                                    <input type="hidden" name="cantidad" value="1"/>
                                    <input type="hidden" name="idProducto" value="${c.id}"/>
                                    <!-- PERMITE QUE LOS USUARIOS QUE NO TIENEN CUENTA LOS REDIRIGA EL LOGIN -->
                                    <%
                                        String usuario = (String) session.getAttribute("nombreUsuario");
                                        if (usuario == null) {
                                    %>
                                    <style>
                                        .anadir-car{
                                            display: none;
                                        }
                                    </style>
                                    <button type="button"  style="width: 100%;" onclick="enviarLogin(event)">Añadir al carrito</button>
                                    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
                                    <script>
                                      function enviarLogin(event) {
                                            event.preventDefault(); // Previene el comportamiento predeterminado del enlace

                                            Swal.fire({
                                            title: 'Para Añadir un Producto debes Logearte',
                                            text: "Por favor inicia sesión",
                                            icon: 'warning',
                                            showCancelButton: true,
                                            confirmButtonColor: '#3085d6',
                                            cancelButtonColor: '#d33',
                                            confirmButtonText: 'Sí, Logearme'
                                            }).then((result) => {
                                            if (result.isConfirmed) {
                                               //Mandamos al usuario a logearse
                                                window.location.href = "login.jsp";
                                            }
                                            });
                                            }
                                    </script>
                                    <%
                                        }
                                    %>
                                    <button type="submit" class="anadir-car" style="width: 100%;">Añadir al carrito</button>
                                </form>            
                            </div>
                        </div>
                    </c:forEach>
                </div>





        </main> 


<jsp:include page="WEB-INF/Vista/footerTienda.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>



<script>
document.getElementById("buscador-input").addEventListener("input", function () {
    var buscador = document.getElementById("buscador-input").value.toLowerCase();
    var productos = document.querySelectorAll(".info-product h2");


    productos.forEach(function (product, index) {
        var productoNombre = product.textContent.toLowerCase();
        var titulo = product.closest(".productos-item");

        if (productoNombre.includes(buscador)) {
            titulo.style.display = "block"; // Mostrar productos que coincidan
        } else {
            titulo.style.display = "none"; // Ocultar productos que no coincidan
        }
    });
});

</script>


</body>
</html>
