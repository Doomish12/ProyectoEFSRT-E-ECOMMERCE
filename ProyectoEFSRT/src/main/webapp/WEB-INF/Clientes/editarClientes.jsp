<div class="modal fade" id="editarClienteModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-success text-white">
                <h5 class="modal-title">Editar Cliente</h5> 
                <button class="btn-close" data-bs-dismiss="modal"></button>               
            </div>

            <form id="formularioCliente"  action="${pageContext.request.contextPath}/ServletClientes?accion=editar"
                  method="POST" class="was-validated">

                <div class="modal-body">
                    <div class="form-group">
                        <label for="nombres">Nombres</label>
                        <input type="hidden" class="form-control" name="id"  required />
                        <input type="text" class="form-control" name="nombres"  required/>
                    </div>
                    <div class="form-group">
                        <label for="edad">Edad</label>
                        <input type="number" class="form-control" name="edad" id="editClienteEdad" required/>
                    </div>
                    <div class="form-group">
                        <label for="direccion">Direccion</label>
                        <input type="text" class="form-control" name="direccion" id="editClienteDireccion" required/>
                    </div>
   
                <div class="form-group">
                    <label for="correo">Correo</label>
                    <input type="email" class="form-control" name="correo" id="editClienteCorreo" required/>
                </div>
                <div class="form-group">
                    <label for="usuario">Usuario</label>
                    <input type="text" class="form-control" name="usuario" id="editClienteUsuario" required/>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="text" class="form-control" name="password" id="editClientePassword" required/>
                </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
            <!-- Bot�n para mostrar la alerta -->
            <button type="button" class="btn btn-primary" onclick="editarCliente()">Guardar</button>
            <!-- Bot�n de enviar formulario (inicialmente oculto) -->
            <button type="submit" id="editarSubmit" style="display: none;"></button>

        </div>
        </form>
    </div>
</div>
</div>

<!-- EDITAR CLIENTE CODIGO JAVASCRIPT Y AJAX --> 

<script>
    function editarCliente() {
        // Si los campos no est�n vac�os, muestra la alerta de �xito
        Swal.fire({
            title: 'Cliente Actualizado!',
            text: 'Hiciste clic en el bot�n!',
            icon: 'success',
            confirmButtonText: 'OK'
        }).then((result) => {
            // Si el usuario hace clic en "OK", activa el evento de clic del bot�n de enviar formulario
            if (result.isConfirmed) {
                // Encuentra el bot�n de enviar formulario por su ID y haz clic en �l
                var submitButton = document.getElementById('editarSubmit');
                submitButton.click();
            }
        });
    }
</script>