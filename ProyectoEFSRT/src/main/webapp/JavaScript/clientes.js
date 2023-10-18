/* Created by Tivotal */

// Función para cambiar el modo oscuro
function toggleDarkMode(isDarkMode) {
  if (isDarkMode) {
    document.body.classList.add("dark");
  } else {
    document.body.classList.remove("dark");
  }
}

// Verificar si hay una preferencia de modo oscuro almacenada en localStorage
const isDarkModeEnabled = localStorage.getItem("darkModeEnabled") === "true";

// Aplicar el modo oscuro según la preferencia almacenada
toggleDarkMode(isDarkModeEnabled);

let sideMenu = document.querySelectorAll(".enlace");
sideMenu.forEach((item) => {
  let li = item.parentElement;

  item.addEventListener("click", () => {
    sideMenu.forEach((link) => {
      link.parentElement.classList.remove("activo");
    });
    li.classList.add("activo");
  });
});

let menuBar = document.querySelector(".menu-btn");
let sideBar = document.querySelector(".barra-lateral");
menuBar.addEventListener("click", () => {
  sideBar.classList.toggle("hide");
});

let switchMode = document.getElementById("switch-mode");
switchMode.addEventListener("change", (e) => {
  const isDarkMode = e.target.checked;
  // Guardar la preferencia de modo oscuro en localStorage
  localStorage.setItem("darkModeEnabled", isDarkMode);
  toggleDarkMode(isDarkMode);
});

