let idProductoElement = document.querySelectorAll(".idProducto");
let linkProductoElement = document.querySelectorAll(".linkProducto");
let searchInputElement = document.getElementById("searchInput");
let selectCategoriasElement = document.getElementById("categoria");
let marcaElement = document.getElementById("marca");
let precioFiltroElement = document.getElementById("precioFiltro");



if(searchInputElement != null || searchInputElement != undefined){
    searchInputElement.addEventListener("change", function (event) {
        var selectedOption = document.querySelector('option[value="' + event.target.value + '"]');
        console.log(selectedOption);
        var dataId = selectedOption.getAttribute('data-id');
        console.log('Opción seleccionada:', this.value);
        console.log('ID asociado:', dataId);
        event.preventDefault();
            fetch('/InnovaTech/AjaxController', {
                method: 'POST',
                body: JSON.stringify({
                    accion: "filtrarProductosSearch",
                    idProducto: dataId
                })
            })
                    .then(response => response.json())
                    .then(data => {
                        console.log(data);
                        window.location.href = data.redirectUrl; 
                    });
                    
        });
}

selectCategoriasElement.addEventListener("change", function () {
    fetch('/InnovaTech/AjaxController', {
        method: 'POST',
        body: JSON.stringify({
            accion: "filtrarProductosCategoria",
            categoria: selectCategoriasElement.value
        })
    })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                window.location.href = data.redirectUrl; 
            });
});

if(linkProductoElement != null || linkProductoElement != undefined){
    for (let i = 0; i < linkProductoElement.length; i++) {
        linkProductoElement[i].addEventListener("click", function (event) {
            event.preventDefault();
            fetch('/InnovaTech/AjaxController', {
                method: 'POST',
                body: JSON.stringify({
                    accion: "filtrarProductosID",
                    idProducto: idProductoElement[i].value
                })
            })
                    .then(response => response.json())
                    .then(data => {
                        console.log(data);
                        window.location.href = data.redirectUrl; 
                    });
        });
    }
}

if(marcaElement != null || marcaElement != undefined){
    marcaElement.addEventListener("change", function () {
        fetch('/InnovaTech/AjaxController', {
            method: 'POST',
            body: JSON.stringify({
                accion: "filtrarProductosMarca",
                marca: marcaElement.value
            })
        })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    window.location.href = data.redirectUrl; 
                });
    });
}

if(precioFiltroElement != null || precioFiltroElement != undefined){
    precioFiltroElement.addEventListener("change", function () {
        fetch('/InnovaTech/AjaxController', {
            method: 'POST',
            body: JSON.stringify({
                accion: "filtrarProductosPrecio",
                precio: precioFiltroElement.value
            })
        })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    window.location.href = data.redirectUrl; 
                });
    });
}