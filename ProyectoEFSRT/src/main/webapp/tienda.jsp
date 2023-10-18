<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>InnovatechX</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css2?family=Epilogue&family=VT323&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Anton&family=Bebas+Neue&family=Merriweather:ital,wght@1,900&family=Quicksand:wght@700&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css">

        <link rel="stylesheet" href="Estilos/tiendaMain.css" />
        <link rel="stylesheet" href="Estilos/footerTienda.css" />
        <link rel="stylesheet" href="Estilos/headerTienda.css" />

    </head>
    <body>
        <jsp:include page="WEB-INF/Vista/headerTienda.jsp"/>

        <main class="contenido-tienda">
            
            <div id="carouselExampleInterval" class="carousel slide" data-bs-ride="carousel" data-bs-interval="9000">
                <div class="carousel-inner">
                    <div class="carousel-item active" data-bs-interval="9000">
                        <video id="video1" class="d-block w-100" muted autoplay>
                            <source src="Imagenes/tienda/virtuoso-pro-hero.webm" type="video/mp4">
                            Tu navegador no soporta el elemento de video.
                        </video>
                    </div>
                    <div class="carousel-item" data-bs-interval="8000">
                        <video id="video2" class="d-block w-100" muted autoplay>
                            <source src="Imagenes/tienda/hp-hero-dominator-titanium.webm" type="video/mp4">
                            Tu navegador no soporta el elemento de video.
                        </video>
                    </div>
                    <div class="carousel-item" data-bs-interval="6000">
                        <video id="video3" class="d-block w-100" muted autoplay>
                            <source src="Imagenes/tienda/hp_icue-link-hx.webm" type="video/mp4">
                            Tu navegador no soporta el elemento de video.
                        </video>
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>

            <script>
                document.getElementById('carouselExampleInterval').addEventListener('slid.bs.carousel', function (event) {
                    var videos = document.querySelectorAll('video');
                    videos.forEach(function (video) {
                        video.pause();
                    });

                    var videoId = 'video' + (event.to + 1);
                    document.getElementById(videoId).play();
                });
            </script>



            <div class="marcas">
                <article> 
                    <img src="Imagenes/tienda/zara.png" />
                </article>
                <article> 
                    <img src="Imagenes/tienda/samsung.png"/>
                </article>
                <article> 
                    <img src="Imagenes/tienda/oppo.png"/>
                </article>
                <article> 
                    <img src="Imagenes/tienda/asus.png"/>
                </article>
                <article> 
                    <img src="Imagenes/tienda/hurley.png"/>
                </article>
                <article> 
                    <img src="Imagenes/tienda/dng.png"/>
                </article>
            </div>

            <div class="productos">
                <section class="texto-prod">
                    <div class="logo linea"><a href="/" class="texto-a"><span class="circle"></span> .Productos Nuevos</a></div>
                </section>
                <section class="articulos-tienda">
                    <article class="articulo"> 
                        <img src="Imagenes/tienda/Monitor.png" />
                        <p>MONITOR ASUS 22" VP228HE FHD 1MS 60HZ HDMI VGA</p>
                        <p>S/150.50</p>
                        <p class="color-precio">Precio al Contado</p>
                        <button class="estilos-boton">Añadir al carrito</button>
                    </article>
                    <article class="articulo"> 
                        <img src="Imagenes/tienda/Silla.png"/>
                        <p>SILLA SATE GAMER NEGRA RGB A-GC8710 RGB</p>
                        <p>S/150.50</p>
                        <p class="color-precio">Precio al Contado</p>
                        <button class="estilos-boton">Añadir al carrito</button>
                    </article>
                    <article class="articulo"> 
                        <img src="Imagenes/tienda/Targeta.png"/>
                        <p>PLACA DE VIDEO GEFORCE GIGABYTE RTX 3050 Gaming OC 8GB</p>
                        <p>S/150.50</p>
                        <p class="color-precio">Precio al Contado</p>
                       <button class="estilos-boton">Añadir al carrito</button>
                    </article>
                    <article class="articulo"> 
                        <img src="Imagenes/tienda/fuente.png"/>
                        <p>FUENTE AEROCOOL CYLON 1000W RGB PLATINUM</p>
                        <p>S/150.50</p>
                        <p class="color-precio">Precio al Contado</p>
                        <button class="estilos-boton">Añadir al carrito</button>
                    </article>
                    <article class="articulo"> 
                        <img src="Imagenes/tienda/laptop.png"/>
                        <p>NOTEBOOK PCBOX FIRE! 5 I5 1035G1 8GB 256SSD 14" FHD W10 HOME</p>
                        <p>S/150.50</p>
                        <p class="color-precio">Precio al Contado</p>
                       <button class="estilos-boton">Añadir al carrito</button>
                    </article>
                    <article class="articulo"> 
                        <img src="Imagenes/tienda/pc gamers.png"/>
                        <p>PC Diseño Ryzen 5 4500 B450M PRO-VDH 16GB SSD 1TB RX 6500 XT 4GB </p>
                        <p>S/150.50</p>
                        <p class="color-precio">Precio al Contado</p>
                        <button class="estilos-boton">Añadir al carrito</button>
                    </article>
                    <article class="articulo"> 
                        <img src="Imagenes/tienda/placa.png"/>
                        <p>MOTHERBOARD GIGABYTE X570 AORUS ELITE</p>
                        <p>S/150.50</p>
                        <p class="color-precio">Precio al Contado</p>
                       <button class="estilos-boton">Añadir al carrito</button>
                    </article>
                    <article class="articulo"> 
                        <img src="Imagenes/tienda/placa2.png"/>
                        <p>COMBO AMD Ryzen 9 7900X C/water ROG STRIX + MB X670E + 32GB DDR5 </p>
                        <p>S/150.50</p>
                        <p class="color-precio">Precio al Contado</p>
                       <button class="estilos-boton">Añadir al carrito</button>
                    </article>
                </section>
            </div>

            <div class="img-final">
                <img src="Imagenes/tienda/img1C.webp" width="1350px" alt="img">
            </div>
        </main>


        <jsp:include page="WEB-INF/Vista/footerTienda.jsp"/>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </body>
</html>
