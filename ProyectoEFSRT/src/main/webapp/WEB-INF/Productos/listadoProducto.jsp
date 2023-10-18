<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@taglib  prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="es_PE"/>
<main class="pagina-entera">
        <div class="head-title">
          <div class="left">
            <h1>Productos</h1>
            <ul class="breadcrumb">
              <li>
                <a href="#">Productos</a>
              </li>
              <i class="fas fa-chevron-right"></i>
              <li>
                <a href="#" class="activo">Home</a>
              </li>
            </ul>
          </div>
        <div class="table-data">
          <div class="order">
            <div class="head">
              <!-- <h3>Lista de Clientes</h3> -->
            </div>
            
          <div class="row justify-content-center ">
            <div class="col-lg-12 ">
                <div class="card shadow ">
                    <div class="card-header text-white cambio">
                        <h4 class="mb-0 text-center ">Listado de Productos</h4>
                    </div>
                    <div class="card-body cambio-color">
                        <div class="form-group ">
                            <a class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#agregarProductoModal" href="#">Registrar Producto</a>
    
                        </div>
    
                        <div class="table-responsive">
                            <table class="table table-hover table-striped table-secondary">
                                <thead>
                                    <tr class="bg-light">
                                        <th scope="col">#</th>
                                        <th scope="col">Nombre</th>
                                        <th scope="col">Descripcion</th>
                                        <th scope="col">Precio</th>                                  
                                        <th scope="col">Cantidad</th>
                                        <th scope="col">Imagen</th>
                                        <th scope="col">Editar</th>
                                        <th scope="col">Eliminar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="c" items="${listaProducto}" varStatus="status">
                                        <tr>
                                            <td>${status.count}</td>
                                            <td>${c.nombre}</td>
                                            <td>${c.descripcion}</td>
                                            <td><f:formatNumber value="${c.precio}" type="currency"/></td>
                                            <td>${c.cantidad}</td>
                                            <td>
                                                <img src="imagen?idProducto=${c.id}" class="imgtd" alt="Imagen del Producto" name="imagen"/>
                                            </td>    
    
    
                                            <td>     
                                                <a href="#" class="btn btn-success mb-3 editar-producto" data-id="${c.id}" data-bs-toggle="modal" data-bs-target="#editarProductoModal"> <i class="fa-solid fa-pen-to-square"></i></a>     
                                                 </td>
                                            <td>
                                            <a href="${pageContext.request.contextPath}/ServletProducto?accion=eliminar&id=${c.id}" class="btn btn-danger mb-3" onclick="eliminarProducto(event)" >Suprimir</a>
                                            </td>
                                                 
                                            
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

            </div>
          </div>
  </main>
  
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
    //ELIMINAR PRODUCTOS CODIGO JAVASCRIPT Y AJAX:

    function eliminarProducto(event) {
    event.preventDefault(); // Previene el comportamiento predeterminado del enlace

    Swal.fire({
        title: '¿Estás seguro?',
        text: "Esta acción no se puede deshacer",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sí, eliminarlo'
    }).then((result) => {
        if (result.isConfirmed) {
             Swal.fire(
                'Eliminado!',
                'El Producto ha sido eliminado exitosamente.',
                'success'
            ).then(() => {
                window.location.href = event.target.href;
            });
        }
    });
}
    

</script>
<script>
$(document).ready(function() {
    $(".editar-producto").click(function() {
        var productoId = $(this).data("id");

        $.ajax({
            url: "${pageContext.request.contextPath}/ServletProducto?accion=encontrar&id=" + productoId,
            type: "GET",
            dataType: "json",
            success: function(producto) {
                // Llenar los campos del formulario en el modal con los datos del Producto
                $("#editarProductoModal input[name='id']").val(producto.id);
                $("#editarProductoModal input[name='nombre']").val(producto.nombre);
                $("#editarProductoModal input[name='descripcion']").val(producto.descripcion);
                $("#editarProductoModal input[name='precio']").val(producto.precio);
                $("#editarProductoModal input[name='cantidad']").val(producto.cantidad);
    },
            error: function(xhr, status, error) {
                console.error(xhr.responseText);
            }
        });
    });
});
</script>

<jsp:include page="/WEB-INF/Productos/agregarProducto.jsp"/>
<jsp:include page="/WEB-INF/Productos/editarProducto.jsp"/>
