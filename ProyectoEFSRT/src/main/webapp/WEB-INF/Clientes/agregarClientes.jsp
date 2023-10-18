<div class="modal fade" id="agregarClienteModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-dark">
                <h5 class="modal-title">Agregar Cliente</h5> 
                <button class="btn-close" data-bs-dismiss="modal"></button>               
            </div>

            <form action="${pageContext.request.contextPath}/ServletClientes?accion=insertar"
                  method="POST" class="was-validated" >

                <div class="modal-body">
                    <div class="form-group">
                        <label for="nombre">Nombre</label>
                        <input type="text" class="form-control" name="nombres" required/>
                    </div>
                    <div class="form-group">
                        <label for="edad">Edad</label>
                        <input type="number" class="form-control" name="edad" required/>
                    </div>
                    <div class="form-group">
                        <label for="direccion">Direccion</label>
                        <input type="text" class="form-control" name="direccion" required/>
                    </div>
                    <div class="form-group">
                    <label for="correo">Correo</label>
                    <input type="email" class="form-control" name="correo" required/>
                    </div>
                    <div class="form-group">
                        <label for="usuario">Usuario</label>
                        <input type="text" class="form-control" name="usuario" required/>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" name="password" required/>
                    </div>
                </div> 
                <div class="modal-footer">
                    <!-- Botón para mostrar la alerta -->
                    <button type="button" class="btn btn-primary" onclick="guardarCliente()">Guardar</button>
                    <!-- Botón de enviar formulario (inicialmente oculto) -->
                    <button type="submit" id="submitButton" style="display: none;"></button>

                </div>
            </form>
        </div>
    </div>
</div>
<script>
    /// AGREGAR CLIENTE CODIGO JAVASCRIPT Y AJAX
    function guardarCliente() {
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
                            title: 'Cliente Registrado!',
                            text: 'El cliente ha sido registrado correctamente.',
                            icon: 'success',
                            confirmButtonText: 'OK'
                        }).then(function () {
                            var contexto = "${pageContext.request.contextPath}";
                            window.location.href = contexto + "/ServletClientes?accion=listar";
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