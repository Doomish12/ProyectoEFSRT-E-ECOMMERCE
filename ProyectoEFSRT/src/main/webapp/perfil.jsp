<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Perfil | InnovatechX</title>
        <link href="https://fonts.googleapis.com/css2?family=Epilogue&family=VT323&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Anton&family=Bebas+Neue&family=Merriweather:ital,wght@1,900&family=Quicksand:wght@700&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css">
        <link rel='stylesheet' type="text/css" href='Estilos/footerTienda.css'>
        <link rel='stylesheet' type="text/css" href='Estilos/headerTienda.css'>
        <link rel='stylesheet' type="text/css" href='Estilos/perfilUsuario.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.3/dist/sweetalert2.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.3/dist/sweetalert2.min.css">
        <style>
            #ocultarContra{
                display: none;
            }
        </style>
    </head>
    <body>

        <jsp:include page="WEB-INF/Vista/headerTienda.jsp"/>      
        <main class="main">

            <div class="perfil-con">
                <h1>Editar Perfil</h1>
                <form  action="${pageContext.request.contextPath}/ServletClientes?accion=editarPerfil"
                       method="POST">
                    <div class="form-group">
                        <label for="nombre">Nombres:</label>
                        <div class="input-group">
                            <input type="hidden" class="form-control"   value="${datos.id}" name="id">
                            <input type="text" class="form-control"   value="${datos.nombres}" name="nombres">
                            <i class="fas fa-user input-group-text"></i>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edad">Edad del usuario:</label>
                        <div class="input-group">
                            <input type="number" class="form-control"  value="${datos.edad}" name="edad">
                            <i class="fas fa-calendar input-group-text"></i>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="direccion">Dirección del usuario:</label>
                        <div class="input-group">
                            <input type="text" class="form-control"   value="${datos.direccion}" name="direccion">
                            <i class="fas fa-home input-group-text"></i>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="correo">Correo del usuario:</label>
                        <div class="input-group">
                            <input type="email" class="form-control"   value="${datos.correo}" name="correo">
                            <i class="fas fa-envelope input-group-text"></i>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="usuario">Nombre de usuario:</label>
                        <div class="input-group">
                            <input type="text" class="form-control"  value="${datos.usuario}" name="usuario" readonly>
                            <i class="fas fa-id-badge input-group-text"></i>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password">Contraseña:</label>
                        <div class="input-group">
                            <input type="password" class="form-control" id="password" value="${datos.password}" name="password">
                               <i class="fas ri-eye-line input-group-text" id="verContra"></i>
                               <i class="fas ri-eye-off-line input-group-text" id="ocultarContra"></i>
  
                         
                        </div>
                    </div>

                    <div class="center">

                        <button type="button" class="btn btn-primary center" onclick="editarPerfil()">Guardar Cambios</button>
                        <button type="submit" id="editarSubmit" style="display: none;"></button>
                        <br/>
                        <br/>
                        <a type="submit" class="btn btn-warning center" href="tienda.jsp">Regresar</a>

                    </div>
                </form>
            </div>
        </main>                   
        <jsp:include page="WEB-INF/Vista/footerTienda.jsp"/>                
        <script src="https://kit.fontawesome.com/e0232df615.js" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <!-- EDITAR CLIENTE CODIGO JAVASCRIPT Y AJAX --> 

<script>
function editarPerfil() {
    // Si los campos no están vacíos, muestra la alerta de éxito
    Swal.fire({
        title: 'Datos Actualizado!',
        text: 'Hiciste clic en el botón!',
        icon: 'success',
        confirmButtonText: 'OK'
    }).then((result) => {
        // Si el usuario hace clic en "OK", activa el evento de clic del botón de enviar formulario
        if (result.isConfirmed) {
            // Encuentra el botón de enviar formulario por su ID y haz clic en él
            var submitButton = document.getElementById('editarSubmit');
            submitButton.click();
        }
    });
}
</script>
<script>
      //OCULAT CONTRASEÑA Y VER
var verContra = document.querySelector('#verContra');
var ocultarContra = document.querySelector('#ocultarContra');
var contra = document.querySelector('#password');

verContra.addEventListener('click', () => {
    contra.type = "text";
    ocultarContra.style.display = "block";
    verContra.style.display = "none";
});
ocultarContra.addEventListener('click', () => {
    contra.type = "password";
    ocultarContra.style.display = "none";
    verContra.style.display = "block";
});


</script>


    </body>
</html>
