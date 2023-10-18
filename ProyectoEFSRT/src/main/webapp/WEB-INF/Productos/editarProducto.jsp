<div class="modal fade" id="editarProductoModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-success text-white">
                <h5 class="modal-title">Editar Producto</h5> 
                <button class="btn-close" data-bs-dismiss="modal"></button>               
            </div>

        <form action="${pageContext.request.contextPath}/insertar?accion=modificar" method="POST"
              class="was-validated" enctype="multipart/form-data" id="editarProForm">

                <div class="modal-body">
                    <div class="form-group">
                        <input type="hidden" class="form-control"  name="id" required />
                        <label for="nombre">Nombre</label>

                        <input type="text" class="form-control" name="nombre"  required/>
                    </div>
                    <div class="form-group">
                        <label for="descripcion">Descripción</label>
                        <input type="text" class="form-control" name="descripcion"  required/>
                    </div>
                    <div class="form-group">
                        <label for="precio">Precio</label>
                        <input type="text" class="form-control" name="precio"  required/>
                    </div>
                    <div class="form-group">
                        <label for="cantidad">Cantidad</label>
                        <input type="number" class="form-control" name="cantidad"  required/>
                    </div>
                    <div class="form-group">
                        <label for="imagen">Imagen</label>
                        <input type="file" class="form-control" name="imagen" required/>
                    </div> 
                </div>
                <div class="modal-footer">
                                        <!-- Botón para mostrar la alerta -->
                    <button type="button" class="btn btn-warning" onclick="editarProducto()">Guardar</button>
                    <!-- Botón de enviar formulario (inicialmente oculto) -->
                    <button type="submit" id="submitButton" style="display:none;" ></button>
                </div>
            </form>
        </div>
    </div>
</div>
