<div class="modal fade" id="editarAdminModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-success text-white">
                <h5 class="modal-title">Editar Admin</h5> 
                <button class="btn-close" data-bs-dismiss="modal"></button>               
            </div>

            <form action="${pageContext.request.contextPath}/insertar?accion=modificarAdmin"
                  method="POST" class="was-validated" id="editarAdminForm">

                <div class="modal-body">
                    <div class="form-group">
                        <label for="usuario">Usuario</label>
                        <input type="hidden" class="form-control" name="id" id="editAdminId" required />
                        <input type="text" class="form-control" name="usuario" id="editAdminUsuario" required/>
                    </div>
                    <div class="form-group">
                        <label for="correo">Correo</label>
                        <input type="email" class="form-control" name="correo" id="editAdminCorreo" required/>
                    </div>
                    <div class="form-group">
                        <label for="password">Contraseña</label>
                        <input type="text" class="form-control" name="password" id="editAdminPassword" required/>
                    </div>
                    <div class="form-group">
                        <label for="foto">Foto</label>
                        <input type="file" class="form-control" name="foto" required/>
                    </div>  
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    <!-- Botón para mostrar la alerta -->
                    <button type="button" class="btn btn-primary" onclick="editar()">Guardar</button>
                    <!-- Botón de enviar formulario (inicialmente oculto) -->
                    <button type="submit" id="editarAdmin" style="display: none;"></button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
//EDITAR PRODUCTOS CODIGO JAVASCRIPT Y AJAX:
function editar() {
    var formulario = document.getElementById('editarAdminForm');
    var formData = new FormData(formulario);

    // Validar campos
    if (validarCampoEditar(formData)) {
        fetch('insertar?accion=modificarAdmin', {
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
                    title: 'Administrador Actualizado!',
                    text: 'El Administrador ha sido Actualizado correctamente.',
                    icon: 'success',
                    confirmButtonText: 'OK'
                }).then(() => {
                  var contexto = "${pageContext.request.contextPath}";
                  window.location.href = contexto + "/ServletAdmin?accion=listar";
                });
            } else {
             validarCampoEditar(formData);
            }
        })
        .catch(function(error) {
            console.error('Error:', error);
        });
    }
}

function validarCampoEditar(formData) {
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
