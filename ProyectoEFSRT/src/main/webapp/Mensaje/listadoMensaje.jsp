<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<style>
    .eliminar-mensaje{
        height: 250px;
    }
    .mensaje-bg{
        background-color: #eae3e3;
        border-radius: 20px;
        padding: 20px;
        margin-bottom: 20px;
    }
    .mensaje-header{
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .mensaje-header p{
        color: rgb(7, 176, 213);
        font-weight: bold;
    }
    .mensaje-body p{
        color: black;
    }
</style>
<main class="pagina-entera">
    <div class="head-title">
        <div class="left">
            <h1>Mensajes</h1>
            <ul class="breadcrumb">
                <li>
                    <a href="#">Mensajes</a>
                </li>
                <i class="fas fa-chevron-right"></i>
                <li>
                    <a href="#" class="activo">Home</a>
                </li>
            </ul>
        </div>


        <div class="table-data">

            <div class="col-lg-4 eliminar-mensaje">
                <h5>Eliminar Mensaje</h5>
                <form action="${pageContext.request.contextPath}/ServletMensaje?accion=eliminar" method="POST">
                    <p>Seleccione un mensaje para eliminar:</p>
                    <select class="form-select mb-3" id="selectMensaje" name="id">
                        <c:forEach var="mensaje" items="${listaMensaje}" varStatus="status">
                            <option value="${mensaje.id}">${status.count}</option>
                        </c:forEach>
                    </select>

                    <!-- Botón para mostrar la alerta -->
                    <button type="button" class="btn btn-danger" onclick="mostrarAlerta()">Eliminar</button>
                    <!-- Botón de enviar formulario (inicialmente oculto) -->
                    <button type="submit" id="submitButton" style="display: none;"></button>
                </form>
            </div>
            <div class="col-lg-7">
                <h3><i class="fa-solid fa-comments"></i> Mensajes</h3>
                <c:forEach var="c" items="${listaMensaje}">
                    <div class="mensaje-bg">
                        <div class="mensaje-header">
                            <p>${c.nombre}</p>
                            <p><i class="fa-regular fa-clock"></i> ${c.fechaEnvio}</p>
                        </div> 
                        <div class="mensaje-body">
                            <p>${c.correo}</p>    
                            <p>${c.mensaje}</p>
                        </div>           
                    </div>
                </c:forEach>     
            </div>
        </div>
    </div>

</main>
<script>
    function mostrarAlerta() {
        var mensajeNulo = document.getElementById("selectMensaje");

        if (mensajeNulo.value === "") {
              Swal.fire({
                        title: 'Error',
                        text: "No hay ningún mensaje para eliminar",
                        icon: 'error',
                        confirmButtonText: 'OK'
                    });               
        } else {
            Swal.fire({
                title: 'Mensaje Eliminado!',
                text: 'Hiciste clic en el botón!',
                icon: 'success',
                confirmButtonText: 'OK'
            }).then((result) => {
                // Si el usuario hace clic en "OK", activa el evento de clic del botón de enviar formulario
                if (result.isConfirmed) {
                    // Encuentra el botón de enviar formulario por su ID y haz clic en él
                    var submitButton = document.getElementById('submitButton');
                    submitButton.click();
                }
            });
        }

    }

</script>           
