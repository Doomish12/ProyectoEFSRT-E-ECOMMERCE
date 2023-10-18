<%@taglib  prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="es_PE"/>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>


    </head>
    <body>
        <div id="page" class="site page-home">
            <header>
                <div class="header-arriba mobile-hide">
                    <div class="contenedor">
                        <div class="elemento items">
                            <div class="left">
                                <ul class="items main-links">
                                    <li><a href="#">Blog</a></li>
                                    <li><a href="#">Productos Destacados</a></li>
                                    <li><a href="#">Lista de Deseos</a></li>
                                </ul>
                            </div>
                            <div class="right">
                                <ul class="items main-links">
                                    <li>
                                        <!-- PERMITE QUE LOS USUARIOS QUE NO TIENEN CUENTA LES SALGA EL BOTON INICIAR SESSION -->
                                        <%
                                            String nombreUsuario = (String) session.getAttribute("nombreUsuario");
                                            if (nombreUsuario == null) {
                                        %>
                                        <style>
                                            .mostrar-menu{
                                                display: none;
                                            }
                                        </style>
                                        <a href="login.jsp">Iniciar Sesión</a>
                                        <%
                                            }
                                        %>
                                        <div class="mostrar-menu">
                                            <a href="#" class="btn dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">Mi Cuenta</a>
                                            <ul class="dropdown-menu info-usuario">
                                                <li> <img src="Imagenes/tienda/usuario.png" width="60px" height="60px"/></li>
                                                <li><p class="nombre-user">${nombreUsuario}</p></li>
                                                <li>
                                                    <!-- Para encontrar los datos del usuario -->
                                                    <form action="${pageContext.request.contextPath}/ServletClientes?accion=encontrarPerfil" method="POST">
                                                        <input type="hidden" name="usuario" value="${nombreUsuario}">
                                                        <button type="submit" class="dropdown-item">Mi Perfil</button>
                                                    </form>
                                                      <!-- Para encontrar los datos del usuario -->    
                                                </li>
                                                <li><a  href="${pageContext.request.contextPath}/ServletClientes?accion=cerrar"  class="dropdown-item " style="color:red;" >Cerrar Sesión</a></li>
                                            </ul>
                                        </div>
                                    </li>



                                    <li><a href="#">Rastreo de orden</a></li>
                                    <li><a href="#">USD <span class="icon-small"><i class="ri-arrow-down-s-line"></i></span></a>
                                        <ul>
                                            <li class="current"><a href="#">USD</a></li>
                                            <li><a href="#">EURO</a></li>
                                            <li><a href="#">GBP</a></li>
                                            <li><a href="#">IDR</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="#">Spanish<span class="icon-small"><i class="ri-arrow-down-s-line"></i></span></a>
                                        <ul>
                                            <li class="current"><a href="#">English</a></li>
                                            <li><a href="#">German</a></li>
                                            <li><a href="#">English</a></li>
                                            <li><a href="#">Polish</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- header-arriba -->
                <div class="header-nav">
                    <div class="contenedor">
                        <div class="elemento items">
                            <a href="#" class="trigger desktop-hide"><span class="i ri-menu-2-line"></span></a>
                            <div class="left items">
                                <div class="logo"><a href="/"><span class="circle"></span>.InnovaTechX</a></div>
                                <nav class="mobile-hide">
                                    <ul class="items second-links">
                                        <li><a href="tienda.jsp">Home</a></li>
                                        <li><a href="${pageContext.request.contextPath}/ServletProducto?accion=listarPro">Productos</a></li>
                                        <li class="has-child">
                                            <a href="#">Tecnologia
                                                <div class="icon-small"><i class="ri-arrow-down-s-line"></i></div>
                                            </a>
                                            <div class="mega">
                                                <div class="contenedor">
                                                    <div class="elemento">
                                                        <div class="flexcol">
                                                            <div class="row">
                                                                <h4>Componentes de PC</h4>
                                                                <ul>
                                                                    <li><a href="#">Memoria RAM DDR4</a></li>
                                                                    <li><a href="#">Procesadores de última generación</a></li>
                                                                    <li><a href="#">Placas Madre para Gaming</a></li>
                                                                    <li><a href="#">Tarjetas Gráficas Potentes</a></li>
                                                                    <li><a href="#">Discos SSD de alta velocidad</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <div class="flexcol">
                                                            <div class="row">
                                                                <h4>Tecnología Móvil</h4>
                                                                <ul>
                                                                    <li><a href="#">Smartphones Android y iOS</a></li>
                                                                    <li><a href="#">Fundas y Accesorios para Celulares</a></li>
                                                                    <li><a href="#">Tabletas con Pantalla Retina</a></li>
                                                                    <li><a href="#">Auriculares Bluetooth</a></li>
                                                                    <li><a href="#">Smartwatches y Dispositivos Wearables</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <div class="flexcol">
                                                            <div class="row">
                                                                <h4>Periféricos de PC</h4>
                                                                <ul>
                                                                    <li><a href="#">Teclados Mecánicos RGB</a></li>
                                                                    <li><a href="#">Ratones Gaming Personalizables</a></li>
                                                                    <li><a href="#">Monitores UltraHD con HDR</a></li>
                                                                    <li><a href="#">Impresoras 3D de Alta Precisión</a></li>
                                                                    <li><a href="#">Cámaras Web de Alta Definición</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <div class="flexcol">
                                                            <div class="row">
                                                                <h4>Redes y Conectividad</h4>
                                                                <ul>
                                                                    <li><a href="#">Routers Wi-Fi 6 de Alta Velocidad</a></li>
                                                                    <li><a href="#">Adaptadores de Red Gigabit</a></li>
                                                                    <li><a href="#">Cables Ethernet Blindados</a></li>
                                                                    <li><a href="#">Dispositivos de Almacenamiento en Red</a></li>
                                                                    <li><a href="#">Sistemas Mesh para Cobertura Total</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <div class="flexcol products">
                                                            <div class="row">
                                                                <div class="media">
                                                                    <div class="thumbnail object-cover">
                                                                        <a href="#">
                                                                            <img src="Imagenes/tienda/SillaGamer.avif" alt="Descripción de la imagen">
                                                                        </a>
                                                                    </div>
                                                                </div>
                                                                <div class="text-content">
                                                                    <h4 style="color: white; background-color: black;">¡Lo más buscado!</h4>
                                                                    <a href="#" class="primary-button">Comprar</a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>


                                        </li>
                                        <li><a href="contacto.jsp">Contacto</a></li>
                                        <li>
                                            <a href="#">Nuevos Productos
                                                <div class="fly-item"><span>New! </span></div>
                                            </a>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                            <div class="right">
                                <ul class="items second-links">
                                    <li class="mobile-hide"><a href="#">
                                            <div class="icon-large"><i class="ri-heart-line"></i></div>
                                            <div class="fly-item"><span class="item-number">0</span></div>
                                        </a></li>
                                    <li><a href="${pageContext.request.contextPath}/ServletCarrito?accion=listarCarrito&usuario=${nombreUsuario}" class="iscart">

                                            <div class="icon-large">
                                                <i class="ri-shopping-cart-line"></i>
                                                <div class="fly-item"><span class="item-number">${contadorCarrito}</span></div>
                                            </div>

                                            <div class="icon-text">
                                                <div class="mini-text">Total</div>
                                                <div class="cart-total"><f:formatNumber value="${totalCarrito}" type="currency"/></div>
                                            </div>
                                        </a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>


            </header>
    </body>
</html>
