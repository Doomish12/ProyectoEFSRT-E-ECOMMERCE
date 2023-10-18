<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login | InnovatechX</title>
           <script src="https://kit.fontawesome.com/9e8faa626d.js" crossorigin="anonymous"></script>
           <link rel="stylesheet" href="Estilos/login.css" />
                   <!-- SweetAlert2 JS -->
         <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.3/dist/sweetalert2.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.3/dist/sweetalert2.min.js"></script>
    </head>
    <body>
         <div class="contenedor">
      <div class="forms-contenedor">
        <div class="iniciar-sesion">
          <form action="${pageContext.request.contextPath}/ServletClientes?accion=login" class="iniciar-sesion-form"
                method="POST">
            <h2 class="title">Iniciar Sesión</h2>
            <div class="input-field">
              <i class="fas fa-user"></i>
              <input type="text" placeholder="Usuario" name="user" />
            </div>
            <div class="input-field">
              <i class="fas fa-lock"></i>
              <input type="password" placeholder="Contraseña" name="contrasenia"/>
            </div>
            <input type="submit" value="Entrar" class="btn solid" />
            <p class="error">${mensajeErroLogin}</p>
      
          </form>


          <form action="${pageContext.request.contextPath}/ServletClientes?accion=insertar" class="sesion-form"
                method="POST">
            <h2 class="title">Crear Cuenta</h2>
            <div class="input-field">
              <i class="fas fa-user"></i>
              <input type="text" placeholder="Nombres" name="nombres" />
            </div>
            <div class="input-field">
              <i class="fas fa-id-badge"></i>
              <input type="text" placeholder="Usuario" name="usuario"/>
            </div>
            <div class="input-field">
              <i class="fas fa-envelope"></i>
              <input type="email" placeholder="Correo" name="correo" />
            </div>
            <div class="input-field">
              <i class="fas fa-lock"></i>
              <input type="password" placeholder="Contraseña" name="password"/>
            </div>
            <div class="input-field">
              <i class="fa-solid fa-house"></i>
              <input type="text" placeholder="Dirección" name="direccion"/>
            </div>
            <div class="input-field">
              <i class="fa-solid fa-image-portrait"></i>
              <input type="number" placeholder="Edad" name="edad"/>
            </div>
                 <!-- Botón para mostrar la alerta -->
            <button type="button" class="btn" onclick="registrarUsuario()">Registrarse</button>
            <!-- Botón de enviar formulario (inicialmente oculto) -->
            <button type="submit" id="submitButton" style="display: none;"></button>
          </form>
                
        </div>
      </div>

      <div class="panels-contenedor">
        <div class="panel left-panel">
          <div class="content">
            <h3>Nuevo Aquí ?</h3>
            <p>
              Presiona en el boton Registrarse para que puedas crear una cuenta
            </p>
            <button class="btn transparent" id="sign-up-btn">
              Registrarse
            </button>
          </div>
            <img src="Imagenes/login/log.svg" class="image" alt="" />
        </div>
        <div class="panel right-panel">
          <div class="content">
            <h3>Tienes Cuenta?</h3>
            <p>
              Presiona en el boton Iniciar Sesión para que puedas logearte
            </p>
            <button class="btn transparent" id="sign-in-btn">
              Iniciar Sesión
            </button>
          </div>
            <img src="Imagenes/login/register.svg" class="image" alt="" />
        </div>
      </div>
    </div>
        <script src="JavaScript/login.js"></script>
        
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    /// AGREGAR CLIENTE CODIGO JAVASCRIPT Y AJAX
    function registrarUsuario() {
        var nombre = document.getElementsByName('nombres')[0].value;
        var edad = document.getElementsByName('edad')[0].value;
        var direccion = document.getElementsByName('direccion')[0].value;
        var correo = document.getElementsByName('correo')[0].value;
        var usuario = document.getElementsByName('usuario')[0].value;
        var password = document.getElementsByName('password')[0].value;

        if (nombre.trim() === '' || edad.trim() === '' || direccion.trim() === '' || correo.trim() === '' || usuario.trim() === '' || password.trim() === '') {
            Swal.fire({
                title: 'Error',
                text: 'Por favor, completa todos los campos del formulario.',
                icon: 'error',
                confirmButtonText: 'OK'
            });
        } else {
            // Objeto con los datos del formulario
            var datos = {
                nombres: nombre,
                edad: edad,
                direccion: direccion,
                correo: correo,
                usuario: usuario,
                password: password
            };

            // Enviar datos al servidor usando AJAX
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/ServletClientes?accion=insertar',
                data: datos,
                success: function (response) {
                    // Manejar la respuesta del servidor
                    if (response === 'success') {
                        Swal.fire({
                            title: 'Usuario Registrado!',
                            text: 'Se ha registrado correctamente.',
                            icon: 'success',
                            confirmButtonText: 'OK'
                        }).then(function () {
                            window.location.href = "login.jsp";
                        });
                    } else {
                        Swal.fire({
                            title: 'Error',
                            text: response, // Mostrar el mensaje de error recibido del servidor
                            icon: 'error',
                            confirmButtonText: 'OK'
                        });
                    }
                },
                error: function (xhr, status, error) {
                    console.error('Error en la solicitud AJAX: ' + status + ' - ' + error);
                }
            });
        }
    }
</script>

        
    </body>
</html>


