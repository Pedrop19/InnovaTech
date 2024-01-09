let btnMasElement = document.querySelectorAll(".btn-mas");
let btnMenosElement = document.querySelectorAll(".btn-menos");
let cantidadElement = document.querySelectorAll(".cantidad");
let idElement = document.querySelectorAll(".idArticulo");
let precioTotalElement = document.querySelectorAll(".precioTotal");
let precioElement = document.querySelectorAll(".precioArticulo");
let itemsCarritoElement = document.querySelector(".item-carrito");



for (let i = 0; i < btnMasElement.length; i++) {
    btnMasElement[i].addEventListener("click", function () {
    fetch('/InnovaTech/AjaxController', {
        method: 'POST',
        body: JSON.stringify({
            id: idElement[i].value,
            accion: "sumar"
        })
    })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                let cantidad = cantidadElement[i];
                cantidad.innerHTML = data;
                if (cantidad.innerHTML == 0) {
                    let parentElement = btnMasElement[i].closest(".item-carrito");
                    console.log(parentElement);
                    parentElement.remove();
                    if(itemsCarritoElement == 0 || itemsCarritoElement == null){
                        window.location.reload();
                    }
                }
                let precioTotal = parseFloat(parseInt(data) * parseFloat(precioElement[i].value)).toFixed(2);
                precioTotalElement[i].innerHTML = precioTotal;
            });
});
}

for (let i = 0; i < btnMenosElement.length; i++) {
    btnMenosElement[i].addEventListener("click", function () {
    fetch('/InnovaTech/AjaxController', {
        method: 'POST',
        body: JSON.stringify({
            id: idElement[i].value,
            accion: "restar"
        })
    })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                let cantidad = cantidadElement[i];
                cantidad.innerHTML = data;
                if (cantidad.innerHTML == 0) {
                    let parentElement = btnMenosElement[i].closest(".item-carrito");
                    console.log(parentElement)
                    parentElement.remove();
                    if(itemsCarritoElement.length() == 0 || itemsCarritoElement == null){
                        window.location.reload();
                    }
                }
                let precioTotal = parseFloat(parseInt(data) * parseFloat(precioElement[i].value)).toFixed(2);
                precioTotalElement[i].innerHTML = precioTotal;
            });
});
}
