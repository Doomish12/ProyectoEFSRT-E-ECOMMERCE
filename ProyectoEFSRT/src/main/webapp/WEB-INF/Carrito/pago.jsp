<%@taglib  prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="es_PE"/>

  <div class="container">
    <!-- Contenido de la página -->
    <!-- ... -->
  
    <!-- Modal Bootstrap -->
    <div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="card-container">
                
                <div class="front">
                    <div class="image">
                        <img src="Imagenes/Carrito/chip.png" alt="">
                        <img src="Imagenes/Carrito/visa.png" alt="">
                    </div>
                    <div class="card-number-box">################</div>
                    <div class="flexbox">
                        <div class="box">
                            <span>Titular de la Targeta</span>
                            <div class="card-holder-name">Nombre Completo</div>
                        </div>
                        <div class="box">
                            <span>Expiración</span>
                            <div class="expiration">
                                <span class="exp-month">mm</span>
                                <span class="exp-year">yy</span>
                            </div>
                        </div>
                    </div>
                </div>
        
                <div class="back">
                    <div class="stripe"></div>
                    <div class="box">
                        <span>cvv</span>
                        <div class="cvv-box"></div>
                        <img src="Imagenes/Carrito/visa.png" alt="">
                    </div>
                </div>
        
            </div>
            <div class="modal-content">
           
                <button type="button"   class="btn-close cerrar-modal" data-bs-dismiss="modal" aria-label="Close"></button>
                <form action="" class="formulario-pago" class="was-validated">
                    <div class="inputBox">
                        <span>Número de Tarjeta</span>
                        <input type="text" maxlength="20" id="credit-card" class="card-number-input" name="targeta">
                    </div>
                    <div class="inputBox">
                        <span>Nombres</span>
                        <input type="text" class="card-holder-input" name="nombres">
                    </div>
                    <div class="flexbox">
                        <div class="inputBox">
                            <span>Expiración</span>
                            <select name="" id="" class="month-input">
                                <option value="mes" selected disabled>mes</option>
                                <option value="01">01</option>
                                <option value="02">02</option>
                                <option value="03">03</option>
                                <option value="04">04</option>
                                <option value="05">05</option>
                                <option value="06">06</option>
                                <option value="07">07</option>
                                <option value="08">08</option>
                                <option value="09">09</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                            </select>
                        </div>
                        <div class="inputBox">
                            <span>Expiración Año</span>
                            <select name="" id="" class="year-input">
                                <option value="Año" selected disabled>Año</option>
                                <option value="2024">2024</option>
                                <option value="2025">2025</option>
                                <option value="2026">2026</option>
                                <option value="2027">2027</option>
                                <option value="2028">2028</option>
                                <option value="2029">2029</option>
                                <option value="2030">2030</option>
                            </select>
                        </div>
                        <div class="inputBox">
                            <span>Cvv</span>
                            <input type="text" maxlength="3" class="cvv-input" name="cvv">
                        </div>
                    </div>
                    <input type="button" onclick="venta()"  value="Pagar <f:formatNumber value="${totalCarrito}" type="currency"/>" class="submit-btn" >
                </form>
            </div>
        </div>
    </div>
</div>
   <script>
function venta() {
 // Validar campos aquí
    var targeta = document.querySelector('.card-number-input').value;
    var nombres = document.querySelector('.card-holder-input').value;
    var cvv = document.querySelector('.cvv-input').value;
    
    if (targeta === '' || nombres === '' || cvv === '') {
        Swal.fire({
            title: 'Error',
            text: 'Por favor, completa todos los campos del formulario.',
            icon: 'error',
            confirmButtonText: 'OK'
        });
    } else {
        // Muestra la alerta de "Procesando pago"
        Swal.fire({
            title: 'Procesando pago...',
            html: '<img src="Imagenes/Animacion-Pago.gif" width="250px" height="250px" alt="Procesando pago...">',
            icon: 'info',
            showConfirmButton: false,
            allowOutsideClick: false,
            customClass: {
            title: 'processing-title' // Agrega la clase de animación aquí
         }
            
        });

        // Simula un proceso de espera de 2 segundos (puedes ajustar el tiempo según tus necesidades)
        setTimeout(function() {
            // Oculta la alerta de "Procesando pago"
            Swal.close();

            // Muestra la alerta de "Compra Realizada"
            Swal.fire({
                title: 'Compra Realizada!',
                text: 'Hiciste clic en el botón!',
                icon: 'success',
                confirmButtonText: 'OK'
            }).then((result) => {
                // Si el usuario hace clic en "OK", activa el evento de clic del botón de enviar formulario
                if (result.isConfirmed) {
                    // Encuentra el botón de enviar formulario por su ID y haz clic en él
                    var submitButton = document.getElementById('venta');
                    submitButton.click();
                }
            });
        }, 3000); // 2000 milisegundos = 2 segundos de espera
    }
}


</script>               
                
 <script>
                    
//CODIGO PARA JAVASCRIPT PARA LA TAEGETA  
document.querySelector('.card-number-input').oninput = () =>{
    document.querySelector('.card-number-box').innerText = document.querySelector('.card-number-input').value;
};

document.querySelector('.card-holder-input').oninput = () =>{
    document.querySelector('.card-holder-name').innerText = document.querySelector('.card-holder-input').value;
};

document.querySelector('.month-input').oninput = () =>{
    document.querySelector('.exp-month').innerText = document.querySelector('.month-input').value;
};

document.querySelector('.year-input').oninput = () =>{
    document.querySelector('.exp-year').innerText = document.querySelector('.year-input').value;
};

document.querySelector('.cvv-input').onmouseenter = () =>{
    document.querySelector('.front').style.transform = 'perspective(1000px) rotateY(-180deg)';
    document.querySelector('.back').style.transform = 'perspective(1000px) rotateY(0deg)';
};

document.querySelector('.cvv-input').onmouseleave = () =>{
    document.querySelector('.front').style.transform = 'perspective(1000px) rotateY(0deg)';
    document.querySelector('.back').style.transform = 'perspective(1000px) rotateY(180deg)';
};

document.querySelector('.cvv-input').oninput = () =>{
    document.querySelector('.cvv-box').innerText = document.querySelector('.cvv-input').value;
};

document.getElementById('credit-card').addEventListener('input', function(event) {
          let input = event.target;
          let formattedValue = input.value.replace(/\D/g, '').substring(0, 16); // Elimina no números y limita a 16 caracteres
          formattedValue = formattedValue.replace(/(\d{4})(?=\d)/g, '$1-'); // Agrega guiones después de cada 4 dígitos
          
          input.value = formattedValue;
        });

</script>