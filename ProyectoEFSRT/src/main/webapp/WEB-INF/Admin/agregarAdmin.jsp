<div class="modal fade" id="agregarAdminModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-dark">
                <h5 class="modal-title">Agregar Administrador</h5> 
                <button class="btn-close" data-bs-dismiss="modal"></button>               
            </div>

            <form action="${pageContext.request.contextPath}/insertar?accion=agregarAdmin"
                  method="POST" class="was-validated" id="agregarAdmin">

                <div class="modal-body">
                    <div class="form-group">
                        <label for="usuario">Usuario</label>
                        <input type="text" class="form-control" name="usuario" required/>
                    </div>
                    <div class="form-group">
                        <label for="correo">Correo</label>
                        <input type="email" class="form-control" name="correo" required/>
                    </div>
                    <div class="form-group">
                        <label for="password">Contraseña</label>
                        <input type="password" class="form-control" name="password" required/>
                    </div>
                    <div class="form-group">
                        <label for="foto">Foto</label>
                        <input type="file" class="form-control" name="foto" required/>
                    </div>   
                </div> 
                <div class="modal-footer">
                    <!-- Botón para mostrar la alerta -->
                    <button type="button" class="btn btn-primary" onclick="guardarAdmin()">Guardar</button>
                    <!-- Botón de enviar formulario (inicialmente oculto) -->
                    <button type="submit" id="submitButton" style="display: none;"></button>

                </div>
            </form>
        </div>
    </div>
</div>


<script>
function guardarAdmin() {
    var formulario = document.getElementById('agregarAdmin');
    var formData = new FormData(formulario);

    // Validar campos
    if (validarAgregar(formData)) {
        fetch('insertar?accion=agregarAdmin', {
            method: 'POST',
            body: formData
        })
        .then(function(response) {
            return response.text();
        })
        .then(function(data) {
            if (data === 'success') {
                // Producto insertado correctamente
                Swal.fire({
                    title: 'Administrador Registrado!',
                    text: 'El Administrador ha sido registrado correctamente.',
                    icon: 'success',
                    confirmButtonText: 'OK'
                }).then(() => {
                  var contexto = "${pageContext.request.contextPath}";
                  window.location.href = contexto + "/ServletAdmin?accion=listar";
                });
            } else {
             validarAgregar(formData);
            }
        })
        .catch(function(error) {
            console.error('Error:', error);
        });
    }
}

function validarAgregar(formData) {
    // Validar campos aquí
    var usuario = formData.get('usuario');
    var correo = formData.get('correo');
    var password = formData.get('password');
    var foto = formData.get('foto');

    if (!usuario || !correo || !password  || !foto) {
        Swal.fire({
            title: 'Error',
            text: 'Por favor, completa todos los campos del formulario.',
            icon: 'error',
            confirmButtonText: 'OK'
        });
        return false;
    }
    return true;
}
</script>
