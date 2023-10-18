<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<main class="pagina-entera">
        <div class="head-title">
          <div class="left">
            <h1>Administradores</h1>
            <ul class="breadcrumb">
              <li>
                <a href="#">Admin</a>
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
            <div class="col-lg-10 ">
                <div class="card shadow ">
                    <div class="card-header text-white cambio">
                        <h4 class="mb-0 text-center ">Listado de Administradores</h4>
                    </div>
                    <div class="card-body cambio-color">
                        <div class="form-group ">
                            <a class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#agregarAdminModal" href="#">Registrar Administrador</a>
    
                        </div>
    
                        <div class="table-responsive">
                            <table class="table table-hover table-striped table-secondary">
                                <thead>
                                    <tr class="bg-light">
                                        <th scope="col">#</th>
                                        <th scope="col">Usuario</th>
                                        <th scope="col">Correo</th>
                                        <th scope="col">Contraseña</th> 
                                        <th scope="col">Foto</th>
                                        <th scope="col">Editar</th>
                                        <th scope="col">Eliminar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="c" items="${listaAdmin}" varStatus="status">
                                        <tr>
                                            <td>${status.count}</td>
                                            <td>${c.usuario}</td>
                                            <td>${c.correo}</td>
                                            <td>${c.password}</td>
                                            <td>
                                                 <img src="ServletImgEncontrar?id=${c.id}" class="imgtd" alt="Foto del admin" name="foto"/>
                                            </td>
    
    
                                            <td>     
                                                <a href="#" class="btn btn-success mb-3 editar-admin" data-id="${c.id}" data-bs-toggle="modal" data-bs-target="#editarAdminModal"> <i class="fa-solid fa-pen-to-square"></i></a>     
                                                 </td>
                                            <td>
                                            <a href="${pageContext.request.contextPath}/ServletAdmin?accion=eliminar&id=${c.id}" class="btn btn-danger mb-3" onclick="eliminarAdmin(event)" > Suprimir</a>
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
    function eliminarAdmin(event) {
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
                'El Administrador ha sido eliminado exitosamente.',
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
    $(".editar-admin").click(function() {
        var adminId = $(this).data("id");

        $.ajax({
            url: "${pageContext.request.contextPath}/ServletAdmin?accion=encontrar&id=" + adminId,
            type: "GET",
            dataType: "json",
            success: function(admin) {
                // Llenar los campos del formulario en el modal con los datos del Admin
                $("#editarAdminModal input[name='id']").val(admin.id);
                $("#editarAdminModal input[name='usuario']").val(admin.usuario);
                $("#editarAdminModal input[name='correo']").val(admin.correo);
                $("#editarAdminModal input[name='password']").val(admin.password);
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText);
            }
        });
    });
});
</script>

<jsp:include page="/WEB-INF/Admin/agregarAdmin.jsp"/>
<jsp:include page="/WEB-INF/Admin/editarAdmin.jsp"/>
