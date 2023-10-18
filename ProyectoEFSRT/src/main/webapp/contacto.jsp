<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset='utf-8'>
        <meta http-equiv='X-UA-Compatible' content='IE=edge'>
        <title>InnovatechX</title>
        <meta name='viewport' content='width=device-width, initial-scale=1'>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <!--*? links letras fuentes -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Epilogue&family=VT323&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Anton&family=Bebas+Neue&family=Merriweather:ital,wght@1,900&family=Quicksand:wght@700&display=swap" rel="stylesheet">

        <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.3/dist/sweetalert2.min.css">
        <link rel="stylesheet" type="text/css" media="screen"  href="Estilos/footerTienda.css">
        <link rel='stylesheet' type='text/css' media='screen' href='Estilos/contacto.css'>
        <link rel="stylesheet" type="text/css" media="screen"  href="Estilos/headerTienda.css">

        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    </head>
    <body>


        <jsp:include page="WEB-INF/Vista/headerTienda.jsp"/>

        <main class="opacity main-contacto n2">
            <div class="cont-habilidades">


                <div class="contenedor-contacto n10">
                    <section class="formulario-section">
                        <h1 class="acerca-l"><span class="acerca-l2 color-icon">|</span>Escribenos</h1>
                        <p>Por favor, comparte los detalles de tu situación completando el  <br>
                            formulario a continuación. Estaré encantado(a) de <br>revisarlo y ponerme en  
                            en contacto contigo lo antes posible.<br>
                            para resolver cualquier inquietud que puedas tener.

                        </p>
                        <form action="${pageContext.request.contextPath}/ServletMensaje?accion=insertar" method="POST">
                            <label class="color-label" for="nombre">Escribe Tu Nombre</label>
                            <input type="text" name="nombre"  required>

                            <label class="color-label" for="correo">Escribe Tu Correo</label>
                            <input type="email" name="correo">

                            <label class="color-label" for="mensaje">Escribe Tu Mensaje</label>
                            <textarea name="mensaje" ></textarea>
                            <!-- Botón para mostrar la alerta -->
                            <button type="button" class="boton-co" onclick="mostrarAlerta()">Enviar</button>
                            <!-- Botón de enviar formulario (inicialmente oculto) -->
                            <button type="submit" id="submitButton" style="display: none;"></button>

                        </form>
                    </section>
                    <section class="grid-section">

                        <article><img src="Imagenes/Contacto/info-1.svg" class="img-cont" width="400px" height="400px" alt="info"></article>





                    </section>   

                </div>
            </div>
        </main>

                        <jsp:include page="WEB-INF/Vista/footerTienda.jsp"/>
    </body>
</html>
<!-- SweetAlert2 JS -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.3/dist/sweetalert2.min.js"></script>
<script>
function mostrarAlerta() {
    // Obtén los valores de los campos del formulario
    var nombre = document.getElementsByName('nombre')[0].value;
    var correo = document.getElementsByName('correo')[0].value;
    var mensaje = document.getElementsByName('mensaje')[0].value;

    // Verifica si los campos están vacíos
    if (nombre.trim() === '' || correo.trim() === '' || mensaje.trim() === '') {
        // Muestra una alerta indicando que los campos están vacíos
        Swal.fire({
            title: 'Error',
            text: 'Por favor, completa todos los campos del formulario.',
            icon: 'error',
            confirmButtonText: 'OK'
        });
    } else {
        // Si los campos no están vacíos, muestra la alerta de éxito
        Swal.fire({
            title: 'Mensaje Enviado!',
            text: 'Hiciste clic en el botón!',
            icon: 'success',
            confirmButtonText: 'OK'
        }).then((result) => {
            // Si el usuario hace clic en "OK", activa el evento de clic del botón de enviar formulario
            if (result.isConfirmed) {
                // Encuentra el botón de enviar formulario por su ID y haz clic en él
                var submitButton = document.getElementById('submitButton');
                submitButton.click();
            }
        });
    }
}

</script>