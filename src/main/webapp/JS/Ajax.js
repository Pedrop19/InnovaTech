let emailElement = document.getElementById("email");
let nifElement = document.getElementById("nif");
let password1Element = document.getElementById("password1");
let password2Element = document.getElementById("password2");
let error = document.getElementById("error");
let btnRegsitrarElement = document.getElementById("btn-registrar");
let codigoPostalElement = document.getElementById("codigoPostal");

let nifElementvalue = nifElement.value;
let rgexCodigoPostal = /^(0[1-9]|[1-4][0-9]|5[0-2])[0-9]{3}$/;


function validateCodigoPostal() {
    if (rgexCodigoPostal.test(codigoPostalElement.value)) {
        if(error.classList.contains("text-success")){
            error.classList.remove("text-success");
            error.classList.add("text-danger");
        }else{
            error.classList.add("text-danger");
        }
        error.innerHTML = "El código postal no es válido";
        btnRegsitrarElement.disabled = true;
        return false;
    }else{
        error.classList.remove("text-danger");
        error.classList.add("text-success");
        error.innerHTML = "El código postal es válido";
        btnRegsitrarElement.disabled = false;
    }
    return true;
}

function validatePassword() {
    if (password1Element.value !== password2Element.value) {
        if(error.classList.contains("text-success")){
            error.classList.remove("text-success");
            error.classList.add("text-danger");
        }else{
            error.classList.add("text-danger");
        }
        error.innerHTML = "Las contraseñas no coinciden";
        btnRegsitrarElement.disabled = true;
        return false;
    }else{
        error.classList.remove("text-danger");
        error.classList.add("text-success");
        error.innerHTML = "Las contraseñas coinciden";
        btnRegsitrarElement.disabled = false;
    }
    return true;
}

function validateEmail() {
    fetch('/InnovaTech/AjaxController', {
        method: 'POST',
        body: JSON.stringify({
            accion: "validateEmail",
            email: emailElement.value
        })
    })
        .then(response => response.json())
        .then(data => {
            if (data === true) {
                if(error.classList.contains("text-success")){
                error.classList.remove("text-success");
                error.classList.add("text-danger");
                }
                error.innerHTML = "El email ya existe";
                console.log("El email ya existe");
                btnRegsitrarElement.disabled = true;
                console.log(data);
            } else {
                error.classList.remove("text-danger");
                error.classList.add("text-success");
                error.innerHTML = "El email está disponible";
                console.log("El email está disponible");
                btnRegsitrarElement.disabled = false;
            }
        });
}

function LetraNif(dni) {      
    let cadena = "TRWAGMYFPDXBNJZSQVHLCKET";
    let posicion = dni % 23;
    let letra = cadena.substring(posicion, posicion + 1);
    return dni + letra;
}

emailElement.addEventListener("keyup", validateEmail);
password2Element.addEventListener("keyup", validatePassword);
nifElement.addEventListener("keyup", function () {
    if (nifElement.value.length === 8) {
        nifElement.value = LetraNif(nifElement.value);
    }
});

codigoPostalElement.addEventListener("keyup", validateCodigoPostal);