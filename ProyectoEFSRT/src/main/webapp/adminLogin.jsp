<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Panel | InnovatechX</title>
        <link rel="stylesheet" href="Estilos/adminLogin.css">
        <style>
            #ocultarContra{
                display: none;
            }
        </style>
    </head>
    <body>
        <section class="cont-admin">
            <div class="form-box">
                <div class="form-value">
                    <form action="${pageContext.request.contextPath}/ServletAdmin?accion=login" method="POST">
                        <h2>ADMIN PANEL</h2>

                        <div class="inputbox">
                            <ion-icon name="person-circle-outline"></ion-icon>
                            <input type="text" name="usuario" required>
                            <label for="">Usuario</label>
                        </div>
                        <div class="inputbox">
                            <ion-icon name="eye-outline" id="verContra"></ion-icon>
                            <ion-icon name="eye-off-outline" id="ocultarContra"></ion-icon>
                            <input type="password" name="password" id="password" required>
                            <label for="">Password</label>
                        </div>

                        <button>Iniciar Session</button>
                        <p class="error">${mensajeErroLogin}</p>
                    </form>
                </div>
            </div>
        </section>
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

        <script>
            var verContra = document.querySelector('#verContra');
            var ocultarContra = document.querySelector('#ocultarContra');
            var contra = document.querySelector('#password');
            
            verContra.addEventListener('click', () => {
                contra.type = "text";
                ocultarContra.style.display = "block";
                verContra.style.display = "none";
            });
            ocultarContra.addEventListener('click', () => {
                contra.type = "password";
                ocultarContra.style.display = "none";
                verContra.style.display = "block";
            });


        </script>
    </body>
</html>
