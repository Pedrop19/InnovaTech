let btnCambiarElement = document.getElementById('btn-cambiar');
let idContrActual = document.getElementById('contrActual');
let idContrNueva = document.getElementById('contrNueva');
let password2Element = document.getElementById('contrNueva2');
let btnAceptarElement = document.getElementById('btn-aceptar');
let btnCancelarElement = document.getElementById('btn-cancelar');
let divContrasenaElement = document.getElementById('contrasena');

btnCambiarElement.addEventListener('click', function () {
   if(divContrasenaElement.classList.contains('d-none')){
       divContrasenaElement.classList.remove('d-none');
    }else{
         divContrasenaElement.classList.add('d-none');
     }
});