<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Admin Panel | InnovatechX</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <!--font awesome-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.3/dist/sweetalert2.min.css">
        <!--css file-->
        <link rel="stylesheet" href="Estilos/clientes.css" />
        <!-- SweetAlert2 JS -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.3/dist/sweetalert2.min.js"></script>
    </head>
    <body>
                             <!-- PERMITE QUE LOS USUARIOS QUE NO TIENEN CUENTA LOS REDIRIGA EL LOGIN -->
        <%
            String nombreAdmin = (String) session.getAttribute("nombreAdmin");
         if (nombreAdmin == null) {
        %>
        <style>
            .pagina-entera{
                display: none;
            }
        </style>

        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script>
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Solo los Administradores tienen acceso a esta página'
            }).then(function () {
                window.location.href = "adminLogin.jsp";
            });
        </script>
        <%
            }
        %>
        
           
        
        
        
        <section class="barra-lateral pagina-entera">
            <a href="#" class="logo">
                <i class="fab fa-slack"></i>
                <span class="text">Admin Panel</span>
            </a>

            <ul class="menu-lateral top">
                <li>
                    <a href="${pageContext.request.contextPath}/ServletClientes?accion=listar" class="enlace">
                        <i class="fas fa-border-all"></i>
                        <span class="text">Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/ServletAdmin?accion=tienda" class="enlace">
                        <i class="fas fa-shopping-cart"></i>
                        <span class="text">Mi tienda</span>
                    </a>
                </li>
                <li class="activo">
                    <a href="#" class="enlace">
                        <i class="fa-brands fa-product-hunt"></i>
                        <span class="text">Productos</span>
                    </a>
                </li>

                <li>
                    <a href="${pageContext.request.contextPath}/ServletMensaje?accion=listar" class="enlace">
                        <i class="fas fa-message"></i>
                        <span class="text">Mensajes</span>
                    </a>
                </li>
                <li >
                    <a href="${pageContext.request.contextPath}/ServletAdmin?accion=listar" class="enlace">
                        <i class="fas fa-people-group"></i>
                        <span class="text">Perfiles de Administrador</span>
                    </a>
                </li>
            </ul>

            <ul class="menu-lateral">
                <li>
                    <a href="${pageContext.request.contextPath}/ServletAdmin?accion=cerrar" class="logout">
                        <i class="fas fa-right-from-bracket"></i>
                        <span class="text">Cerrar Sesión</span>
                    </a>
                </li>
            </ul>
        </section>

        <section class="contenido pagina-entera">
            <nav>
                <i class="fas fa-bars menu-btn"></i>
                <a href="#" class="enlace">Categorias</a>
                <form action="#">

                </form>
                <div class="icon-container">
                    <a href="${pageContext.request.contextPath}/ServletMensaje?accion=listar" class="enlace">
                        <i class="fas fa-message"></i>
                        <div class="badge">${contadorMensaje}</div>
                    </a>
                </div>
                <input type="checkbox" hidden id="switch-mode" />
                <label for="switch-mode" class="switch-mode"></label>
               <p class="adminBienvenida">Bienvenido Admin: ${nombreAdmin}</p>


                <a href="#" class="profile">
                <img src="ServletImgEncontrar?id=${sessionScope.adminId}" alt="Foto de perfil">
                </a>
            </nav>



            <jsp:include page="WEB-INF/Productos/listadoProducto.jsp"/>

            </<section>

<!-- URL CONTEXT PAGE  -->
<script>
    var contexto = "${pageContext.request.contextPath}";
</script>
<!-- URL CONTEXT PAGE  -->




<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="JavaScript/productos.js"></script>
<script src="JavaScript/clientes.js"></script>
<script src="https://kit.fontawesome.com/e0232df615.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>
</html>
