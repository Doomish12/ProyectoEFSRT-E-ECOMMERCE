/* global Swal, contexto */

//EDITAR PRODUCTOS CODIGO JAVASCRIPT Y AJAX:
function editarProducto() {
    var formulario = document.getElementById('editarProForm');
    var formData = new FormData(formulario);

    // Validar campos
    if (validarCampos(formData)) {
        fetch('insertar?accion=modificar', {
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
                    title: 'Producto Actualizado!',
                    text: 'El producto ha sido Actualizado correctamente.',
                    icon: 'success',
                    confirmButtonText: 'OK'
                }).then(() => {
                 
                  window.location.href = contexto + "/ServletProducto?accion=listar";
                });
            } else {
             validarCampos(formData);
            }
        })
        .catch(function(error) {
            console.error('Error:', error);
        });
    }
}

function validarCampos(formData) {
    // Validar campos aquí
    var nombre = formData.get('nombre');
    var descripcion = formData.get('descripcion');
    var precio = formData.get('precio');
    var cantidad = formData.get('cantidad');
    var imagen = formData.get('imagen');

    if (!nombre || !descripcion || !precio || !cantidad || !imagen) {
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

//AGREGAR PRODUCTOS CODIGO JAVASCRIPT Y AJAX:

function guardarProducto() {
    var formulario = document.getElementById('productoForm');
    var formData = new FormData(formulario);

    // Validar campos
    if (validarCampos(formData)) {
        fetch('insertar?accion=agregar', {
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
                    title: 'Producto Registrado!',
                    text: 'El producto ha sido registrado correctamente.',
                    icon: 'success',
                    confirmButtonText: 'OK'
                }).then(() => {
                  window.location.href = contexto + "/ServletProducto?accion=listar";
                });
            } else {
             validarCampos(formData);
            }
        })
        .catch(function(error) {
            console.error('Error:', error);
        });
    }
}

function validarCampos(formData) {
    // Validar campos aquí
    var nombre = formData.get('nombre');
    var descripcion = formData.get('descripcion');
    var precio = formData.get('precio');
    var cantidad = formData.get('cantidad');
    var imagen = formData.get('imagen');

    if (!nombre || !descripcion || !precio || !cantidad || !imagen) {
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

