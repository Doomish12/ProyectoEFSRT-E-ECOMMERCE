<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib  prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="es_PE"/>
<main class="pagina-entera">
        <div class="head-title">
          <div class="left">
            <h1>Dashboard</h1>
            <ul class="breadcrumb">
              <li>
                <a href="#">Dashboard</a>
              </li>
              <i class="fas fa-chevron-right"></i>
              <li>
                <a href="#" class="activo">Home</a>
              </li>
            </ul>
          </div>

            <a href="${pageContext.request.contextPath}/ServletClientes?accion=pdfHistorial" class="download-btn">
            <i class="fas fa-cloud-download-alt"></i>
            <span class="text">Descargar Historial Ventas</span>
          </a>
        </div>

        <div class="box-info">
          <li>
            
              <i class="fa-solid fa-message"></i>
            <span class="text">
              <h3>${contadorMensaje}</h3>
              <p>Mensajes</p>
            </span>
          
          </li>
          <li>
            <i class="fas fa-people-group"></i>
            <span class="text">
              <h3>${contador}</h3>
              <p>Clientes</p>
            </span>
          </li>
          <li>
            <i class="fas fa-dollar-sign"></i>
            <span class="text">
              <h3><f:formatNumber value="${historialVentas}" type="currency"/></h3>
              <p>Ventas Totales</p>
            </span>
          </li>
        </div>

        <div class="table-data">
          <div class="order">
            <div class="head">
              <!-- <h3>Lista de Clientes</h3> -->
            </div>
            
          <div class="row justify-content-center ">
            <div class="col-lg-10 ">
                <div class="card shadow ">
                    <div class="card-header text-white cambio">
                        <h4 class="mb-0 text-center ">Listado de Clientes</h4>
                    </div>
                    <div class="card-body cambio-color">
                        <div class="form-group ">
                            <a class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#agregarClienteModal" href="#">Registrar Cliente</a>
    
                        </div>
    
                        <div class="table-responsive">
                            <table class="table table-hover table-striped table-secondary">
                                <thead>
                                    <tr class="bg-light">
                                        <th scope="col">#</th>
                                        <th scope="col">Nombre</th>
                                        <th scope="col">Edad</th>
                                        <th scope="col">Direccion</th>
                                        <th scope="col">Correo</th>  
                                        <th scope="col">Editar</th>
                                        <th scope="col">Eliminar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="c" items="${lista}" varStatus="status">
                                        <tr>
                                            <td>${status.count}</td>
                                            <td>${c.nombres}</td>
                                            <td>${c.edad}</td>
                                            <td>${c.direccion}</td>
                                            <td>${c.correo}</td>
    
    
                                            <td>
                                                    <a href="#" class="btn btn-success mb-3 editar-cliente" data-id="${c.id}" data-bs-toggle="modal" data-bs-target="#editarClienteModal"> <i class="fa-solid fa-pen-to-square"></i></a>
                                            </td>
                                            <td>
                                                 <a href="${pageContext.request.contextPath}/ServletClientes?accion=eliminar&id=${c.id}" class="btn btn-danger mb-3" onclick="confirmarEliminar(event)" > Suprimir </a>
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
  
  <script>
    function confirmarEliminar(event) {
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
                'El Cliente ha sido eliminado exitosamente.',
                'success'
            ).then(() => {
                window.location.href = event.target.href;
            });
        }
    });
}
    
</script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function() {
    $(".editar-cliente").click(function() {
        var clienteId = $(this).data("id");

        $.ajax({
            url: "${pageContext.request.contextPath}/ServletClientes?accion=encontrar&id=" + clienteId,
            type: "GET",
            dataType: "json",
            success: function(cliente) {
                // Llenar los campos del formulario en el modal con los datos del libro
                $("#editarClienteModal input[name='id']").val(cliente.id);
                $("#editarClienteModal input[name='nombres']").val(cliente.nombres);
                $("#editarClienteModal input[name='edad']").val(cliente.edad);
                $("#editarClienteModal input[name='direccion']").val(cliente.direccion);
                $("#editarClienteModal input[name='correo']").val(cliente.correo);
                $("#editarClienteModal input[name='usuario']").val(cliente.usuario);
                $("#editarClienteModal input[name='password']").val(cliente.password);
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText);
            }
        });
    });
});
</script>

<jsp:include page="/WEB-INF/Clientes/agregarClientes.jsp"/>
<jsp:include page="/WEB-INF/Clientes/editarClientes.jsp"/>
